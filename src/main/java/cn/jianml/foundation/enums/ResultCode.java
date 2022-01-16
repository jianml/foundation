package cn.jianml.foundation.enums;

/**
 * 错误码定义
 *
 * @author wujian
 * @date 2022年01月17日
 */
public enum ResultCode {
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

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
