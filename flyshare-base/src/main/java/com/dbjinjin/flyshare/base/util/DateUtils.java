package com.dbjinjin.flyshare.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>标题： DateUtils</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月8日 上午10:54:49</p>
 * <p>类全名：com.dbjinjin.flyshare.base.util.DateUtils</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
public class DateUtils
{
	public static final String DATE_STYLE = "yyyy-MM-dd";

	public static Date getServerDate()
	{
		return new Date();
	}

	public static Date getDate(String source, String pattern)
	{
		try
		{
			return new SimpleDateFormat(pattern).parse(source);
		} catch (ParseException e)
		{
			throw new IllegalArgumentException("日期格式错误,不符合规则：" + pattern + "~!");
		}
	}
}
