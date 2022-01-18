package cn.jianml.foundation.enums;

import cn.jianml.foundation.exception.BaseErrorInfo;

/**
 * 错误码定义
 *
 * @author wujian
 * @date 2022年01月17日
 */
public enum ResultEnum implements BaseErrorInfo {
    /**
     * 成功状态码
     */
    SUCCESS(200, "Operation Success"),

    /**
     * 参数错误
     */
    PARAM_ERROR(1001, "Parameter Error"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
