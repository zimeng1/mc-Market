package org.ethh.marketMakerManager.exception;

import lombok.extern.slf4j.Slf4j;
import org.ethh.common.enums.ErrorCode;
import org.ethh.marketMakerManager.model.Response.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author wangyifei
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse<String> handleIllegalException(Exception e) {
        log.error(" ExceptionHandler Exception", e);
        return ApiResponse.failureWithDetails(ErrorCode.INTERNAL_ERROR,e.getMessage());
    }

    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public ApiResponse<String> handleGlobalExceptionException(GlobalException e) {
        log.error(" ExceptionHandler GlobalExceptionException", e);
        return ApiResponse.failureWithDetails(ErrorCode.CUSTOM_ERROR,e.getMessage());
    }
    
    
}
