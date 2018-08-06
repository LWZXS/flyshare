package com.dbjinjin.flyshare.ui.test;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * <p>标题： ClientService</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月6日 下午3:51:45</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.test.ClientService</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@FeignClient(name = "FlyShare-Busi")
public interface ClientService
{
	@RequestMapping("/say")
	public String say();

	@RequestMapping("/hello")
	public String hello(@RequestParam("name") String name);

}
