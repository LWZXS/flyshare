package com.dbjinjin.flyshare.ui.test;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * <p>标题： TestController</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月6日 下午3:51:50</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.test.TestController</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@RestController
public class TestController
{
	@Resource
	private ClientService clientService;

	@RequestMapping("index")
	public String index()
	{
		String jack = clientService.say();
		return jack;
	}

	@RequestMapping("test/{name}")
	public String test(@PathVariable String name)
	{
		String hello = clientService.hello(name);
		return hello;
	}

}
