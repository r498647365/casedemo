package com.jiekou.model;

import java.io.IOException;
import java.io.Reader;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.object.User;

import lombok.Data;

@RestController
public class Mydemo {
	@Autowired
	private SqlSessionTemplate template;
	//带插入功能的接口，先不带cookie
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public int inserttest(@RequestBody User user) {
		int insert = template.insert("insert", user);
		return insert;
	}

	//上面不带cookie的插入接口成功，看来真的是环境demo环境问题，太乱了
	//开发带cookie的接口
	public int inserttest2(HttpServletRequest request,@RequestBody User user) {
		int i=0;
		for (Cookie cookie : request.getCookies()) {
			if(cookie.getName().equals("login")&&cookie.getValue().equals("true")) {
				i=template.insert("insert", user);
				return i;
			}
		}
		return i;
	}
/*
 * ok,上面的也成功了，看来真的是环境问题，这个springboot启动也很快，因为我插入的参数
 * 写成json一样的了，可以将url和参数存数据库相关接口的表里，程序去获取就行了，大周是存在properties
 * 里面的，还是不太好，等大周的做完了，自己重写一个
 */
	//开发个login的接口，对应我的测试用例
	@RequestMapping(value="/login3",method=RequestMethod.POST)
	public String getlogin(@RequestBody User user) {
		int selectOne = template.selectOne("loginjiekou", user);
		if(selectOne==1) {
			return "true";
		}
		return "false";
	}
	
}
