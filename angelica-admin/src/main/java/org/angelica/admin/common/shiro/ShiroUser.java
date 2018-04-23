package org.angelica.admin.common.shiro;

import java.io.Serializable;

public class ShiroUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String loginName;
	private String name;
	
	public ShiroUser(Long userId,String loginName,String name) {
		this.userId = userId;
		this.loginName = loginName;
		this.name = name;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
