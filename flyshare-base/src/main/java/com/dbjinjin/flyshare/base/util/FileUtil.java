package com.dbjinjin.flyshare.base.util;

import java.io.File;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月30日 上午9:48:15</p>
 * <p>类全名：com.dbjinjin.flyshare.base.util.FileUtil</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
public class FileUtil
{

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static String getContentType(File file)
	{
		if (file == null)
		{
			return null;
		}
		String fileName = file.getName();
		int index = fileName.lastIndexOf(".");
		if (index == -1)
		{
			return null;
		}
		String fileExtension = fileName.substring(index);
		if (StrUtils.isNull(fileExtension))
		{
			return null;
		}
		if (".bmp".equalsIgnoreCase(fileExtension))
		{
			return "image/bmp";
		}
		if (".gif".equalsIgnoreCase(fileExtension))
		{
			return "image/gif";
		}
		if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension))
		{
			return "image/jpeg";
		}
		if (".html".equalsIgnoreCase(fileExtension))
		{
			return "text/html";
		}
		if (".txt".equalsIgnoreCase(fileExtension))
		{
			return "text/plain";
		}
		if (".vsd".equalsIgnoreCase(fileExtension))
		{
			return "application/vnd.visio";
		}
		if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension))
		{
			return "application/vnd.ms-powerpoint";
		}
		if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension))
		{
			return "application/msword";
		}
		if (".xml".equalsIgnoreCase(fileExtension))
		{
			return "text/xml";
		}
		return null;
	}
}
