package com.hhy.wj.controller;

import com.hhy.wj.pojo.User;
import com.hhy.wj.result.Result;
import com.hhy.wj.result.ResultFactory;
import com.hhy.wj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;




@RestController
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@CrossOrigin
	@PostMapping(value = "api/login")
	public Result login(@RequestBody User requestUser){
		// 对html标签进行转义，防止XSS攻击
		String username = requestUser.getUsername();
		username = HtmlUtils.htmlEscape(username);
		
		String password = requestUser.getPassword();
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		try {
			subject.login(usernamePasswordToken);
			return ResultFactory.buildSuccessResult(username);
		} catch (AuthenticationException e) {
			String message = "帐号或密码错误";
			return ResultFactory.buildFailResult(message);
		}
	}
	@PostMapping("api/register")
	public Result register(@RequestBody User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		username = HtmlUtils.htmlEscape(username);
		user.setUsername(username);
		
		boolean exist = userService.isExist(username);
//		System.out.println(exist);
		if(exist) {
			String message = "用户名已被使用";
			
			
			return ResultFactory.buildFailResult(message);
		}
		// 生成盐
		String salt = new SecureRandomNumberGenerator().nextBytes().toString();
		// hash次数
		int times = 2;
		// 得到 hash 后的密码
		String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
		// 将用户存储到数据库中
		user.setSalt(salt);
		user.setPassword(encodedPassword);
		userService.add(user);
		
		return ResultFactory.buildSuccessResult(user);
	}
	
	@GetMapping("api/logout")
	public Result logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		String message = "成功登出";
		return ResultFactory.buildSuccessResult(message);
	}
}
