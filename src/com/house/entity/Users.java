package com.house.entity;

/**
 * 
 * @Description 租房用户
 */
public class Users {

	private int uid;
	private String uname;
	private String password;
	private String school;
	private int stu_number ;
	private String stu_name;
	private String telephone;
	private String email;
	private int sex;
	private String uimage;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getStu_number() {
		return stu_number;
	}

	public void setStu_number(int stu_number) {
		this.stu_number = stu_number;
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getUimage() {
		return uimage;
	}

	public void setUimage(String uimage) {
		this.uimage = uimage;
	}

//	public Users(int uID, String uName, String uPassword, String uPhoneNumber, String uNickName) {
//		super();
//		this.uID = uID;
//		this.uName = uName;
//		this.uPassword = uPassword;
//		this.uPhoneNumber = uPhoneNumber;
//		this.uNickName = uNickName;
//	}

	public Users() {
		super();
	}

//	public Users(String uName, String uPassword, String uPhoneNumber, String uNickName) {
//		super();
//		this.uName = uName;
//		this.uPassword = uPassword;
//		this.uPhoneNumber = uPhoneNumber;
//		this.uNickName = uNickName;
//	}

}
