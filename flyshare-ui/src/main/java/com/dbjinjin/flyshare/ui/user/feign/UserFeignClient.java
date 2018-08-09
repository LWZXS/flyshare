package com.dbjinjin.flyshare.ui.user.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dbjinjin.flyshare.ui.user.feign.fallback.UserFeignClientFallback;
import com.dbjinjin.flyshare.ui.user.model.User;

/**
 * <p>标题： UserFeignClient</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月8日 下午5:11:15</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.user.feign.UserFeignClient</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@FeignClient(name = "flyshare-busi", fallback = UserFeignClientFallback.class)
public interface UserFeignClient
{

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User findById(@RequestParam("id") Long id);

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User postUser(@RequestBody User user);

}
