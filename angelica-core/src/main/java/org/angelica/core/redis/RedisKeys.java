package org.angelica.core.redis;
/**
 * 统一定义redis中存储的各种对象的Key前缀和超时时间.
 */
public enum RedisKeys {
	BLACKLIST("blacklist:", 60 * 60 * 1),//黑名单
	USER("user:", 60 * 60 * 1),//用户信息
	PERMISSIONS("permissions:", 7 * 24 * 60 * 60 * 1),//用户权限信息,缓存7天
	;

	private String prefix;
	private int expiredTime;

	RedisKeys(String prefix, int expiredTime) {
		this.prefix = prefix;
		this.expiredTime = expiredTime;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getExpiredTime() {
		return expiredTime;
	}
}
