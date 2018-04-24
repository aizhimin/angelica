package org.angelica.core.id;

/**
 * 将id转换成唯一hash值
 * 用于掩盖真实ID，可用于网站核心数据ID的隐藏
 * @author aizhimin
 */
public class HashidsUtil {
	private static final String salt = "546123dasda$%&**((afsdfad";//盐
	private static final int minHashLength = 15;//hash最小长度
	private static Hashids hashids;
	
	public static Hashids getInstance() {  
        if (hashids == null) {  
        	hashids = new Hashids(salt,minHashLength);  
        }  
        return hashids;  
    }  
	
	public static String encode(Long id) {
		return HashidsUtil.getInstance().encode(id);
	}
	
	public static long decode(String hash) {
		return HashidsUtil.getInstance().decode(hash)[0];
	}
	
}
