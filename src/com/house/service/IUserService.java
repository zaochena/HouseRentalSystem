package com.house.service;

import com.house.entity.User;

public interface IUserService {
	/**
	 *  用户登录
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
	 * @param user
	 * @return
	 */
	public User checkOldPwd(User user);
}
