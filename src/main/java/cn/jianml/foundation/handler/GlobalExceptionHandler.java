package cn.jianml.foundation.handler;

import cn.jianml.foundation.entity.Response;
import cn.jianml.foundation.exception.BizException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author wujian
 * @date 2022年01月18日
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 业务异常处理
     */
    @ExceptionHandler(BizException.class)
    public Response bizExceptionHandler(BizException e) {
        return Response.error(e.getErrorCode(), e.getErrorMsg());
    }
}
