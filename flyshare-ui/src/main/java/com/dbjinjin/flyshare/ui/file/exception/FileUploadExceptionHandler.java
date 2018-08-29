package com.dbjinjin.flyshare.ui.file.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * <p>标题： FileUploadExceptionHandler</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月29日 下午2:35:20</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.file.exception.FileUploadExceptionHandler</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@ControllerAdvice
public class FileUploadExceptionHandler
{
	@ExceptionHandler(MultipartException.class)
	public String handleError1(MultipartException e, RedirectAttributes redirectAttributes)
	{
		String message = e.getCause().getMessage();
		int index = message.indexOf(":");
		if (index > 0)
		{
			message = message.substring(index + 1);
		}
		redirectAttributes.addFlashAttribute("message", message.trim());
		return "redirect:/file/upload.html";
	}
}
