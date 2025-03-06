package org.ethh.marketMakerManager.permission;

/**
 * MyInterceptor
 *
 * @author GZM
 * @since 2024/12/17 下午6:57
 */

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 检查是否为登录接口
		String requestURI = request.getRequestURI();
		if (requestURI.equals("/api/auth/login")) {
			return true; // 登录接口不需要拦截
		}
		
		// 检查用户是否登录
		if (StpUtil.isLogin()) {
			return true; // 用户已登录，放行请求
		} else {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write("unauthorized: no login");
			response.getWriter().flush();
			return false; // 拦截请求
		}
	}
}