package org.ethh.marketMakerManager.permission;

/**
 * WebSecurityConfig
 *
 * @author GZM
 * @since 2024/12/17 下午6:08
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
	
	@Autowired
	private MyInterceptor myInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor)
				.addPathPatterns("/api/**") // 拦截所有请求
				.excludePathPatterns("/api/auth/login", "/api/auth/logout"); // 排除登录和登出接口
	}
}