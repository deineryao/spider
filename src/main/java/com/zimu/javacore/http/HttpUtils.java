package com.zimu.javacore.http;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;


/** 
 * @title 一个简单的http工具类 后面要抽象和封装 
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:20
 * @version 1.0
 */
public class HttpUtils {
	
	public static String sendPost(String path,String params) throws UnsupportedEncodingException, IOException{
        URL url =new URL(path);
        HttpURLConnection conn = (HttpsURLConnection)url.openConnection();
        buildHttpsURLConnection((HttpsURLConnection)conn);
        conn.setRequestMethod(HttpMethod.POST);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586");
        conn.setConnectTimeout(5*1000);
        conn.setReadTimeout(5*1000);
		DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
        outStream.write(params.getBytes());
        outStream.flush();
        outStream.close();
        System.out.println(conn.getResponseCode()); //响应代码 200表示成功
        String result ="";
        if(conn.getResponseCode()==200){
            InputStream inStream = conn.getInputStream();   
            result=new String(stream2Byte(inStream), "UTF-8");
        }
		return result;
	}

	public static byte[] stream2Byte(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		byte[] b = new byte[1024];
		while ((len = is.read(b, 0, b.length)) != -1) {
			baos.write(b, 0, len);
		}
		byte[] buffer = baos.toByteArray();
		return buffer;
	}

	public static String sendGet(String url, String params) {
		return null;
	}

	public static String sendGet(String url) {
		return null;
	}

	/**
	 * 设置https相关属性
	 * @param connection
	 */
	private static void buildHttpsURLConnection(HttpsURLConnection connection) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(new KeyManager[0],
					new TrustManager[] { new DefaultTrustManager() },
					new SecureRandom());
			SSLContext.setDefault(ctx);
			connection.setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	public static String encodeUrlProperty(String value) {
		String result = "";
		try {
			result = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(encodeUrlProperty("account=x3zMUeaBrazoJK1Iqq+zxA=="
						+ "&channelId=4C9EF1676B564240DF6AA684F968E85A&clientType=2&"
						+ "deviceNo=e205fa700a1211854044887930b5c68bf1f65c45&marketId=374&password=b9ut1UXb3kbd2Wvx1pJrpQ=="
						+ "&secret=yingonline"));
	}
}
