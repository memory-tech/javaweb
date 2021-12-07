package com.example.web.controller;

import com.example.web.domain.Result;
import com.example.web.domain.User;
import com.example.web.enm.Message;
import com.example.web.mapper.UserMapper;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @Author Memory
 * @Date 2021/12/2 13:25
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"no"})
public class UserController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     * @param no
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public Result login(String no, String password, Model model){
        User user=userMapper.findByNo(no);
        if(user==null||!user.getPassword().equals(password)){
            return ResultUtils.error(Message.USER_ERR_PASS);
        }
        model.addAttribute("no",no);
        return ResultUtils.success("登录成功");
    }

    /**
     * 修改登录密码
     * @param no
     * @param password
     * @return
     */
    @RequestMapping(value = "/editPassword",method = RequestMethod.GET)
    @ResponseBody
    public Result editPassword(String no,String password){
        User user=userMapper.findByNo(no);
        if(user==null){
            return ResultUtils.error(Message.USER_NOT_EXIST);
        }
        user.setPassword(password);
        userMapper.updateOne(user);
        return ResultUtils.success("密码修改成功");
    }
}

