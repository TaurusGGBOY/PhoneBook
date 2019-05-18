package com.example.model;

public class User {

	private String id;
	private String pwd;
	private String name;
	private String tel;
	private String sex;
	private String isDelete;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpwd() {
		return pwd;
	}

	public void setpwd(String name) {
		this.pwd = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String name) {
		this.tel = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String name) {
		this.sex = name;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String name) {
		this.isDelete = name;
	}
}