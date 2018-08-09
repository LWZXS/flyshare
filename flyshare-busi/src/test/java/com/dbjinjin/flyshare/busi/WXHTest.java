package com.dbjinjin.flyshare.busi;

import java.util.UUID;

import com.dbjinjin.flyshare.base.util.DateUtils;
import com.dbjinjin.flyshare.busi.user.model.User;

/**
 * <p>标题： WXHTest</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月8日 下午4:35:11</p>
 * <p>类全名：com.dbjinjin.flyshare.busi.WXHTest</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
public class WXHTest
{
	public static void main(String[] args)
	{
		User user = new User();
		user.setBirthday(DateUtils.getDate("1990-06-24", DateUtils.DATE_STYLE));
		user.setEmail("dbjinjin0820@qq.com");
		user.setGender("M");
		user.setNickname("小吴同学");
		user.setPassword("123456");
		user.setUsername("root");
		System.out.println(user);
		System.out.println(UUID.randomUUID().toString().toUpperCase());
	}
}
