package com.dbjinjin.flyshare.ui.test.web;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * <p>标题： TestController</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月25日 下午3:03:42</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.test.web.TestController</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@Controller
@RequestMapping("fastjson")
public class TestController
{

	@RequestMapping("/test")
	@ResponseBody
	public User test()
	{
		User user = new User();
		user.setName("wuxiaohai");
		user.setPassword("53770");
		user.setPredate(new Date());
		return user;
	}

	class User
	{
		private String name;
		private String password;
		@JSONField(format = "yyyy-MM-dd")
		private Date predate;

		public Date getPredate()
		{
			return predate;
		}

		public void setPredate(Date predate)
		{
			this.predate = predate;
		}

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public String getPassword()
		{
			return password;
		}

		public void setPassword(String password)
		{
			this.password = password;
		}

	}
}
