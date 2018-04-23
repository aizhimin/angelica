package org.angelica.admin.common;

/**
 * 常量类
 */
public class Constant {
	
	public static final String APP_NAME = "qingdanxia-admin";//应用名称，唯一标识
	
	public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";//jwt 密钥
	public static final String JWT_ISUSER = "admin.qingdanxia.com";//发布者
	public static final int JWT_TTL = 60*60*1000;  //jwt token 有效时长1小时 
	public static final int JWT_REFRESH_TTL = 7*24*60*60*1000;  //有效时长7天小时
	
	public static final String HEADER_AUTH = "Authorization";//权限字符串报文头
	
	public static final String SEPARATOR = ",";

}
