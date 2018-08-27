package com.dbjinjin.flyshare.ui.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * <p>标题： TimeLogAspect</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月25日 下午3:01:02</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.log.TimeLogAspect</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@Aspect
@Component
public class TimeLogAspect
{
	@Around("execution(* com.dbjinjin.flyshare.ui.*.web..*(..))")
	public Object method(ProceedingJoinPoint pjp) throws Throwable
	{
		System.out.println("=====Aspect处理=======");
		Object[] args = pjp.getArgs();
		for (Object arg : args)
		{
			System.out.println("参数为:" + arg);
		}
		long start = System.currentTimeMillis();
		Object object = pjp.proceed();
		System.out.println("Aspect 耗时:" + (System.currentTimeMillis() - start));
		return object;
	}
}
