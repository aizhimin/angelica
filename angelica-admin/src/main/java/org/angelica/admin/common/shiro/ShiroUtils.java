package org.angelica.admin.common.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 * 
 */
public class ShiroUtils {

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static ShiroUser getShiroUser() {
		return (ShiroUser)SecurityUtils.getSubject().getPrincipal();
	}

	public static Long getUserId() {
		return getShiroUser().getUserId();
	}
	
	public static String getLoginName() {
		return getShiroUser().getLoginName();
	}
	
	public static String getName() {
		return getShiroUser().getName();
	}
	
	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

}
