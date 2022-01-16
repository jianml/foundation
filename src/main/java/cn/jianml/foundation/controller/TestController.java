package cn.jianml.foundation.controller;

import cn.jianml.foundation.entity.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("get")
    public Response get() {
        return Response.success("Hello World");
    }
}
