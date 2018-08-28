package com.dbjinjin.flyshare.ui.base.model;

import java.io.Serializable;

import com.dbjinjin.flyshare.base.model.BaseModel;

/**
 * 
 * <p>标题： Message</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月28日 下午3:10:38</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.base.model.Message</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
public class Message<T extends BaseModel> implements Serializable
{
	private static final long serialVersionUID = 3467503218742678868L;
	// 是否处理成功
	private boolean result;
	// 处理信息
	private String message;
	// 异常编码
	private String code;
	// 数据
	private T data;

	public Message()
	{
		this(true, null, null, null);
	}

	public Message(boolean result, String message, String code, T data)
	{
		super();
		this.result = result;
		this.message = message;
		this.code = code;
		this.data = data;
	}

	public boolean isSucc()
	{
		return result;
	}

	public boolean isFail()
	{
		return !isSucc();
	}

	public void setResult(boolean result)
	{
		this.result = result;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		this.data = data;
	}

}
