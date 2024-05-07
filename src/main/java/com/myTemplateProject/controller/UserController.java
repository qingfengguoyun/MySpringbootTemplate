package com.myTemplateProject.controller;

import com.myTemplateProject.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户Controller")
public class UserController {

    @GetMapping("/test")
    @ApiOperation(value = "用户测试接口")
    public Result userTest(){
        return Result.success("userTest");
    }
}
