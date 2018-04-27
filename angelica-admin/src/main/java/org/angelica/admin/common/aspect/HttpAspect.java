package org.angelica.admin.common.aspect;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.angelica.admin.entity.system.AdminUser;
import org.angelica.core.utils.JacksonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 统一访问日志：记录所有访问日志
 * @author aiken
 */
@Aspect
@Component
public class HttpAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);
	
	@Pointcut("execution(* org.angelica.admin.controller..*.*(..))")
	public void log() {}
	
	/**
	 * 记录请求日志
	 * @param joinPoint
	 */
	@Before("log()")
	public void request(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		String ip = request.getRemoteAddr();
		String agent= request.getHeader("user-agent");//来访客户端信息
		
		String classMethod = joinPoint.getSignature().getName();
		//获取请求用户id
		Object[] args = joinPoint.getArgs();
		String currentUser = "";
		for(Object arg:args) {
			if(arg instanceof AdminUser) {
				AdminUser user = (AdminUser)arg;
				currentUser = String.valueOf(user.getId());
			}
		}
		//获取请求参数
		StringBuilder params = new StringBuilder();
		Enumeration<String> enu = request.getParameterNames();
		while(enu.hasMoreElements()){  
			String paraName = enu.nextElement(); 
			if(params.length() == 0) {
				params.append(paraName+"="+request.getParameter(paraName));
			}
			params.append("&"+paraName+"="+request.getParameter(paraName));
		}
		
		logger.info("url={},class_method={},currentUser={},args={},UserAgent={},method={},ip={}",url,classMethod,currentUser,params.toString(),agent,method,ip);
	}
	
	
	@After("log()")
	public void doAfter() {}
	
	/**
	 * 记录返回日志
	 * @param object
	 * @throws JsonProcessingException 
	 */
	@AfterReturning(returning="object",pointcut="log()")
	public void doAfterReturning(Object object) throws JsonProcessingException {
		if(object == null) {
			logger.info("response={}","空");
		}else {
			logger.info("response={}",JacksonUtil.nonDefaultMapper().toJson(object));
		}
	}
}
