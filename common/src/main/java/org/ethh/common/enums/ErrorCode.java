package org.ethh.common.enums;

/**
 * @author wangyifei
 */
public enum ErrorCode {
    /**
     * 错误码定义规范
     * 应用标识：表示错误属于哪个应用，三位数字。
     * 功能域标识：表示错误属于应用中的哪个功能模块，三位数字。
     * 错误类型：表示错误属于哪种类型，一位字母。
     * 错误编码：错误类型下的具体错误，三位数字。
     */
    SUCCESS("001-001-S-000", "成功"),

    INTERNAL_ERROR("001-001-E-000", "系统内部错误"),
    
    CUSTOM_ERROR("001-002-E-000", "业务异常"),
    
    
    ;

    private final String code;

    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
