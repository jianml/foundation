package cn.jianml.foundation.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务异常
 *
 * @author wujian
 * @date 2022年01月18日
 */
@Data
@NoArgsConstructor
public class BizException extends RuntimeException {
    /**
     * 错误码
     */
    protected Integer errorCode;

    /**
     * 异常信息
     */
    protected String errorMsg;

    public BizException(BaseErrorInfo baseErrorInfo) {
        super(baseErrorInfo.getMessage());
        this.errorCode = baseErrorInfo.getCode();
        this.errorMsg = baseErrorInfo.getMessage();
    }
}
