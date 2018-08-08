package com.dbjinjin.flyshare.ui.user.web;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dbjinjin.flyshare.ui.user.feign.UserFeignClient;
import com.dbjinjin.flyshare.ui.user.model.User;

/**
 * <p>标题： UserController</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月8日 下午5:14:02</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.user.web.UserController</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@RestController
public class UserController
{
	@Autowired
	private UserFeignClient userFeignClient;

	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id)
	{
		return userFeignClient.findById(id);
	}

	@GetMapping("/movie/user")
	public User postUser(User user)
	{
		Random random = new Random();
		long id = (long) random.nextInt(1000);
		User tmpUser = new User();
		tmpUser.setUsername("TempUser" + id);
		tmpUser.setPassword("Password" + id);
		tmpUser.setNickname("Nick" + id);
		return userFeignClient.postUser(tmpUser);
	}
}
