package org.angelica.admin.common.shiro;

import java.util.Set;

import org.angelica.admin.common.Constant;
import org.angelica.admin.common.jwt.JwtToken;
import org.angelica.core.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.angelica.qingdanxia.common.RedisKeys;
import com.angelica.qingdanxia.entity.account.AdminUser;
import com.angelica.qingdanxia.service.account.AdminUserService;

import io.jsonwebtoken.ExpiredJwtException;
/**
 * 认证与授权
 * @author aizhimin
 *
 */
@Component
public class ShiroJwtRealm extends AuthorizingRealm {
	
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
	
	/**
     * 授权(验证权限时调用)
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser)principals.getPrimaryPrincipal();
		Set<String> permsSet = adminUserService.loadPermissions(shiroUser.getUserId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
	}

	/**
     * 认证(登录时调用)
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String jwtToken = (String) token.getPrincipal();
		Long userId = null;
        //根据jwtToken，查询用户信息
		try {
			userId = JwtTokenUtil.getUserIdFromToken(Constant.JWT_SECRET,jwtToken);
		}catch(ExpiredJwtException e) {
			throw new AuthenticationException("token已过期");
		}catch(Exception e) {
			throw new AuthenticationException("token无效");
		}
		
		//校验token是否注销，如果发现在黑名单中，则判定token已注销
      	String invalidToken = redisTemplate.opsForValue().get(RedisKeys.BLACKLIST.getPrefix()+userId);
      	if(StringUtils.isNotBlank(invalidToken)) {
      		throw new AuthenticationException("token已注销");
      	}
		
        //查询用户信息: 每次鉴权，此处可缓存，提高速度
        AdminUser adminUser = adminUserService.getAdminUser(userId);
        //账号禁用
        if(adminUser.getStatus() == AdminUser.FORBIDDEN){
            throw new DisabledAccountException("账号已被禁用,请联系管理员");
        }
        //账号锁定
        if(adminUser.getStatus() == AdminUser.LOCKED){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(new ShiroUser(userId,adminUser.getLoginName(),adminUser.getName()), jwtToken, getName());
        return info;
	}

}
