package com.dbjinjin.flyshare.busi.user.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbjinjin.flyshare.busi.user.model.User;
import com.dbjinjin.flyshare.busi.user.service.UserService;

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
public class UserController
{
	@Autowired
	private UserService userRepository;

	@GetMapping("/user/list")
	public List<User> findUserList()
	{
		return this.userRepository.findAll();
	}
}
