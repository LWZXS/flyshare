package com.dbjinjin.flyshare.ui.user.model;

import com.dbjinjin.flyshare.base.model.BaseModel;

/**
 * <p>标题： User</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月8日 下午5:07:21</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.user.model.User</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
public class User extends BaseModel
{
	private static final long serialVersionUID = -8813251610827179947L;
	private String username;// 用户名
	private String password;// 密码
	private String nickname;// 昵称

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

}
