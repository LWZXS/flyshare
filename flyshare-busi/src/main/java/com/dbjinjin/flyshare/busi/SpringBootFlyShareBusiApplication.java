package com.dbjinjin.flyshare.busi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 * <p>标题： SpringBootFlyShareBusiApplication</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月8日 上午11:16:44</p>
 * <p>类全名：com.dbjinjin.flyshare.busi.SpringBootFlyShareBusiApplication</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootFlyShareBusiApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootFlyShareBusiApplication.class, args);
	}
}
