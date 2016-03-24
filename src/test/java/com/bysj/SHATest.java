package com.bysj;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.junit.Test;

public class SHATest {
    
    public static final String KEY_MD5 = "MD5";
    public static final String KEY_SHA = "SHA";

    @Test
    public void encode() {
        BigInteger sha = null;
        String inputStr = "19940625";
        System.out.println("=======加密前的数据:" + inputStr);
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_MD5);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
            System.out.println("md5加密后:" + sha.toString(16));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void test(){
//        String string = null;
//        System.out.println(string.isEmpty());
//        System.out.println(SecurityUtils.getToken());
//        System.out.println(new Random(Calendar.getInstance().getTimeInMillis()).nextInt(6));
        
        System.out.println(System.getProperty("user.dir"));
    }
}
