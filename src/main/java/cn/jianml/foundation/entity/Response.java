package cn.jianml.foundation.entity;

import cn.jianml.foundation.enums.ResultEnum;
import cn.jianml.foundation.exception.BaseErrorInfo;
import lombok.Builder;
import lombok.Data;

/**
 * 统一响应
 *
 * @author wujian
 * @date 2022年01月17日
 */
@Data
@Builder
public class Response {
    private Integer code;

    private String msg;

    private Object data;

    public static Response success() {
        return Response.builder()
                .code(ResultEnum.SUCCESS.getCode())
                .msg(ResultEnum.SUCCESS.getMessage())
                .build();
    }

    public static Response success(Object data) {
        return Response.builder()
                .code(ResultEnum.SUCCESS.getCode())
                .msg(ResultEnum.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public static Response error(BaseErrorInfo baseErrorInfo) {
        return Response.builder()
                .code(baseErrorInfo.getCode())
                .msg(baseErrorInfo.getMessage())
                .build();
    }

    public static Response error(Integer errorCode, String errorMsg) {
        return Response.builder()
                .code(errorCode)
                .msg(errorMsg)
                .build();
    }
}
