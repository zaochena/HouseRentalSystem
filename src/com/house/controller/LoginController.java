package com.house.controller;

import com.house.entity.User;
import com.house.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	private IUserService mapper;

	@RequestMapping("/login")
	@ResponseBody
	public String toCustomerPage(String username,String password,HttpServletRequest req ) {
		User user = new User();
		user.setUname(username);
		user.setPassword(password);
		User loginUser = mapper.login(user);
		if(loginUser!=null) {
			req.getSession().setAttribute("loginUser", loginUser);
			return "OK";
		}
		return "FAIL";

	}
	
	@RequestMapping("/signout")
	public String signout(HttpSession session) {
		session.invalidate();
		return "redirect:toIndexPage";
	}
	
	@RequestMapping("/regist")
	@ResponseBody
	public String regist(User user) {
		int regist;
		try {
			regist = mapper.regist(user);
			if(regist>0) {
				return "OK";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "FAIL";
	}
	
}
