package com.house.service.impl;

import com.house.dao.CourseMapper;
import com.house.entity.Course;
import com.house.entity.CourseSelected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.dao.UserMapper;
import com.house.entity.User;
import com.house.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private CourseMapper Cmapper;

	@Autowired
	private UserMapper mapper;
	@Override
	public CourseSelected[] getInfo(){
		return Cmapper.selectInfo();
	}
	@Override
	public Course selectCourseName(Course course){
		return Cmapper.selectCourseName(course);
	}
	@Override
	public User login(User user) {
		return mapper.login(user);
	}

	@Override
	public int regist(User user) {
		return mapper.regist(user);
	}

	@Override
	public int updateUserPwd(User user) {
		return mapper.updateUserPwd(user);
	}

	@Override
	public User checkOldPwd(User user) {
		return mapper.checkOldPwd(user);
	}
	@Override
	public int validationInfo(User user){
		return mapper.validationInfo(user);
	}
}
