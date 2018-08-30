package com.jiekou.cases;

import java.io.IOException;

import javax.swing.text.html.parser.Entity;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.jiekou.model.InsertUser1;
import com.my.unitl.Unitl;

/*
 * 开发验证insert接口的case，只需要验证返回结果和去数据库查看就行
 */
public class InsertCase {
	private String url;
	@Test(dependsOnGroups= {"logintrue"})
	public void insert() throws IOException {
		//获得url
		InsertUser1 selectOne = Unitl.getSqlsession().selectOne("selectaddUsercase", 6);
		url=selectOne.getUrl();
		Gson gson=new Gson();
		String json = gson.toJson(selectOne);
		//发送请求
		HttpResponse postRequest = Unitl.PostRequest(url, json);
		String string = EntityUtils.toString(postRequest.getEntity(),"utf-8");
		int statusCode = postRequest.getStatusLine().getStatusCode();
		//进入数据库验证获得添加后user表的个数是否增加
		int selectOne2 = Unitl.getSqlsession().selectOne("selectUsercount");
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(string, "添加成功");
		Assert.assertEquals(selectOne2, 6);
	}
}
