package org.angelica.core.security;

/**
 * spring security 推荐的密码加密方法
 * @author aizhimin
 *
 */
public class BCryptUtil {
	
	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	/**
	 * 明文密码加密，相同密码，每次加密都不一样
	 * @param rawPassword 明文密码
	 * @return 返回60位密文密码
	 */
	public static String encode(String rawPassword) {
		return encoder.encode(rawPassword);
	}
	
	/**
	 * 校验密码是否一致
	 * @param rawPassword 加密前的明文密码
	 * @param encodedPassword 加密后的密文密码
	 * @return true 代表密码一致  
	 */
	public static boolean matches(String rawPassword, String encodedPassword) {
		return encoder.matches(rawPassword, encodedPassword);
	}
}
