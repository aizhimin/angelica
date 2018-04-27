package org.angelica.core.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 实体基类：用于只有新增没有修改的表
 * 每个实体类都需要继承，每个表都要求有创建时间
 * @author aizhimin
 *
 */
public class BaseIdEntity {
	protected Long id;
	private Date gmtCreate;//创建时间

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+08:00")
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
}
