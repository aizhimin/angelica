package org.angelica.admin.service;

public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = -6187278601290069774L;
	private int code;
	
	public ServiceException(int code,String message) {
		super(message);
		this.code = code;
	}
	
	public ServiceException(ErrorCode errorCode) {
		super(errorCode.getMsg());
		this.code = errorCode.getCode();
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
