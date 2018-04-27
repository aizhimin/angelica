package org.angelica.admin.common;

import java.util.List;

import org.angelica.admin.common.shiro.ShiroUtils;
import org.angelica.admin.service.ErrorCode;
import org.angelica.admin.service.ServiceException;
import org.angelica.admin.service.log.impl.LogTaskFactory;
import org.angelica.core.log.LogManager;
import org.angelica.core.response.ResponseError;
import org.angelica.core.response.ResponseResult;
import org.angelica.core.utils.ToolUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 * @author aiken
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 服务器未知运行时异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseResult handle(Exception e) {
		logger.error(ToolUtil.getExceptionMsg(e));
		return ResponseResult.error(ResponseError.UNKNOWN);
	}
	/**
	 * 业务异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = ServiceException.class)
	public ResponseResult handle(ServiceException e) {
		logger.error(ToolUtil.getExceptionMsg(e));
		LogManager.getInstance().executeLog(LogTaskFactory.exceptionLog(ShiroUtils.getUserId(), e));
		return ResponseResult.error(e.getCode(), e.getMessage());
	}
	
	/**
	 * 授权失败
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = AuthenticationException.class)
	public ResponseResult handle(AuthenticationException e) {
		return ResponseResult.error(ResponseError.TOKEN_INVALID,e.getMessage());
	}
	
	/**
	 * 账号被锁定
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = LockedAccountException.class)
	public ResponseResult handle(LockedAccountException e) {
		return ResponseResult.error(ErrorCode.ACCOUNT_LOCKED.getCode(), ErrorCode.ACCOUNT_LOCKED.getMsg());
	}
	
	/**
	 * 账号被禁用
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = DisabledAccountException.class)
	public ResponseResult handle(DisabledAccountException e) {
		return ResponseResult.error(ErrorCode.ACCOUNT_DISABLED.getCode(), ErrorCode.ACCOUNT_DISABLED.getMsg());
	}
	
	
	/**
	 * 请求缺少参数
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ResponseResult handle(MissingServletRequestParameterException e) {
		MissingServletRequestParameterException exception = (MissingServletRequestParameterException)e;
		return ResponseResult.error(ResponseError.MISSING_PARAM.getCode(), ResponseError.MISSING_PARAM.getMsg() + exception.getParameterName());
	}
	
	/**
	 * HTTP请求方式错误
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ResponseResult handle(HttpRequestMethodNotSupportedException e) {
		HttpRequestMethodNotSupportedException exception = (HttpRequestMethodNotSupportedException)e;
		return ResponseResult.error(ResponseError.HTTP_REQUEST_METHOD_NOTSUPPORTED.getCode(), ResponseError.HTTP_REQUEST_METHOD_NOTSUPPORTED.getMsg() + exception.getSupportedHttpMethods());
	}
	/**
	 * Valid校验参数的报错
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = BindException.class)
	public ResponseResult handle(BindException e) {
		BindException exception = (BindException)e;
		List<FieldError> errors = exception.getFieldErrors();
		StringBuffer sbf = new StringBuffer();
		for(FieldError fe:errors) {
			sbf.append(fe.getField()+fe.getDefaultMessage()+",");
		}
		if(sbf.length()>0)
			sbf.deleteCharAt(sbf.length()-1);
		return ResponseResult.error(ResponseError.ERROR_PARAM.getCode(), ResponseError.ERROR_PARAM.getMsg()+sbf.toString());
	}
}
