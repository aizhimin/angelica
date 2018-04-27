package org.angelica.admin.entity.system;

import org.angelica.core.entity.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 平台管理员
 * @author aizhimin
 */
public class AdminUser extends BaseEntity {
	
	public static final int FORBIDDEN = 0;//禁用
	public static final int NORMAL = 1;//正常
	public static final int LOCKED = 2;//锁定
	
	private String loginName;//用户名、登录名
	private String name;//昵称、姓名
	private String password;
	private Integer status;
	
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
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this);
	}
	
}
