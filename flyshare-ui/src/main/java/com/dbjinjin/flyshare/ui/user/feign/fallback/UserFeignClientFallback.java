package com.dbjinjin.flyshare.ui.user.feign.fallback;

import org.springframework.stereotype.Component;

import com.dbjinjin.flyshare.ui.user.feign.UserFeignClient;
import com.dbjinjin.flyshare.ui.user.model.User;

/**
 * <p>标题： UserFeignClientFallback</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月9日 上午9:58:23</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.user.feign.fallback.UserFeignClientFallback</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@Component
public class UserFeignClientFallback implements UserFeignClient
{

	@Override
	public User findById(Long id)
	{
		return new User();
	}

	@Override
	public User postUser(User user)
	{
		return new User();
	}

}
