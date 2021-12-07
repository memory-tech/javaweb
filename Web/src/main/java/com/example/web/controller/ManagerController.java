package com.example.web.controller;

import com.example.web.domain.Result;
import com.example.web.enm.Message;
import com.example.web.utils.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Memory
 * @Date 2021/12/7 10:43
 * @Version 1.0
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public Result login(String username,String password){
        if(username.equals("管理员")&&password.equals("123456")){
            return ResultUtils.success();
        }
        return ResultUtils.error(Message.USER_ERR_PASS);
    }
}
