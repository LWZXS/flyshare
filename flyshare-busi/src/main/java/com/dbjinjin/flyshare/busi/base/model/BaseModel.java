package com.dbjinjin.flyshare.busi.base.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * <p>标题： BaseModel</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月8日 下午3:32:53</p>
 * <p>类全名：com.dbjinjin.flyshare.busi.base.model.BaseModel</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
public abstract class BaseModel implements Serializable, Cloneable
{
	private static final long serialVersionUID = -8269730212130393113L;

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
