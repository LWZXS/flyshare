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
	private String path;

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public FileInfo(String path)
	{
	}

}
