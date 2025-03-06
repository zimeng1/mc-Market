package org.ethh.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * LoggingAspect
 *
 * @author GZM
 * @since 2024/12/6 下午2:35
 */
@Aspect
@Component
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("@annotation(org.ethh.common.annotation.StrategyOperateLog)")
	public void logExecutionTime(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		String[] parameterNames = methodSignature.getParameterNames();
		Object[] parameterValues = joinPoint.getArgs();
		StringBuilder params = new StringBuilder();
		for (int i = 0; i < parameterNames.length; i++) {
			params.append(parameterNames[i]).append(": ").append(parameterValues[i]).append(", ");
		}
		logger.info("Executing method: {} with parameters: {}", joinPoint.getSignature().toShortString(), params.toString());
	}
	
	@AfterReturning(pointcut = "@annotation(org.ethh.common.annotation.StrategyOperateLog)", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		logger.info("Method execution completed: {}", joinPoint.getSignature().toShortString());
		logger.info("Returned value: {}", result);
	}
	
	@AfterThrowing(pointcut = "@annotation(org.ethh.common.annotation.StrategyOperateLog)", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		logger.error("Exception in method: {}", joinPoint.getSignature().toShortString());
		logger.error("Exception message: {}", exception.getMessage());
	}
}
