package cn.jianml.foundation.formatter;

import cn.jianml.foundation.enums.DirectionEnum;
import lombok.Builder;
import lombok.Data;

/**
 * 访问日志格式化
 *
 * @author wujian
 * @date 2022年01月20日
 */
@Data
@Builder
public class AccessLogFormatter {
    /**
     * 方向：IN / OUT
     */
    private DirectionEnum direction;

    /**
     * IP
     */
    private String ip;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求资源地址
     */
    private String url;

    /**
     * HTTP响应码
     */
    private int httpStatus;

    /**
     * 系统响应码
     */
    private String responseCode;

    /**
     * 系统响应描述
     */
    private String responseDesc;

    /**
     * 耗时
     */
    private String cost;

    /**
     * 获取格式化后的日志
     */
    public String getLog() {
        StringBuilder sb = new StringBuilder()
                .append("direction:").append(this.direction.name()).append(" | ")
                .append("ip:").append(this.ip).append(" | ")
                .append("method:").append(this.method).append(" | ")
                .append("url:").append(this.url);
        if (DirectionEnum.OUT == this.direction) {
            sb.append(" | ").append("httpCode:").append(this.httpStatus).append(" | ");
            if (this.responseCode != null) {
                sb.append("responseCode:").append(this.responseCode).append(" | ");
            }
            if (this.responseDesc != null) {
                sb.append("responseDesc:").append(this.responseDesc).append(" | ");
            }
            sb.append("cost:").append(this.cost);
        }

        return sb.toString();
    }
}
