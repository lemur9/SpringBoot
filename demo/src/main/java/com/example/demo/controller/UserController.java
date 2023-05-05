package com.example.demo.controller;

import com.example.demo.config.MyInfo;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Api("用户控制层")
@Controller
public class UserController {

    @Autowired
    MyInfo myInfo;

    @Autowired
    UserService userService;

    @ApiOperation(value = "获取所有用户信息", notes = "获取所有用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "ID", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "缺少必要的请求参数"),
            @ApiResponse(code = 401, message = "请求路径没有相应权限"),
            @ApiResponse(code = 403, message = "请求路径被隐藏不能访问"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 405, message = "请求方法不支持"),
    })
    @RequestMapping("/users")
    public String users(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping("/myInfo")
    public @ResponseBody MyInfo myInfo() {
        return myInfo;
    }

    @RequestMapping("/async")
    public @ResponseBody Object async() {
        System.out.println("start select first user");
        userService.getUserById(1);
        System.out.println("end select first user");
        return "OK";
    }

    @RequestMapping("/asyncResult")
    public @ResponseBody Object asyncResult() throws ExecutionException, InterruptedException {
        System.out.println("start get asyncResult");
        Future<User> result = userService.getUserById(1);
        System.out.println("end get asyncResult");
        return result.get();
    }
}
