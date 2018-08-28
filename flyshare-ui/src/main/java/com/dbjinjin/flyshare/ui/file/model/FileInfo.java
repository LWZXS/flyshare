package com.dbjinjin.flyshare.ui.file.model;

import com.dbjinjin.flyshare.base.model.BaseModel;

/**
 * <p>标题： FileInfo</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月25日 下午3:12:51</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.file.model.FileInfo</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
public class FileInfo extends BaseModel
{
	private static final long serialVersionUID = 3009460466742939602L;

	// 文件名称
	private String name;
	// 路径
	private String path;
	// 文件大小
	private long size;
	// 上传时间
	private long time;

	// 下载URL
	private String url;

	public FileInfo()
	{
	}

	/**
	 * @param name
	 * @param path
	 * @param size
	 * @param time
	 */
	public FileInfo(String name, String path, long size, long time)
	{
		super();
		this.name = name;
		this.path = path;
		this.size = size;
		this.time = time;
	}

	public long getSize()
	{
		return size;
	}

	public void setSize(long size)
	{
		this.size = size;
	}

	public long getTime()
	{
		return time;
	}

	public void setTime(long time)
	{
		this.time = time;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

}
