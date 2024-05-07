package com.myTemplateProject.controller;

import com.myTemplateProject.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Api(tags = "测试接口")
public class TestController {

    @GetMapping("/test")
    @ApiOperation(value = "测试")
    public Result test(){
        return Result.success("测试接口返回");
    }
}
