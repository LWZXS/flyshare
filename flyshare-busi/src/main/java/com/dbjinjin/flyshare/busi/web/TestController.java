package com.dbjinjin.flyshare.busi.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>标题： TestController</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月6日 下午3:25:28</p>
 * <p>类全名：com.dbjinjin.flyshare.busi.web.TestController</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@RestController
public class TestController
{
	@Value("${server.port}")
	private String port;

	@RequestMapping("say")
	public String say()
	{
		return "hello world";
	}

	@RequestMapping("hello")
	public String hello(@RequestParam String name)
	{
		return "hello " + name + ",port is " + port;
	}
}
