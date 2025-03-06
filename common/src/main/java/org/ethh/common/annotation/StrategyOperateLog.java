package org.ethh.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * StrategyOperateLog
 *
 * @author GZM
 * @since 2024/12/6 下午2:17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StrategyOperateLog {
	
}
