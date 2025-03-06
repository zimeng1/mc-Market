package org.ethh.marketMakerManager.model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ethh.common.enums.ErrorCode;

import java.util.Map;

/**
 * @author wangyifei
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功响应的静态方法
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    /**
     * 失败响应的静态方法
     */
    public static ApiResponse<Void> failure(ErrorCode errorCode) {
        return ApiResponse.<Void>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

    /**
     * 带有额外信息的失败响应的静态方法
     */
    public static ApiResponse<Map<String, Object>> failureWithDetails(ErrorCode errorCode, Map<String, Object> details) {
        return ApiResponse.<Map<String, Object>>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(details)
                .build();
    }
    
    /**
     * 带有额外信息的失败响应的静态方法
     */
    public static <T> ApiResponse<T> failureWithDetails(ErrorCode errorCode, T data) {
        return ApiResponse.<T>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(data)
                .build();
    }
}
