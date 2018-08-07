package com.dbjinjin.flyshare.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * <p>标题： SpringBootFlyShareConfigApplication</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月6日 下午4:58:39</p>
 * <p>类全名：com.dbjinjin.flyshare.config.SpringBootFlyShareConfigApplication</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class SpringBootFlyShareConfigApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootFlyShareConfigApplication.class, args);
	}
}
