package org.angelica.admin.service;

/**
 * 业务错误码
 * @author aiken
 *
 */
public enum ErrorCode {
	USER_NOT_EXIST(2001,"该用户不存在"),
	PASSWORD_ERROR(2002,"密码错误"),
	USER_EXIST(2003,"该用户已注册"),
	ACCOUNT_LOCKED(2004,"帐号已被锁定,请联系管理员"),
	ACCOUNT_DISABLED(2005,"帐号已被禁用,请联系管理员"),
	;
	
	private int code;
	private String msg;
	private ErrorCode(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}
	
}
