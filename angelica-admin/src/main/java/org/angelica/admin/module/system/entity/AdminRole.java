package org.angelica.admin.module.system.entity;

import org.angelica.core.entity.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
/**
 * 角色
 * @author aizhimin
 */
public class AdminRole extends BaseEntity {
	private String roleName;//角色名称
	private String roleDesc;//角色描述
	private Integer status;//状态：1-正常，2-禁用，3-删除
	
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this);
	}
	
}
