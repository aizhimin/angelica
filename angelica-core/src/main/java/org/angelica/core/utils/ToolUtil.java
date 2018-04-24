package org.angelica.core.utils;

/**
 * 项目常用工具类
 * @author aizhimin
 *
 */
public class ToolUtil {
	
	/**
	 * 获取异常堆栈信息
	 * @param e
	 * @return
	 */
	public static String getExceptionMsg(Exception e) {  
		  
        StringBuffer sb = new StringBuffer();  
        sb.append(e.getMessage()+"\n");
        StackTraceElement[] stackArray = e.getStackTrace();  
        for (int i = 0; i < stackArray.length; i++) {  
            StackTraceElement element = stackArray[i];  
            sb.append("	at"+element.toString() + "\n");  
        }  
        return sb.toString();  
    }  
}
