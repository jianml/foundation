package cn.jianml.foundation.controller;

import cn.jianml.foundation.entity.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller
 *
 * @author wujian
 * @date 2022年01月17日
 */
@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("get")
    public Response get() {
        return Response.success("Hello World");
    }
}
