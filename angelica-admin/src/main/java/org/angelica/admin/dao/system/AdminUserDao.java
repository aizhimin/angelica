package org.angelica.admin.dao.system;

import java.util.List;

import org.angelica.admin.entity.system.AdminUser;

import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface AdminUserDao extends BaseMapper<AdminUser> {
	
	/**
	 * 获取用户的权限字符串
	 * @param adminUserId
	 * @return
	 */
	public List<String> listPermission(Long adminUserId);
	
}
