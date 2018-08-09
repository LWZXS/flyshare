package com.dbjinjin.flyshare.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * <p>标题： SpringBootFlyShareUIApplication</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月6日 下午3:49:53</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.SpringBootFlyShareUIApplication</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient // 启用服务注册于发现
@EnableFeignClients // 启用Feign远程调用
@EnableCircuitBreaker	//熔断器
@EnableHystrixDashboard //熔断器监控
public class SpringBootFlyShareUIApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootFlyShareUIApplication.class, args);
	}
}
