package com.bysj.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

public class SecurityUtils {
    
    public static final String KEY_MD5 = "MD5";
//    private static final String KEY_SHA = "SHA";

    public static String getToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }

    public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer(
                "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

    public static String encode(String password) {
        BigInteger sha = null;
        byte[] inputData = password.getBytes();
        String after = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_MD5);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
            after = sha.toString(32);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return after;
    }
}
