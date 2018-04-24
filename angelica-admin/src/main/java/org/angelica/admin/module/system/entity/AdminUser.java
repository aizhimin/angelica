package org.angelica.admin.module.system.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * 管理员
 * @author aizhimin
 */
public class AdminUser {
	
	public static final int FORBIDDEN = 0;//禁用
	public static final int NORMAL = 1;//正常
	public static final int LOCKED = 2;//锁定
	
	private Long id;
	private String loginName;//用户名、登录名
	private String name;//昵称、姓名
	private String password;
	private Integer status;
	
	
	private List<AdminRole> roles = Lists.newArrayList();//角色集合
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public List<AdminRole> getRoles() {
		return roles;
	}
	public void setRoles(List<AdminRole> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, "roles");
	}
	
	/**
	 * 获取用户的权限集合
	 * @param adminUser
	 * @return
	 */
	public static Set<String> buildPermissions(AdminUser adminUser){
		Set<String> permsSet = Sets.newHashSet();
		if(adminUser != null) {
			List<AdminRole> roles = adminUser.getRoles();
			for(AdminRole ar:roles) {
				List<AdminResource> resourceList = ar.getResources();
				for(AdminResource ap:resourceList) {
					if(StringUtils.isBlank(ap.getPermission())) {
						continue;
					}
					permsSet.addAll(Arrays.asList(ap.getPermission().trim().split(",")));
				}
			}
		}
		return permsSet;
	}
}
