package com.dbjinjin.flyshare.ui.file.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dbjinjin.flyshare.base.common.SysConstant;
import com.dbjinjin.flyshare.base.util.FileUtil;
import com.dbjinjin.flyshare.base.util.StrUtils;
import com.dbjinjin.flyshare.ui.base.model.Message;
import com.dbjinjin.flyshare.ui.base.util.MessageUtil;
import com.dbjinjin.flyshare.ui.file.model.FileUpload;
import com.dbjinjin.flyshare.ui.file.model.FileUploadResponseInfo;
import com.dbjinjin.flyshare.ui.file.repository.FileUploadRepository;

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

	@Autowired
	private FileUploadRepository fileUploadRepository;

	@RequestMapping("/upload.html")
	public String index()
	{
		return "file/upload";
	}

	@PostMapping("/upload")
	@ResponseBody
	public Message<FileUpload> upload(@RequestParam("upload-file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Message<FileUpload> message = new Message<>();
		try
		{
			request.setCharacterEncoding(SysConstant.DEFAULT_CODE);
			response.setCharacterEncoding(SysConstant.DEFAULT_CODE);
			String realPath = request.getServletContext().getRealPath(path);
			String fileName = file.getOriginalFilename();
			fileName = URLEncoder.encode(fileName, SysConstant.DEFAULT_CODE);
			// 检测上传附件路径是否存在 不存在则创建
			checkSavePathExists(realPath);
			File localFile = new File(realPath, fileName);
			// 文件存储
			file.transferTo(localFile);
			String linkUrl = buildUploadFileLinkUrl(request, fileName);
			FileUpload fileUpload = new FileUpload(fileName, localFile.getPath(), linkUrl);
			fileUploadRepository.save(fileUpload);
			MessageUtil.buildSuccMessageInfo(message, "附件上传成功", fileUpload);
		} catch (Exception e)
		{
			MessageUtil.buildExecMessageInfo(message, e);
		}
		return message;
	}

	private String buildUploadFileLinkUrl(HttpServletRequest request, String fileName)
	{
		String ipAddress = request.getLocalAddr();
		int port = request.getLocalPort();
		String path = request.getServletPath();
		return "http://" + ipAddress + ":" + port + (StrUtils.isNull(path) ? "/" + path : "") + "/file/download?filename=" + fileName;
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
		Message<FileUploadResponseInfo> message = new Message<>();
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try
		{
			request.setCharacterEncoding(SysConstant.DEFAULT_CODE);
			response.setCharacterEncoding(SysConstant.DEFAULT_CODE);
			String realPath = request.getServletContext().getRealPath(path);
			// 下载文件名称
			String fileName = request.getParameter("filename");
			// 是否是下载模式 (默认在线预览)
			boolean downloadModel = StrUtils.obj2bool(request.getParameter("download"), true);
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
			String disposition = downloadModel ? ("attachment;filename=" + fileName) : ("inline;filename=" + fileName);
			String contentType = downloadModel ? "application/x-download" : StrUtils.obj2str(FileUtil.getContentType(file), "application/x-download");
			inputStream = new FileInputStream(file);
			outputStream = response.getOutputStream();
			response.setContentType(contentType);
			response.addHeader("Content-Disposition", disposition);
			IOUtils.copy(inputStream, outputStream);
			MessageUtil.buildSuccMessageInfo(message, "附件下载成功", null);
		} catch (Exception e)
		{
			MessageUtil.buildExecMessageInfo(message, e);
		} finally
		{
			try
			{
				if (outputStream != null)
				{
					outputStream.flush();
					outputStream.close();
				}
				if (inputStream != null)
				{
					inputStream.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return message;
	}
}
