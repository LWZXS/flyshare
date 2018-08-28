package com.dbjinjin.flyshare.ui.file.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dbjinjin.flyshare.base.common.SysConstant;
import com.dbjinjin.flyshare.base.util.StrUtils;
import com.dbjinjin.flyshare.ui.base.model.Message;
import com.dbjinjin.flyshare.ui.base.util.MessageUtil;
import com.dbjinjin.flyshare.ui.file.model.FileInfo;

/**
 * <p>标题： FileController</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月25日 下午3:35:08</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.file.web.FileController</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@Controller
@RequestMapping("/file")
public class FileController
{
	private String path = "/upload";

	@RequestMapping("/upload.html")
	public String index()
	{
		return "file/upload";
	}

	@PostMapping("/upload")
	@ResponseBody
	public Message<FileInfo> upload(@RequestParam("upload-file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		request.setCharacterEncoding(SysConstant.DEFAULT_CODE);
		response.setCharacterEncoding(SysConstant.DEFAULT_CODE);
		Message<FileInfo> message = new Message<>();
		try
		{
			String realPath = request.getServletContext().getRealPath(path);
			String fileName = file.getOriginalFilename();
			fileName = URLEncoder.encode(fileName,SysConstant.DEFAULT_CODE);
			checkSavePathExists(realPath);
			File localFile = new File(realPath, fileName);
			long start = System.currentTimeMillis();
			file.transferTo(localFile);
			long end = System.currentTimeMillis();
			FileInfo fileInfo = new FileInfo(fileName, localFile.getAbsolutePath(), file.getSize(), end - start);
			MessageUtil.buildSuccMessageInfo(message, "附件上传成功", fileInfo);
			String pathInfo = StrUtils.isNotNull(request.getContextPath()) ? "/" + request.getContextPath() : "";
			String path = ("http://" + request.getLocalAddr() + ":" + request.getServerPort()) + pathInfo + "/file/download?filename=" + fileInfo.getName();
			fileInfo.setUrl(path);
		} catch (Exception e)
		{
			MessageUtil.buildExecMessageInfo(message, e);
		}
		return message;
	}

	private void checkSavePathExists(String realPath)
	{
		File savePath = new File(realPath);
		if (!savePath.exists())
		{
			savePath.mkdirs();
			savePath.setWritable(true);
		}
	}

	@GetMapping("/download")
	@ResponseBody
	public Message<?> download(HttpServletRequest request, HttpServletResponse response)
	{
		Message<FileInfo> message = new Message<>();
		try
		{
			request.setCharacterEncoding(SysConstant.DEFAULT_CODE);
			response.setCharacterEncoding(SysConstant.DEFAULT_CODE);
			String realPath = request.getServletContext().getRealPath(path);
			String fileName = request.getParameter("filename");
			fileName = URLEncoder.encode(fileName, SysConstant.DEFAULT_CODE);
			if (StrUtils.isNull(fileName))
			{
				throw new IllegalArgumentException("文件名称为空~!");
			}
			File file = new File(realPath + File.separator + fileName);
			if (!file.exists())
			{
				throw new IllegalArgumentException("附件不存在~!");
			}
			InputStream inputStream = new FileInputStream(file);
			OutputStream outputStream = response.getOutputStream();
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			IOUtils.copy(inputStream, outputStream);
			MessageUtil.buildSuccMessageInfo(message, "附件下载成功", null);
		} catch (Exception e)
		{
			MessageUtil.buildExecMessageInfo(message, e);
		}
		return message;
	}
}
