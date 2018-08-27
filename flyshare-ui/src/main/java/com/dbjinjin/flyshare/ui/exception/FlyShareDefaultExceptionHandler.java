package com.dbjinjin.flyshare.ui.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>标题： FlyShareDefaultExceptionHandler</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月25日 下午1:53:49</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.exception.FlyShareDefaultExceptionHandler</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@ControllerAdvice
public class FlyShareDefaultExceptionHandler
{
	/**
	* 处理 Exception 类型的异常
	* @param e
	* @return
	*/
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String, Object> defaultExceptionHandler(Exception e)
	{

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 500);
		map.put("msg", e.getMessage());
		return map;
	}
}
