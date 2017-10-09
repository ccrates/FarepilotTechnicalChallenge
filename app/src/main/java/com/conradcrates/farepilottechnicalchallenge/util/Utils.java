package com.conradcrates.farepilottechnicalchallenge.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Conrad on 08/10/2017.
 */

public class Utils {

    public static String createGravatarUrl(String email){
        String hashedEmail = md5(email.trim().toLowerCase());
        return "https://www.gravatar.com/avatar/" + hashedEmail + "?s=244";
    }

    // Code courtesy of stackoverflow: https://stackoverflow.com/questions/4846484/md5-hashing-in-android
    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Bitmap resizeBitmap(Bitmap bmp, int maxDimension){
        float height = bmp.getHeight();
        float width = bmp.getWidth();

        float ratio = height / width;

        if(ratio > 1){
            // Height is greater than width
            height = maxDimension;
            width = height / ratio;
        } else {
            // Width is greater than height
            width = maxDimension;
            height = width * ratio;
        }

        return Bitmap.createScaledBitmap(bmp, (int)width, (int)height, false);
    }

    public static void addBlackAndWhiteFilter(Bitmap source){
        Canvas canvas = new Canvas(source);
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(source, 0, 0, paint);
    }

    public static byte[] convertBitmapToByteArray(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

}