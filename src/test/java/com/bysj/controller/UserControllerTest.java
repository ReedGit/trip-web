package com.bysj.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

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
}
