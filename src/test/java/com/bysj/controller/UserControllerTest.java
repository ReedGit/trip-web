package com.bysj.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UserControllerTest {

    private static final String BASEURL = "http://localhost:8080/trip";

    @Test
    public void register() {
        String url = BASEURL + "/user/register";
        String data = "email=1101409940@qq.com&password=-3d46dda4497f6393b130feabbd910027";
        try {
            URL url2 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url2.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(
                    urlConnection.getOutputStream());

            writer.write(data);
            writer.flush();

            // Get the response
            StringBuffer answer = new StringBuffer();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            writer.close();
            reader.close();
            System.out.println("register:"+answer);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void login(){
        String url = BASEURL + "/user/login";
        String data = "email=zerolu@nuist.com&password=-3d46dda4497f6393b130feabbd910027";
        try {
            URL url2 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url2.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(
                    urlConnection.getOutputStream());

            writer.write(data);
            writer.flush();

            // Get the response
            StringBuffer answer = new StringBuffer();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            writer.close();
            reader.close();
            System.out.println("login:"+answer);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void getUser(){
        String url = BASEURL + "/user/1";
        try {
            URL url2 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url2.openConnection();
            urlConnection.setRequestMethod("GET");

            // Get the response
            StringBuffer answer = new StringBuffer();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            reader.close();
            System.out.println("getUser:"+answer);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void edituser(){
        String url = BASEURL + "/user/1";
        String data = "email=zerolu@nuist.com&password=-3d46dda4497f6393b130feabbd910027&introduction=你好世界"
                + "&token=e9a132d0-4133-4ca1-b6f6-2832db03f653&introduction=你好世界&nickname=苏轼";
        try {
            URL url2 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url2.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(
                    urlConnection.getOutputStream());

            writer.write(data);
            writer.flush();

            // Get the response
            StringBuffer answer = new StringBuffer();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            writer.close();
            reader.close();
            System.out.println("editUser:"+answer);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     */
    @SuppressWarnings("deprecation")
    @Test
    public void exist(){
        String url = BASEURL + "/user/exist?";
        String data = "email="+URLEncoder.encode("zerol@nuist.com");
        try {
            URL url2 = new URL(url+data);
            HttpURLConnection urlConnection = (HttpURLConnection) url2.openConnection();
            urlConnection.setRequestMethod("GET");

            // Get the response
            StringBuffer answer = new StringBuffer();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            reader.close();
            System.out.println("exist:"+answer);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void reset(){
        String url = BASEURL + "/user/reset";
        String data = "email=1101409940@qq.com";
        try {
            URL url2 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url2.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(
                    urlConnection.getOutputStream());

            writer.write(data);
            writer.flush();

            // Get the response
            StringBuffer answer = new StringBuffer();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            writer.close();
            reader.close();
            System.out.println("reset:"+answer);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void uploadImage(){
        List<String> list  = new ArrayList<String>();  //要上传的文件名,如：d:\haha.doc.你要实现自己的业务。我这里就是一个空list.
        list.add("C:\\Users\\zerolu\\Pictures\\blog-572x320-stackoverflow.png");
        try {  
            String BOUNDARY = "---------------------------289502075121020"; // 定义数据分隔线
            URL url = new URL(BASEURL+"/user/1/change_avatar");
//            Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("localhost", 8888));
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
            conn.setRequestProperty("Charsert", "UTF-8");   
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);  
              
            OutputStream out = new DataOutputStream(conn.getOutputStream());  
            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线  
            int leng = list.size();  
            for(int i=0;i<leng;i++){  
                String fname = list.get(i);  
                File file = new File(fname);  
                StringBuilder sb = new StringBuilder();    
                sb.append("--");    
                sb.append(BOUNDARY);    
                sb.append("\r\n");    
                sb.append("Content-Disposition: form-data; name=\"headImage"+"\"; filename=\""+ file.getName() + "\"\r\n");    
                sb.append("Content-Type: image/png\r\n\r\n");    
                  
                byte[] data = sb.toString().getBytes();  
                out.write(data);  
                DataInputStream in = new DataInputStream(new FileInputStream(file));  
                int bytes = 0;  
                byte[] bufferOut = new byte[1024];  
                while ((bytes = in.read(bufferOut)) != -1) {  
                    out.write(bufferOut, 0, bytes);  
                }  
                out.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个  
                in.close();  
            }  
            StringBuilder text = new StringBuilder();
            text.append("\r\n--")
                .append(BOUNDARY+"\r\n")
                .append("Content-Disposition: form-data; name=\"token\"\r\n\r\n")
                .append("e9a132d0-4133-4ca1-b6f6-2832db03f653");
            out.write(text.toString().getBytes());
            out.write(end_data);  
            out.flush();    
            System.out.println(out.toString());
            out.close();   
              
            // 定义BufferedReader输入流来读取URL的响应  
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder answer = new StringBuilder();
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                answer.append(line);
            }  
            System.out.println("saveImage:"+answer);
  
        } catch (Exception e) {  
            System.out.println("发送POST请求出现异常！" + e);  
            e.printStackTrace();  
        }  
    }
}
