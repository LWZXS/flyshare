package com.dbjinjin.flyshare.busi.aop.log;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * <p>标题： WebControllerLogAspect</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月10日 上午10:11:20</p>
 * <p>类全名：com.dbjinjin.flyshare.busi.aop.log.WebControllerLogAspect</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@Aspect
@Order(1)
@Component
public class WebControllerLogAspect
{

	private Logger logger = Logger.getLogger(getClass());

	ThreadLocal<Long> startTime = new ThreadLocal<>();

	private static final String PRE_TAG = "************** ";
	private static final String PRE_TAG_HEAD = "=============================日志开始=============================== ";
	private static final String PRE_TAG_FOOT = "=============================日志结束=============================== ";

	@Pointcut("execution(public * com.dbjinjin.flyshare.busi.*.web..*.*(..))")
	public void webLog()
	{
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable
	{
		startTime.set(System.currentTimeMillis());

		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
		// 记录下请求内容
		logger.info(PRE_TAG_HEAD);
		logger.info(PRE_TAG + "URL: " + request.getRequestURL().toString());
		logger.info(PRE_TAG + "方法: " + request.getMethod());
		logger.info(PRE_TAG + "IP: " + request.getRemoteAddr());
		logger.info(PRE_TAG + "调用方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		logger.info(PRE_TAG + "参数名: " + Arrays.toString(paramNames));
		logger.info(PRE_TAG + "参数值: " + Arrays.toString(joinPoint.getArgs()));

	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable
	{
		// 处理完请求，返回内容
		logger.info(PRE_TAG + "响应内容 : " + ret);
		logger.info(PRE_TAG + "调用时长 : " + (System.currentTimeMillis() - startTime.get()) + "ms");
		logger.info(PRE_TAG_FOOT);
	}
}
