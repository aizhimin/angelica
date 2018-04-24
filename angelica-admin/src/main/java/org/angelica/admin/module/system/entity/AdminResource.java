package org.angelica.admin.module.system.entity;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.collect.Lists;

public class AdminResource {
	private Long id;
	private String resourceName;//资源名称
	private Integer resourceType;//资源类型：0-模块,1-菜单,2-按钮
	private String permission;//权限字符串：(资源编码:操作编码)，如：user:list,user:create,多个逗号分隔
	private String url;//菜单url
	private Integer seq;//顺序
	private AdminResource parent;//父级资源
	private List<AdminResource> childs = Lists.newArrayList();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public Integer getResourceType() {
		return resourceType;
	}
	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@JsonBackReference
	public AdminResource getParent() {
		return parent;
	}
	public void setParent(AdminResource parent) {
		this.parent = parent;
	}
	@JsonManagedReference
	@JsonIgnore
	public List<AdminResource> getChilds() {
		return childs;
	}
	public void setChilds(List<AdminResource> childs) {
		this.childs = childs;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, "childs");
	}
	
}
