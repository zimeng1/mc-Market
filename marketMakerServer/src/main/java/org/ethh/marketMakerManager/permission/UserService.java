package org.ethh.marketMakerManager.permission;

/**
 * UserService
 *
 * @author GZM
 * @since 2024/12/17 下午6:07
 */
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
	private static final Map<String, User> userMap = new HashMap<>();
	
	static {
		// 模拟用户数据，实际项目中应从数据库中获取
		userMap.put("admin", new User("admin", "123456")); // 用户名: test，密码: 123456
	}
	
	public User getUserByUsername(String username) {
		return userMap.get(username);
	}
}
