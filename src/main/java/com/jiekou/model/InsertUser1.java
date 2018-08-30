package com.jiekou.model;

public class InsertUser1 {
	private int id;
	private String username;
	private String password;
	private int age;
	private int six;
	private int quanxian;
	private int isdelete;
	private String expected;
	private String url;
	@Override
	public String toString() {
		return "InsertUser1 [id=" + id + ", username=" + username + ", password=" + password + ", age=" + age + ", six="
				+ six + ", quanxian=" + quanxian + ", isdelete=" + isdelete + ", expected=" + expected + ", url=" + url
				+ "]";
	}
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public int getAge() {
		return age;
	}
	public int getSix() {
		return six;
	}
	public int getQuanxian() {
		return quanxian;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public String getExpected() {
		return expected;
	}
	public String getUrl() {
		return url;
	}
}
