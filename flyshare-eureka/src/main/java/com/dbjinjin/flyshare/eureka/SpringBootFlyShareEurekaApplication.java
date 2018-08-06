package com.dbjinjin.flyshare.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * <p>标题： SpringBootFlyShareEurekaApplication</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月6日 上午10:17:15</p>
 * <p>类全名：com.dbjinjin.flyshare.eureka.SpringBootFlyShareEurekaApplication</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringBootFlyShareEurekaApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootFlyShareEurekaApplication.class, args);
	}
}
