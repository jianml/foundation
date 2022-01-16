package cn.jianml.foundation.entity;

import cn.jianml.foundation.enums.ResultCode;
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
                .code(ResultCode.SUCCESS.code())
                .msg(ResultCode.SUCCESS.message())
                .build();
    }

    public static Response success(Object data) {
        return Response.builder()
                .code(ResultCode.SUCCESS.code())
                .msg(ResultCode.SUCCESS.message())
                .data(data)
                .build();
    }

    public static Response error(ResultCode resultCode) {
        return Response.builder()
                .code(resultCode.code())
                .msg(resultCode.message())
                .build();
    }
}
