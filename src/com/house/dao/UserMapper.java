package com.house.dao;

import com.house.entity.User;

public interface UserMapper {
	/**
	 * 用户登录
	 * 
	 * @return
	 */
	public User login(User user);

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	public int regist(User user);
	/**
	 * 修改密码
	 * @return
	 */
	public int updateUserPwd(User user);
	/**
	 * 检查旧密码
	 * @param oldPwd
	 * @return
	 */
	public User checkOldPwd(User user);
	/*

	验证信息
	 */
	public int validationInfo(User user);
}
