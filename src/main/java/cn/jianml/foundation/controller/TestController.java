package cn.jianml.foundation.controller;

import cn.jianml.foundation.entity.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller
 *
 * @author wujian
 * @date 2022年01月17日
 */
@Slf4j
@Validated
@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("get")
    public Response get() {
        log.debug("debug test");
        log.info("Inter get method");
        log.error("error test");
        return Response.success("Hello World");
    }

    @PostMapping("post")
    public Response post() {
        log.info("Inter post");
        return Response.success();
    }
}
