package com.hhy.wj.controller;

import com.hhy.wj.pojo.User;
import com.hhy.wj.result.Result;
import com.hhy.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;


@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@CrossOrigin
	@PostMapping(value = "api/login")
	@ResponseBody
	public Result login(@RequestBody User requestUser){
		// 对html标签进行转义，防止XSS攻击
		String username = requestUser.getUsername();
		username = HtmlUtils.htmlEscape(username);
		
		User user = userService.get(username, requestUser.getPassword());
		if(null == user){
			return new Result(400);
		}else{
			return new Result(200);
		}
	}
}
