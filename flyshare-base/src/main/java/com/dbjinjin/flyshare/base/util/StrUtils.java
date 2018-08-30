package com.dbjinjin.flyshare.base.util;

/**
 * <p>标题： StrUtils</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月2日 下午6:08:01</p>
 * <p>类全名：com.flyshare.base.util.StrUtils</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
public class StrUtils
{
	/**
	 * 检查字符串是否为空
	 * @param checkStr
	 * @return
	 */
	public static boolean isNull(String checkStr)
	{
		return checkStr == null || checkStr.length() == 0;
	}

	/**
	 * 检查字符串是否非空
	 * @param checkStr
	 * @return
	 */
	public static boolean isNotNull(String checkStr)
	{
		return !isNull(checkStr);
	}

	public static boolean obj2bool(Object obj)
	{
		return obj2bool(obj, true);
	}

	public static boolean obj2bool(Object obj, boolean defaultValue)
	{
		if (obj == null)
		{
			return defaultValue;
		} else
		{
			if (obj instanceof Boolean)
			{
				return (boolean) obj;
			} else if (obj instanceof String)
			{
				String s = (String) obj;
				if (s.equalsIgnoreCase("true"))
				{
					return true;
				} else if (s.equalsIgnoreCase("false"))
				{
					return false;
				}
			}
			return defaultValue;
		}
	}

	public static String obj2str(Object obj)
	{
		return obj2str(obj, null);
	}

	public static String obj2str(Object obj, String defaultValue)
	{
		if (obj == null)
		{
			return defaultValue;
		} else
		{
			return obj.toString();
		}
	}
}
