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
}
