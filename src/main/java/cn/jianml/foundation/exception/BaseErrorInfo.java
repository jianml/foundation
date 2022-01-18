package cn.jianml.foundation.exception;

/**
 * 异常信息接口
 *
 * @author wujian
 * @date 2022年01月18日
 */
public interface BaseErrorInfo {
    Integer getCode();

    String getMessage();
}
