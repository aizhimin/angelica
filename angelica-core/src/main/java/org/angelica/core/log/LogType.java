package org.angelica.core.log;

/**
 * 日志类型
 */
public enum LogType {

    LOGIN(1,"登录日志"),
    LOGIN_FAIL(2,"登录失败日志"),
    EXIT(3,"退出日志"),
    EXCEPTION(4,"异常日志"),
    BUSSINESS(5,"业务日志");

	int code;
    String message;

    LogType(int code,String message) {
    	this.code = code;
        this.message = message;
    }
    
    public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
