package com.flyshare.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import com.dbjinjin.consumer.SpringCloudConsumerApplication;

/**
 * <p>标题： SpringBootFlyShareWebApplication</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月2日 下午6:11:56</p>
 * <p>类全名：com.flyshare.web.SpringBootFlyShareWebApplication</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient // Eureka
@EnableFeignClients
@EnableHystrixDashboard // 熔断器监控
@EnableCircuitBreaker
public class SpringBootFlyShareWebApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootFlyShareWebApplication.class, args);
	}
}
