package com.example.demo.common.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WebClient {
    public static String downloadString(String url) throws IOException {
        URL _url = new URL(url);
        InputStream is = _url.openStream();
        Scanner sc = new Scanner(is, "utf-8");
        String result = "";
        while (sc.hasNextLine()) {
            result += sc.nextLine();
        }
        sc.close();
        return result;
    }

    public static byte[] downloadData(String url) throws IOException {
        URL _url = new URL(url);
        URLConnection con = _url.openConnection();
        con.setReadTimeout(10000);
        con.connect();
        Map<String, List<String>> map = con.getHeaderFields();
        BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
        byte[] buffer = new byte[Integer.parseInt(map.get("Content-Length").get(0))];
        bis.read(buffer);
        return buffer;
    }

    public static String uploadData(String url, byte[] bytes) {
    	String result = "";
    	try {
	    	URL _url = new URL(url);
	        HttpURLConnection con = (HttpURLConnection) _url.openConnection();
			con.setReadTimeout(10000);
	        con.setRequestMethod("POST");
//	        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        con.setRequestProperty("Content-Type", "application/json");
//	        con.setRequestProperty("Data-Type", "json");
//	        con.setRequestProperty("Content-Type", "application/form;charset=utf-8");
	        con.setDoInput(true);
	        con.setDoOutput(true);
	        // 握手
	        con.connect();
	        // post数据
	        BufferedOutputStream os = new BufferedOutputStream(con.getOutputStream());
	        os.write(bytes);
	        os.close();
	        // 打开输入流
	        InputStream is = con.getInputStream();
	        Scanner sc = new Scanner(is, "utf-8");
	        while (sc.hasNextLine()) {
	            result += sc.nextLine();
	        }
	        sc.close();
       	} catch (Exception e) {
       		e.printStackTrace();
    	}
        return result;
    }
}
