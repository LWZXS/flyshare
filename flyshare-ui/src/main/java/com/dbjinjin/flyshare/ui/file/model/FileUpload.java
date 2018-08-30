package com.dbjinjin.flyshare.ui.file.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.dbjinjin.flyshare.base.model.BaseModel;
import com.dbjinjin.flyshare.base.util.DateUtils;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月29日 下午2:47:07</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.file.model.FileUpload</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@Entity
@Table(name = "sys_file")
public class FileUpload extends BaseModel
{
	private static final long serialVersionUID = -905961972269270606L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JSONField(serialize=false)
	private Long id;

	@Column
	private String filename;// 文件名

	@Column
	private String filepath;// 文件路径

	@Column
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Date predate;// 上传时间

	@Column
	private String linkurl;// 下载链接

	/**
	 * @param filename
	 * @param filepath
	 */
	public FileUpload(String filename, String filepath, String linkurl)
	{
		super();
		this.filename = filename;
		this.filepath = filepath;
		this.linkurl = linkurl;
		this.predate = DateUtils.getServerDate();
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getFilepath()
	{
		return filepath;
	}

	public void setFilepath(String filepath)
	{
		this.filepath = filepath;
	}

	public Date getPredate()
	{
		return predate;
	}

	public void setPredate(Date predate)
	{
		this.predate = predate;
	}

	public String getLinkurl()
	{
		return linkurl;
	}

	public void setLinkurl(String linkurl)
	{
		this.linkurl = linkurl;
	}
}
