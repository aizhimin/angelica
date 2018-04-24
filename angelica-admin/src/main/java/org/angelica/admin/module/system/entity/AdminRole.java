package org.angelica.admin.module.system.entity;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.common.collect.Lists;
/**
 * 角色
 * @author aizhimin
 */
public class AdminRole {
	private Long id;
	private String roleName;//角色名称
	private String roleDesc;//角色描述
	private Integer status;//状态：1-正常，2-禁用，3-删除
	
	private List<AdminResource> resources = Lists.newArrayList();//资源集合
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public List<AdminResource> getResources() {
		return resources;
	}
	public void setResources(List<AdminResource> resources) {
		this.resources = resources;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, "resources");
	}
	
}
