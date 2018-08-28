package com.dbjinjin.flyshare.ui.base.util;

import java.sql.SQLException;

import org.apache.http.HttpException;

import com.dbjinjin.flyshare.base.model.BaseModel;
import com.dbjinjin.flyshare.ui.base.model.Message;
import com.dbjinjin.flyshare.ui.base.pub.ErrorCodeConstant;

/**
 * <p>标题： MessageUtil</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月28日 下午3:33:01</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.base.util.MessageUtil</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
public class MessageUtil
{

	public static void buildExecMessageInfo(Message<?> message, Exception e)
	{
		messageInitCheck(message);
		if (e != null)
		{
			message.setResult(false);
			message.setMessage(e.getMessage());
			if (e instanceof IllegalArgumentException)
			{
				message.setCode(ErrorCodeConstant.PARAMS);
			} else if (e instanceof HttpException)
			{
				message.setCode(ErrorCodeConstant.NETWORK);
			} else if (e instanceof SQLException)
			{
				message.setCode(ErrorCodeConstant.DATABASE);
			} else
			{
				message.setCode(ErrorCodeConstant.UNDEFINE);
			}
		}
	}

	public static <T extends BaseModel> void buildSuccMessageInfo(Message<T> message, String info, T data)
	{

		messageInitCheck(message);
		message.setResult(true);
		message.setMessage(info);
		message.setData(data);
	}

	private static void messageInitCheck(Message<?> message)
	{
		if (message == null)
		{
			message = new Message<>();
		}
	}

}
