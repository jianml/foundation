package cn.jianml.foundation.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 默认Controller
 *
 * @author wujian
 * @date 2022年01月23日
 */
@RestController
public class DefaultController {
    @Value("${spring.application.name}")
    private String appName;

    @GetMapping
    public String service() {
        return "Hello! I am " + appName;
    }
}
