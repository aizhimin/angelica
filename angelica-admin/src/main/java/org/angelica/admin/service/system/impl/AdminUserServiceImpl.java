package org.angelica.admin.service.system.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.angelica.admin.dao.system.AdminUserDao;
import org.angelica.admin.entity.system.AdminUser;
import org.angelica.admin.service.system.AdminUserService;
import org.angelica.core.redis.RedisKeys;
import org.angelica.core.utils.JacksonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Sets;

@Service
public class AdminUserServiceImpl implements AdminUserService {
	@Autowired
	private AdminUserDao adminUserDao;
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Override
	public AdminUser getAdminUser(Long id) {
		return adminUserDao.selectById(id);
	}
	
	@Override
	public Set<String> listPermission(Long adminUserId){
		//从缓存中查询权限
		String permStr = redisTemplate.opsForValue().get(RedisKeys.PERMISSIONS.getPrefix()+adminUserId);
		if(StringUtils.isNotBlank(permStr)) {
			return JacksonUtil.nonDefaultMapper().fromJson(permStr, new TypeReference<Set<String>>() {});
		}
		//从数据库中查询权限
		Set<String> permsSet = Sets.newHashSet();
		List<String> permList = adminUserDao.listPermission(adminUserId);
		for(String perm:permList) {
			permsSet.addAll(Arrays.asList(perm.trim().split(",")));
		}
		
		//设置权限缓存
		if(!permsSet.isEmpty()) {
			redisTemplate.opsForValue().set(RedisKeys.PERMISSIONS.getPrefix()+adminUserId, 
					JacksonUtil.nonDefaultMapper().toJson(permsSet),
					RedisKeys.PERMISSIONS.getExpiredTime(),
					TimeUnit.SECONDS);
		}
		return permsSet;
	}
}
