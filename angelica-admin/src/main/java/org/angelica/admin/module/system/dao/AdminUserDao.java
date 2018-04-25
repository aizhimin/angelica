package org.angelica.admin.module.system.dao;

import java.util.List;

import org.angelica.admin.module.system.entity.AdminUser;

import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface AdminUserDao extends BaseMapper<AdminUser> {
	
	public List<String> listPermission(Long adminUserId);
	
}
