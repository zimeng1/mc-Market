package org.ethh.marketMakerManager.permission;

/**
 * AuthController
 *
 * @author GZM
 * @since 2024/12/17 下午6:08
 */
import cn.dev33.satoken.stp.StpUtil;
import org.ethh.common.enums.ErrorCode;
import org.ethh.marketMakerManager.model.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ApiResponse<LoginInfo> login(@RequestParam String username, @RequestParam String password) {
		User user = userService.getUserByUsername(username.trim());
		LoginInfo loginInfo = new LoginInfo();
		if (user != null && user.getPassword().equals(password)) {
			// 登录成功，生成 Token
			StpUtil.login(username);
			loginInfo.setToken(StpUtil.getTokenValue());
			loginInfo.setResult("login success");
			return ApiResponse.success(loginInfo);
		}
		loginInfo.setResult("login fail,用户名或密码错误");
		return ApiResponse.failureWithDetails(ErrorCode.INTERNAL_ERROR,loginInfo);
	}
	
	@GetMapping("/logout")
	public ApiResponse<LoginInfo> logout() {
		LoginInfo loginInfo = new LoginInfo();
		StpUtil.logout(); // 登出
		loginInfo.setResult("logout success");
		return ApiResponse.success(loginInfo);
	}
	
	@GetMapping("/userInfo")
	public String getUserInfo() {
		// 获取用户信息
		String username = StpUtil.getLoginIdAsString();
		return "当前登录用户: " + username;
//		return "当前登录用户: " + "1";
	}
}