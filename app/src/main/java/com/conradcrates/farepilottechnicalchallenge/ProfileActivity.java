package com.conradcrates.farepilottechnicalchallenge;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.conradcrates.farepilottechnicalchallenge.backend.NetworkCallback;
import com.conradcrates.farepilottechnicalchallenge.backend.NetworkResponse;
import com.conradcrates.farepilottechnicalchallenge.backend.RestClientFactory;
import com.conradcrates.farepilottechnicalchallenge.constants.IntentConstants;
import com.conradcrates.farepilottechnicalchallenge.constants.NetworkResponseConstants;
import com.conradcrates.farepilottechnicalchallenge.constants.SharedPreferenceConstants;
import com.conradcrates.farepilottechnicalchallenge.util.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    private TextView username;
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();

        final Bitmap bmp = loadAvatar();
        if(bmp != null) {
            avatar.setImageBitmap(bmp);
        }

        RestClientFactory.getInstance().getRestClient().getUserDetails(new NetworkCallback() {
            @Override
            public void onSuccess(NetworkResponse response) {
                String email = response.getValue(NetworkResponseConstants.EMAIL);
                if(email == null || email.isEmpty()){
                    SharedPreferences prefs = getSharedPreferences(SharedPreferenceConstants.PREFS_KEY, Context.MODE_PRIVATE);
                    email = prefs.getString(SharedPreferenceConstants.USER_EMAIL, "");
                }
                username.setText(email);

                String avatarUrl = response.getValue(NetworkResponseConstants.AVATAR_URL);
                if(avatarUrl != null && !avatarUrl.isEmpty()){
                    // If the server has an avatar url, it overrides the stored image
                    Glide.with(getApplicationContext()).load(avatarUrl).into(avatar);
                } else if (bmp == null){
                    // If there is no stored image and avatar url, generate a gravatar using email
                    avatarUrl = Utils.createGravatarUrl(email);
                    Glide.with(getApplicationContext()).load(avatarUrl).into(avatar);
                }
            }

            @Override
            public void onFailure(NetworkResponse response) {

            }
        });

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = avatarDialog();
                dialog.show();
            }
        });
    }

    private void initViews(){
        username = findViewById(R.id.text_username);
        avatar = findViewById(R.id.image_avatar);
    }

    private Dialog avatarDialog() {
        CharSequence[] items = new CharSequence[]{
                getString(R.string.profile_dialog_avatar_camera),
                getString(R.string.profile_dialog_avatar_gallery)};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.profile_dialog_avatar_title)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        switch (i){
                            case 0:
                                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                if(camIntent.resolveActivity(getPackageManager()) != null) {
                                    startActivityForResult(camIntent, IntentConstants.REQUEST_IMAGE_CAPTURE);
                                }
                                break;
                            case 1:
                                Intent galIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                if(galIntent.resolveActivity(getPackageManager()) != null){
                                    startActivityForResult(galIntent, IntentConstants.REQUEST_IMAGE_GALLERY);
                                }
                                break;
                        }
                    }
                });
        return builder.create();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bitmap bmp = null;
            switch (requestCode) {
                case IntentConstants.REQUEST_IMAGE_CAPTURE:
                    Bundle extras = data.getExtras();
                    bmp = (Bitmap)extras.get(IntentConstants.CAMERA_DATA);
                    break;
                case IntentConstants.REQUEST_IMAGE_GALLERY:
                    try {
                        Uri img = data.getData();
                        InputStream is = getContentResolver().openInputStream(img);
                        bmp = BitmapFactory.decodeStream(is);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            if(bmp != null) {
                Bitmap img = Utils.resizeBitmap(bmp, Utils.BITMAP_DEFAULT_SIZE);
                Utils.addBlackAndWhiteFilter(img);
                avatar.setImageBitmap(img);

                String base64encoded = Base64.encodeToString(Utils.convertBitmapToByteArray(img), Base64.DEFAULT);
                RestClientFactory.getInstance().getRestClient().setUserAvatar(base64encoded, null);

                saveAvatar(img);
            }
        }
    }

    private void saveAvatar(Bitmap bmp){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("avatar", Context.MODE_PRIVATE);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos != null) {
                    fos.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private Bitmap loadAvatar(){
        Bitmap bmp = null;
        FileInputStream fis = null;
        try {
            fis = openFileInput("avatar");
            bmp = BitmapFactory.decodeStream(fis);
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null) {
                    fis.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return bmp;
    }
}