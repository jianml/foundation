package cn.jianml.foundation.interceptor;

import cn.jianml.foundation.util.TraceIdUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 调用追踪拦截器
 *
 * @author wujian
 * @date 2022年01月23日
 */
@Component
public class TraceInterceptor implements HandlerInterceptor {
    /**
     * 请求追踪ID
     */
    private static final String TRACE_ID = "TRACE-ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = TraceIdUtils.getTraceId();
        MDC.put(TRACE_ID,traceId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(TRACE_ID);
    }
}
