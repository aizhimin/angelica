package org.angelica.core.utils;

import java.util.Date;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**
 * jwt token 工具类
 * @author aizhimin
 *
 */
public class JwtTokenUtil {
	
	private static final int JWT_TTL = 60*60*1000;  //accessToken token 有效时长1小时 
	private static final int JWT_REFRESH_TTL = 7*24*60*60*1000;  //refreshToken 有效时长7天小时
	
	/**
	 * 签发accessToken
	 * @param jwtSecretKey
	 * @param issuer
	 * @param userId
	 * @return
	 */
	public static String createAccessToken(String jwtSecretKey,String issuer,String userId) {
		return createJwtToken(jwtSecretKey,issuer,userId,JWT_TTL);
	}
	
	/**
	 * 签发refreshToken
	 * @param jwtSecretKey
	 * @param issuer
	 * @param userId
	 * @return
	 */
	public static String createRefreshToken(String jwtSecretKey,String issuer,String userId) {
		return createJwtToken(jwtSecretKey,issuer,userId,JWT_REFRESH_TTL);
	}
	
	/**
	 * 生成jwt token
	 * @param userDetails
	 * @return
	 */
	private static String createJwtToken(String jwtSecretKey,String issuer,String subject,long ttlMillis) {
	    //当前时间戳
	    long currentTimeMillis = System.currentTimeMillis();
	    JwtBuilder jwtBuilder  =  Jwts.builder();
	    //jwt token id
	    jwtBuilder.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	    //用户名
	    jwtBuilder.setSubject(subject);
	    // 签发者
	    jwtBuilder.setIssuer(issuer);
	    //签发时间
	    jwtBuilder.setIssuedAt(new Date(currentTimeMillis));
	    // 有效时间
		Date expiration = new Date(currentTimeMillis+ttlMillis);
		jwtBuilder.setExpiration(expiration);
		//gzip 压缩
		jwtBuilder.compressWith(CompressionCodecs.GZIP);
		//签名加密
		byte[] base64EncodedSecretKey = DatatypeConverter.parseBase64Binary(jwtSecretKey);
		jwtBuilder.signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey);
		
	    //压缩jwt为字符串
	    return jwtBuilder.compact();
    }
	
	
	
	/**
	 * 解析jwt
	 * @param jwt token
	 * @return
	 */
	public static Claims parseJWT(String jwtSecretKey,String jwtToken) {
	    Claims claims = Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
	       .parseClaimsJws(jwtToken).getBody();
	    return claims;
	}
	
	public static Long getUserIdFromToken(String jwtSecretKey,String jwtToken) {
		Claims claims = parseJWT(jwtSecretKey,jwtToken);
		String subject = claims.getSubject();
		if(StringUtils.isNotBlank(subject)) {
			return Long.valueOf(subject);
		}
		return null;
	}
	
	/**
	 * 从token中获取过期时间
	 * @param token
	 * @return
	 */
	public static Date getExpirationDate(String jwtSecretKey,String token) {
		Claims claims = JwtTokenUtil.parseJWT(jwtSecretKey,token);
		return claims.getExpiration();
	}
	
}
