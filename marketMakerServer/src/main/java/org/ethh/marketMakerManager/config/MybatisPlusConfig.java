package org.ethh.marketMakerManager.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * MybatisPlusConfig
 *
 * @author GZM
 * @since 2024/12/18 上午12:15
 */

@Configuration
public class MybatisPlusConfig {
	
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
		paginationInnerInterceptor.setDbType(DbType.MYSQL);
		paginationInnerInterceptor.setOverflow(true);
		interceptor.addInnerInterceptor(paginationInnerInterceptor);
		OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor = new OptimisticLockerInnerInterceptor();
		interceptor.addInnerInterceptor(optimisticLockerInnerInterceptor);
		return interceptor;
	}
}
