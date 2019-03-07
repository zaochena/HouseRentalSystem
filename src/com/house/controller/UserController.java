package com.house.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.house.entity.House;
import com.house.entity.User;
import com.house.service.IHouserService;
import com.house.service.IUserService;

@Controller
public class UserController {
	
	@Autowired
	private IUserService service;
	@Autowired
	private IHouserService dao;
	
	@RequestMapping("/toUserSystem")
	public String toUserSystemPage() {
		return "customer.jsp";
	}
	
	@RequestMapping("/toUserRentalPage")
	public String toUserRentalPage() {
		return "myrental.jsp";
	}
	
	@RequestMapping("/welcome")
	public String toWelcomePage() {
		return "welcome.jsp";
	}
	
	@RequestMapping("/toUpdateHousePage")
	public String toUpdatePage(int hID,HttpServletRequest request) {
		House house = dao.findHouseDetailsById(hID);
		request.getSession().setAttribute("House", house);
		return "updatehouse.jsp";
	}
	
	@RequestMapping("/updateUserPwd")
	@ResponseBody
	public String updateUserPwd(String id,String newPwd,String oldPwd) {
		User oldUser = new User();
		oldUser.setUid(Integer.parseInt(id));
		oldUser.setPassword(oldPwd);
		User checkUser = service.checkOldPwd(oldUser);
		if(checkUser!=null) {
			User newUser = new User();
			newUser.setUid(Integer.parseInt(id));
			newUser.setPassword(newPwd);
			int n = service.updateUserPwd(newUser);
			if(n>0) {
				return "OK";
			}
		}
		return "FAIL";
	}
}
