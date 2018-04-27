package org.angelica.admin.service.log.impl;

import java.util.TimerTask;

import org.angelica.admin.entity.log.OperationLog;
import org.angelica.admin.service.log.OperationLogService;
import org.angelica.core.log.LogType;
import org.angelica.core.utils.SpringContextUtil;
import org.angelica.core.utils.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志操作任务创建工厂
 */
public class LogTaskFactory {

    private static Logger logger = LoggerFactory.getLogger(LogTaskFactory.class);
    
    private static OperationLogService operationLogService = SpringContextUtil.getBean(OperationLogService.class);

    public static TimerTask loginLog(final Long userId, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                	OperationLog loginLog = OperationLog.buildLoginLog(LogType.LOGIN, userId, null, ip);
                    operationLogService.saveOperationLog(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录日志异常!", e);
                }
            }
        };
    }

    public static TimerTask loginLog(final String username, final String msg, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
            	OperationLog loginLog = OperationLog.buildLoginLog(
                        LogType.LOGIN_FAIL, null, "账号:" + username + "," + msg, ip);
                try {
                	operationLogService.saveOperationLog(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录失败异常!", e);
                }
            }
        };
    }

    public static TimerTask exitLog(final Long userId, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
            	OperationLog loginLog = OperationLog.buildLoginLog(LogType.EXIT, userId, null,ip);
                try {
                	operationLogService.saveOperationLog(loginLog);
                } catch (Exception e) {
                    logger.error("创建退出日志异常!", e);
                }
            }
        };
    }

    public static TimerTask bussinessLog(final Long userId, final String bussinessName, final String classname, final String methodName,final String params,final String ip, final String message) {
        return new TimerTask() {
            @Override
            public void run() {
                OperationLog operationLog = OperationLog.buildBussinessLog(
                        LogType.BUSSINESS, userId, bussinessName, classname, methodName,params,ip, message);
                try {
                	operationLogService.saveOperationLog(operationLog);
                } catch (Exception e) {
                    logger.error("创建业务日志异常!", e);
                }
            }
        };
    }

    public static TimerTask exceptionLog(final Long userId, final Exception exception) {
        return new TimerTask() {
            @Override
            public void run() {
                String msg = ToolUtil.getExceptionMsg(exception);
                OperationLog operationLog = OperationLog.buildBussinessLog(
                        LogType.EXCEPTION, userId, "", null, null,null,null, msg);
                try {
                	operationLogService.saveOperationLog(operationLog);
                } catch (Exception e) {
                    logger.error("创建异常日志异常!", e);
                }
            }
        };
    }
}
