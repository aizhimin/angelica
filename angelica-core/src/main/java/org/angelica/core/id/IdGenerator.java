package org.angelica.core.id;

import java.util.UUID;

/**
 * id 生成器
 * @author aizhimin
 *
 */
public class IdGenerator {
	
	/**
	 * mongodb id 生成器
	 * @return
	 */
	public static String getObjectId() {
		return ObjectIdEntity.get().toHexString();
	}
	
	/**
	 * 将long类型id，转换成hash id，用于隐藏真实ID
	 * @param id
	 * @return
	 */
	public static String getHashId(Long id) {
		return HashidsUtil.encode(id);
	}
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getRandomUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
