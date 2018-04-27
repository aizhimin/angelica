package org.angelica.admin.common.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.angelica.admin.common.annotation.OperationLog;
import org.angelica.admin.common.shiro.ShiroUser;
import org.angelica.admin.common.shiro.ShiroUtils;
import org.angelica.admin.service.log.impl.LogTaskFactory;
import org.angelica.core.log.LogManager;
import org.angelica.core.utils.HttpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



/**
 * 操作日志：记录登录用户的操作行为日志
 * @author aizhimin
 *
 */
@Aspect
@Component
public class OperationLogAspect {
	private static Logger logger = LoggerFactory.getLogger(OperationLogAspect.class);
	
	@Pointcut("@annotation(org.angelica.admin.common.annotation.OperationLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object result = point.proceed();
		//保存日志
		try {
			saveOperationLog(point);
		} catch (Exception e) {
			logger.error("日志记录出错!", e);
        }
		return result;
	}

	private void saveOperationLog(ProceedingJoinPoint joinPoint) {
		//未登录，不记录日志
		ShiroUser shiroUser = ShiroUtils.getShiroUser();
		if(shiroUser == null) {
			return;
		}
		
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		
		//请求的类名和方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		
		//获取操作名称：具体日志名称
		OperationLog ol = method.getAnnotation(OperationLog.class);
		String logName = ol.value();

		//获取request
		HttpServletRequest request = HttpUtil.getHttpServletRequest();
		//设置IP地址
		String ip = HttpUtil.getIpAddr(request);
		//获取请求参数
		String params = HttpUtil.getRequestParameters(request);
		
		//对于修改操作，将来实现记录操作前后的值
        String message = null;
       
		//保存操作日志
		LogManager.getInstance().executeLog(LogTaskFactory.bussinessLog(shiroUser.getUserId(), logName, className, methodName,params,ip, message));
	}
}
