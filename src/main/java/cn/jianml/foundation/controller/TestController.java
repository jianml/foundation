package cn.jianml.foundation.controller;

import cn.jianml.foundation.entity.Response;
import cn.jianml.foundation.vo.DemoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
}
