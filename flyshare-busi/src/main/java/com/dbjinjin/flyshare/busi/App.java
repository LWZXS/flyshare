package com.dbjinjin.flyshare.busi;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * <p>标题： App</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月2日 下午4:42:09</p>
 * <p>类全名：com.dbjinjin.flyshare.busi.App</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
public class App
{
	public static void main(String[] args)
	{
		JSONObject json = new JSONObject();
		json.put("name", "wuxiaohai");
		System.out.println("Hello World!" + json);
	}
}
