package org.angelica.admin.service.system;

import java.util.Set;

import org.angelica.admin.entity.system.AdminUser;

public interface AdminUserService {
	/**
	 * 根据id查询一个用户
	 * @param id
	 * @return
	 */
	AdminUser getAdminUser(Long id);
	
	/**
	 * 查询用户拥有的权限字符串
	 * @param adminUserId
	 * @return
	 */
	Set<String> listPermission(Long adminUserId);
}
