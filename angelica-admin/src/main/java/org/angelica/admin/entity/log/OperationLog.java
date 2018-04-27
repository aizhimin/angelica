package org.angelica.admin.entity.log;

import java.util.Date;

import org.angelica.admin.common.Constant;
import org.angelica.core.entity.BaseIdEntity;
import org.angelica.core.log.LogType;
/**
 * 用户操作日志（日志可以改造为存储到mongodb或者阿里云tablestore或者阿里云日志服务上）
 * @author aizhimin
 */
public class OperationLog extends BaseIdEntity{
	private String appName;//应用名称，属于哪个应用的日志
	private Integer logType;//日志类型
	private String logName;//日志名称
	private Long userId;//用户id
	private String classname;//执行类名
	private String method;//请求方法
	private String params;//请求参数
	private String ip;//ip 地址
	private String message;//备注信息
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Integer getLogType() {
		return logType;
	}
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	 /**
     * 创建登录日志
     */
    public static OperationLog buildLoginLog(LogType logType, Long userId, String message, String ip) {
    	OperationLog operationLog = new OperationLog();
    	operationLog.setAppName(Constant.APP_NAME);
    	operationLog.setUserId(userId);
        operationLog.setLogType(logType.getCode());
        operationLog.setLogName(logType.getMessage());
        operationLog.setIp(ip);
        operationLog.setMessage(message);
        operationLog.setGmtCreate(new Date());
        return operationLog;
    }
	
	/**
     * 创建业务操作日志
     */
    public static OperationLog buildBussinessLog(LogType logType, Long userId, String logName, String classname, String methodName,String params,String ip, String message) {
        OperationLog operationLog = new OperationLog();
        operationLog.setAppName(Constant.APP_NAME);
        operationLog.setUserId(userId);
        operationLog.setLogType(logType.getCode());
        operationLog.setLogName(logName);
        operationLog.setClassname(classname);
        operationLog.setMethod(methodName);
        operationLog.setParams(params);
        operationLog.setIp(ip);
        operationLog.setMessage(message);
        operationLog.setGmtCreate(new Date());
        return operationLog;
    }
    
}
