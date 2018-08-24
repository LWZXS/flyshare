package com.dbjinjin.flyshare.ui.index.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>标题： IndexController</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月24日 下午3:02:59</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.index.web.IndexController</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@Controller
public class IndexController
{
	public static final String INDEX_PAGE = "index";
	public static final String LOGIN_PAGE = "login";
	public static final String REGIST_PAGE = "register";

	@RequestMapping("/")
	public String home()
	{
		return INDEX_PAGE;
	}

	@RequestMapping("/index.html")
	public String index()
	{
		return INDEX_PAGE;
	}

	@RequestMapping("/login.html")
	public String login()
	{
		return LOGIN_PAGE;
	}

	@RequestMapping("/register.html")
	public String register()
	{
		return REGIST_PAGE;
	}

}
