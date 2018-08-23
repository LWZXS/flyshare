package com.dbjinjin.flyshare.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * <p>标题： SpringBootFlyShareApiGateWayApplication</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月13日 下午2:46:43</p>
 * <p>类全名：com.dbjinjin.flyshare.gateway.SpringBootFlyShareApiGateWayApplication</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@SpringBootApplication
@EnableZuulProxy
public class SpringBootFlyShareApiGateWayApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootFlyShareApiGateWayApplication.class, args);
	}
}
