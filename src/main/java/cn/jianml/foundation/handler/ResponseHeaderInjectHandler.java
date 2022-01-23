package cn.jianml.foundation.handler;

import cn.jianml.foundation.constant.SystemConstants;
import cn.jianml.foundation.vo.Response;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局统一响应处理：注入响应头
 *
 * @author wujian
 * @date 2022年01月23日
 */
@ControllerAdvice
public class ResponseHeaderInjectHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.getMethod().getReturnType() == Response.class;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        Response responseBody = (Response) body;
        HttpHeaders headers = response.getHeaders();
        headers.set(SystemConstants.RESPONSE_CODE, String.valueOf(responseBody.getCode()));
        headers.set(SystemConstants.RESPONSE_DESC, responseBody.getMsg());
        return body;
    }
}
