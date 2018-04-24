package org.angelica.core.response;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseResult extends LinkedHashMap<String, Object>{
	private static final long serialVersionUID = 1L;
	
	private static final int SUCCESS_CODE = 0;
	private static final String SUCCESS_MSG = "success";
	
	public ResponseResult() {
		put("code", SUCCESS_CODE);
		put("msg", SUCCESS_MSG);
	}
	
	public static ResponseResult error(int code, String msg) {
		ResponseResult r = new ResponseResult();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}
	
	public static ResponseResult error(ResponseError error) {
		ResponseResult r = new ResponseResult();
		r.put("code", error.getCode());
		r.put("msg", error.getMsg());
		return r;
	}
	
	public static ResponseResult error(ResponseError error,String msg) {
		ResponseResult r = new ResponseResult();
		r.put("code", error.getCode());
		r.put("msg", error.getMsg()+" : "+msg);
		return r;
	}
	
	public static ResponseResult success(String msg) {
		ResponseResult r = new ResponseResult();
		r.put("msg", msg);
		return r;
	}
	
	public static ResponseResult success(Map<String, Object> map) {
		ResponseResult r = new ResponseResult();
		r.putAll(map);
		return r;
	}
	
	public static ResponseResult success(String key, Object value) {
		ResponseResult r = new ResponseResult();
		r.put(key, value);
		return r;
	}
	
	public static ResponseResult success() {
		return new ResponseResult();
	}

	public ResponseResult put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
}
