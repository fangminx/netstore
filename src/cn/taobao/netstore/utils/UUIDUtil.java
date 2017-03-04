package cn.taobao.netstore.utils;

import java.util.UUID;

/**
 * 生成随机字符串的工具类
 * @author fangmin
 *
 */
public class UUIDUtil {
	public  static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
