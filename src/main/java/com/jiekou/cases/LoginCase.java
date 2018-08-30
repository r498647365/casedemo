package com.jiekou.cases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jiekou.model.LoginUser1;
import com.my.object.User;
import com.my.unitl.Unitl;

/*
 * 这个类为登录case，不要将所有case放在一个类里，要分类，因为后面
 * 所有操作都是基于这个login的，所以先初始化
 */
public class LoginCase {
	private LoginUser1 loginUser1;
	public CookieStore cookiestore;
	public User selectOne2;
	public String url;
	@BeforeTest
	public void before() throws IOException {
		//加载数据库对象
		SqlSession sqlsession = Unitl.getSqlsession();
		//得到url地址和参数
		loginUser1 = sqlsession.selectOne("UserList",1);
		url=loginUser1.getUrl();
	}
	
	//初始化完成，开始进行请求
	@Test(groups= {"logintrue"})
	public void getlogin() throws ParseException, IOException {
		//第一步，使用Gson将获取的 loginuser类转换为字符串
		Gson gson=new Gson();
		String json = gson.toJson(loginUser1);
		//发送请求并得到返回结果
		HttpResponse postRequest = Unitl.PostRequest(url, json);
		String result=EntityUtils.toString(postRequest.getEntity(),"utf-8");
		//第二步，验证结果
		
		AssertJUnit.assertEquals(postRequest.getStatusLine().getStatusCode(), 200);
		AssertJUnit.assertEquals(result, loginUser1.getexpected());
		System.out.println("测试完成");
		
	}
}
