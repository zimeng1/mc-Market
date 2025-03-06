package org.ethh.marketMakerManager.permission;

/**
 * User
 *
 * @author GZM
 * @since 2024/12/17 下午6:06
 */
public class User {
	private String username;
	private String password; // 实际项目中密码应该加密存储
	
	// 构造函数、getter 和 setter
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}
