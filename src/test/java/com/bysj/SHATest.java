package com.bysj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.bysj.model.User;

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
    public void test() throws IOException{
//        String string = null;
//        System.out.println(string.isEmpty());
//        System.out.println(SecurityUtils.getToken());
//        System.out.println(new Random(Calendar.getInstance().getTimeInMillis()).nextInt(6));
        
//        System.out.println(System.getProperty("user.dir"));
        
//        User user = new User();
//        com.bysj.User user2 = new com.bysj.User();
//        
//        FileOutputStream fos = new FileOutputStream(new File("upload"));
//        ObjectOutputStream os = new ObjectOutputStream(fos);
//        user2.setUsername("lzg");
//        os.writeObject(user2);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(sdf.format(date));
    }
}
