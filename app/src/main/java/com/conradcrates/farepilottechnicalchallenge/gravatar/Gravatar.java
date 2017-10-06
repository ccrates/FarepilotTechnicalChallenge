package com.conradcrates.farepilottechnicalchallenge.gravatar;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Conrad on 06/10/2017.
 */

public class Gravatar {

    public static String createGravatarUrl(String email){
        String hashedEmail = md5(email.trim().toLowerCase());
        return "https://www.gravatar.com/avatar/" + hashedEmail;
    }

    // Code courtesy of stackoverflow: https://stackoverflow.com/questions/4846484/md5-hashing-in-android
    private static String md5(final String s) {
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
}