package org.angelica.admin.entity.system;

import org.angelica.core.entity.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
/**
 * 平台资源
 * @author aizhimin
 *
 */
public class AdminResource extends BaseEntity {
	private String resourceName;//资源名称
	private Integer resourceType;//资源类型：0-模块,1-菜单,2-按钮
	private String permission;//权限字符串：(资源编码:操作编码)，如：system:user:list,system:user:create,多个逗号分隔
	private String url;//菜单url
	private Integer seq;//顺序
	private Long parentId;//父级ID
	
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
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this);
	}
	
}
