package org.angelica.core.response;

/**
 * 请求码
 * @author aiken
 *
 */
public enum ResponseError {
	UNKNOWN(-1,"服务器未知运行时异常"),
	TOKEN_INVALID(401,"授权失败："),
	MISSING_PARAM(1001,"缺失参数："),
	HTTP_REQUEST_METHOD_NOTSUPPORTED(1002,"HTTP请求方式错误，仅支持："),
	ERROR_PARAM(1003,"参数错误："),
	;
	
	private int code;
	private String msg;
	private ResponseError(int code,String msg) {
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
