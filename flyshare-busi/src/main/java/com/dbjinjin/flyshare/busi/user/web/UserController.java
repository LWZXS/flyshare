package com.dbjinjin.flyshare.busi.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbjinjin.flyshare.base.util.DateUtils;
import com.dbjinjin.flyshare.busi.user.model.User;
import com.dbjinjin.flyshare.busi.user.repository.UserRepository;

/**
 * <p>标题： UserController</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月7日 下午5:55:12</p>
 * <p>类全名：com.dbjinjin.flyshare.busi.user.web.UserController</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@RestController
@RefreshScope
public class UserController
{
	@Autowired
	private UserRepository userRepository;

	@Value("${message}")
	private String message;

	@GetMapping("/config")
	public String getConfigInfo()
	{
		return "Config Value:" + message;
	}

	@GetMapping("/user/{id}")
	public User findById(@PathVariable Long id)
	{
		return userRepository.findOne(id);
	}

	@PostMapping("/user")
	public User postUser(@RequestBody User user)
	{
		System.out.println("@GetMapping(\"user\") 接收参数对象 user: " + user);
		user.setPredate(DateUtils.getServerDate());
		return userRepository.save(user);
	}
}
