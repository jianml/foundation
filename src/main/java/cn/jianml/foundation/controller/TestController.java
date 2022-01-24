package cn.jianml.foundation.controller;

import cn.jianml.foundation.service.RedisService;
import cn.jianml.foundation.util.JSchUtils;
import cn.jianml.foundation.vo.Response;
import cn.jianml.foundation.vo.DemoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
@Api(tags = "测试接口")
public class TestController {
    @Autowired
    private RedisService redisService;

    @ApiOperation("GET方法")
    @GetMapping("get")
    public Response get() {
        log.debug("debug test");
        log.info("Inter get method");
        log.error("error test");
        return Response.success("Hello World");
    }

    @ApiOperation("POST方法")
    @PostMapping("post")
    public Response post() {
        log.info("Inter post");
        return Response.success();
    }

    @ApiOperation("校验方法")
    @GetMapping("validate")
    public Response validate(@Length(min=5) @RequestParam String a, @Length(max = 3) @RequestParam String b) {
        return Response.success();
    }

    @ApiOperation("校验方法")
    @PostMapping("validate")
    public Response validate1(@Valid @RequestBody DemoVO demoVO) {
        return Response.success(demoVO);
    }

    @ApiOperation("校验方法")
    @PostMapping(value = "validate/form", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response validate2(@Valid DemoVO demoVO) {
        return Response.success();
    }

    @ApiOperation("JSCH")
    @PostMapping("jsch")
    public Response jsch(String host, int port, String username, String password, String command) {
        return Response.success(JSchUtils.executeCommand(host, port, username, password, command));
    }

    @ApiOperation("redis")
    @PostMapping("redis")
    public Response redis(String key, String value) {
        redisService.set(key, value);
        return Response.success();
    }

    @ApiOperation("redis")
    @GetMapping("redis")
    public Response redis1(String key) {
        return Response.success(redisService.get(key));
    }
}
