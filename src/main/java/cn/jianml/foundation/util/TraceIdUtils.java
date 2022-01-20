package cn.jianml.foundation.util;

import java.util.UUID;

/**
 * 请求追踪工具类
 *
 * @author wujian
 * @date 2022年01月21日
 */
public class TraceIdUtils {
    private TraceIdUtils(){
    }

    /**
     * 生成traceId(基于UUID)
     */
    public static String getTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
