package cn.jianml.foundation.interceptor;

import cn.jianml.foundation.enums.DirectionEnum;
import cn.jianml.foundation.formatter.AccessLogFormatter;
import cn.jianml.foundation.util.IPUtils;
import cn.jianml.foundation.util.TraceIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HTTP请求日志拦截器
 *
 * @author wujian
 * @date 2022年01月20日
 */
@Slf4j(topic = "AccessLog")
@Component
public class AccessLogInterceptor implements HandlerInterceptor {
    /**
     * 业务开始时间
     */
    private static final String START_TIME = "StartTime";

    /**
     * 请求追踪ID
     */
    private static final String TRACE_ID = "TraceId";

    /**
     * 控制器方法处理之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = TraceIdUtils.getTraceId();
        AccessLogFormatter accessLogFormatter = AccessLogFormatter.builder()
                .traceId(traceId)
                .direction(DirectionEnum.IN)
                .ip(IPUtils.getRemoteHost(request))
                .method(request.getMethod())
                .url(request.getRequestURL().toString())
                .build();
        log.info(accessLogFormatter.getLog());
        request.setAttribute(START_TIME, System.currentTimeMillis());
        request.setAttribute(TRACE_ID, traceId);
        return true;
    }

    /**
     * 控制器方法抛不抛异常都会被调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long) request.getAttribute(START_TIME);
        long cost = System.currentTimeMillis() - startTime;
        AccessLogFormatter accessLogFormatter = AccessLogFormatter.builder()
                .traceId((String) request.getAttribute(TRACE_ID))
                .direction(DirectionEnum.OUT)
                .ip(IPUtils.getRemoteHost(request))
                .method(request.getMethod())
                .url(request.getRequestURL().toString())
                .cost(cost + "ms")
                .build();
        log.info(accessLogFormatter.getLog());
    }
}
