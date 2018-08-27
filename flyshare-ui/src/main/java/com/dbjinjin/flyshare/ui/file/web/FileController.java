package com.dbjinjin.flyshare.ui.file.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.dbjinjin.flyshare.ui.file.model.FileInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

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
@RequestMapping("file")
@Api(value = "File测试", tags = { "测试接口" })
public class FileController
{
	private String path = "D:\\wxh";

	@ApiOperation("上传文件")
    @ApiImplicitParam(name = "file", value = "用户名", dataType = "MultipartFile", paramType = "query")
	@PostMapping("/upload")
	public FileInfo upload(MultipartFile file) throws Exception
	{
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		File localFile = new File(path, file.getOriginalFilename());
		file.transferTo(localFile);
		return new FileInfo(localFile.getAbsolutePath());
	}

	@ApiOperation("上传下载")
    @ApiImplicitParam(name = "id", value = "文件ID", dataType = "String", paramType = "query")
	@GetMapping("/download/{id}")
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			InputStream inputStream = new FileInputStream(new File(path, id + ".jpg"));
			OutputStream outputStream = response.getOutputStream();
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=" + id + ".jpg");
			IOUtils.copy(inputStream, outputStream);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
