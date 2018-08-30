package com.jiekou.cases;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.JsonObject;

public class Testdemo {
	public static void main(String[] args) throws UnsupportedEncodingException {
		HttpPost post=new HttpPost("http://localhost:8887/login3");
		DefaultHttpClient defaultHttpClient=new DefaultHttpClient();
		JsonObject jsonObject=new JsonObject();
		jsonObject.addProperty("username", "张三");
		jsonObject.addProperty("password", "123456");
		String aString=jsonObject.toString();
		post.setHeader("Content-Type", "application/json;charset=UTF-8");
		StringEntity stringEntity=new StringEntity(aString);
		post.setEntity(stringEntity);
		
	}
}
