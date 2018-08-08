package com.dbjinjin.flyshare.busi.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.dbjinjin.flyshare.busi.base.model.BaseModel;

/**
 * <p>标题： User</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月7日 下午4:32:41</p>
 * <p>类全名：com.dbjinjin.flyshare.busi.user.model.User</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@Entity
public class User extends BaseModel
{
	private static final long serialVersionUID = 893123116823103886L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;// ID

	@Column
	private String username;// 用户名

	@Column
	private String password;// 密码

	@Column
	private String nickname;// 昵称

	@Column
	private String gender;// 性别

	@Column
	private Date birthday;// 出生日期

	@Column
	private String email;// 邮箱

	@Column
	private Date predate;// 创建时间

	@Column
	private Date modifydate;// 修改时间

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

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Date getPredate()
	{
		return predate;
	}

	public void setPredate(Date predate)
	{
		this.predate = predate;
	}

	public Date getModifydate()
	{
		return modifydate;
	}

	public void setModifydate(Date modifydate)
	{
		this.modifydate = modifydate;
	}
}
