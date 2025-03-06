package org.ethh.common.cache;
import org.ethh.common.BaseDTO;

import java.util.HashMap;
import java.util.Map;
/**
 * SimpleCache
 *
 * @author GZM
 * @since 2024/12/13 下午5:43
 */
public class SimpleCache {
	
	private static Map<String, Map<String, BaseDTO>> cache = new HashMap<>();
	
	public static String CAPITAL = "Capital";
	public static String POSITION = "Position";
	public static String ORDER = "Order";
	
	public static void put(String key, Map<String, BaseDTO> map) {
		cache.put(key, map);
	}
	
	public static Map<String, BaseDTO> get(String key) {
		return cache.get(key);
	}
	
	public static boolean containsKey(String key) {
		return cache.containsKey(key);
	}
	
	public static void remove(String key) {
		cache.remove(key);
	}
	
	public static void clear() {
		cache.clear();
	}
}
