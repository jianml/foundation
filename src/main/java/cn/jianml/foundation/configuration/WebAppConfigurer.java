package cn.jianml.foundation.configuration;

import cn.jianml.foundation.interceptor.AccessLogInterceptor;
import cn.jianml.foundation.interceptor.TraceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 *
 * @author wujian
 * @date 2022年01月20日
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    @Autowired
    private AccessLogInterceptor accessLogInterceptor;

    @Autowired
    private TraceInterceptor traceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(traceInterceptor).order(1);
        registry.addInterceptor(accessLogInterceptor)
                .excludePathPatterns("/swagger-ui/**", "/webjars/**", "/doc.html", "/swagger-resources/**", "/v3/**")
                .order(2);
    }
}
