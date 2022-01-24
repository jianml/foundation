package cn.jianml.foundation.handler;

import cn.jianml.foundation.vo.Response;
import cn.jianml.foundation.enums.ResultEnum;
import cn.jianml.foundation.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 全局异常处理器
 *
 * @author wujian
 * @date 2022年01月18日
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response exception(Exception e) {
        log.error("Exception handle: ", e);
        return Response.error(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response bizExceptionHandler(BizException e) {
        log.error("BizException handle: ", e);
        return Response.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理 form data方式调用接口校验失败抛出的异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response bindExceptionHandler(BindException e) {
        log.error("BindException handle: ", e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            stringJoiner.add("参数[" + field + "]:" +message);
        }
        return Response.error(ResultEnum.PARAM_ERROR.getCode(), stringJoiner.toString());
    }

    /**
     * 处理 json 请求体调用接口校验失败抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException handle: ", e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            stringJoiner.add("参数[" + field + "]:" +message);
        }
        return Response.error(ResultEnum.PARAM_ERROR.getCode(), stringJoiner.toString());
    }

    /**
     * 处理单个参数校验失败抛出的异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response constraintViolationExceptionHandler(ConstraintViolationException e) {
        log.error("ConstraintViolationException handle: ", e);
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuilder sb = new StringBuilder();
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String paramName = propertyPath.replaceFirst("validate\\d*\\.", "");
            stringJoiner.add("参数[" + paramName + "]:" + constraintViolation.getMessage());
        }
        return Response.error(ResultEnum.PARAM_ERROR.getCode(), stringJoiner.toString());
    }

    /**
     * 处理参数缺失异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException handle: ", e);
        return Response.error(ResultEnum.PARAM_ERROR.getCode(), "缺少参数：" + e.getParameterName());
    }
}
