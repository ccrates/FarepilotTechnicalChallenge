package com.conradcrates.farepilottechnicalchallenge;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.conradcrates.farepilottechnicalchallenge.backend.NetworkResponse;
import com.conradcrates.farepilottechnicalchallenge.backend.RestClientFactory;
import com.conradcrates.farepilottechnicalchallenge.constants.IntentConstants;
import com.conradcrates.farepilottechnicalchallenge.constants.NetworkResponseConstants;
import com.conradcrates.farepilottechnicalchallenge.gravatar.Gravatar;

public class ProfileActivity extends AppCompatActivity {

    private TextView username, password;
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();

        NetworkResponse response = RestClientFactory.getInstance().getRestClient().getUserDetails();
        String email = response.getValue(NetworkResponseConstants.EMAIL);

        username.setText(email);

        String url = Gravatar.createGravatarUrl(email);
        Glide.with(getApplicationContext()).load(url).into(avatar);

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
        password = findViewById(R.id.text_password);
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
                                if(camIntent.resolveActivity(getPackageManager()) != null){
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
        switch (requestCode) {
            case IntentConstants.REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap img = (Bitmap) extras.get(IntentConstants.CAMERA_DATA);
                }
                break;
            case IntentConstants.REQUEST_IMAGE_GALLERY:
                if (resultCode == RESULT_OK) {
                    Uri img = data.getData();
                }
                break;
        }
    }
}