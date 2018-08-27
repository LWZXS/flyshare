package com.dbjinjin.flyshare.ui.index.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dbjinjin.flyshare.ui.index.common.IndexConstant;

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

	@RequestMapping("/")
	public String home()
	{
		return IndexConstant.INDEX_PAGE;
	}

	@RequestMapping("/index.html")
	public String index()
	{
		return IndexConstant.INDEX_PAGE;
	}

	@RequestMapping("/login.html")
	public String login()
	{
		return IndexConstant.LOGIN_PAGE;
	}

	@RequestMapping("/register.html")
	public String register()
	{
		return IndexConstant.REGIST_PAGE;
	}

	@RequestMapping("/origin-apply.html")
	public String originApply()
	{
		return IndexConstant.ORIGIN_APPLY_PAGE;
	}

	@RequestMapping("/origin-query.html")
	public String originQuery()
	{
		return IndexConstant.ORIGIN_QUERY_PAGE;
	}

}
