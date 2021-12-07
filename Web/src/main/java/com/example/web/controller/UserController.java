package com.example.web.controller;

import com.example.web.domain.Result;
import com.example.web.domain.User;
import com.example.web.enm.Message;
import com.example.web.mapper.UserMapper;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author Memory
 * @Date 2021/12/2 13:25
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    /**
     *查找所有员工信息
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
    public Result findAll(){
        List<User> users = userMapper.findAll();
        return ResultUtils.success(users);
    }

    /**
     *添加一个员工
     * @param user
     * @return
     */
    @RequestMapping(value = "/addOne",method = RequestMethod.GET)
    @ResponseBody
    public Result addOne(User user){
        userMapper.addOne(user);
        return ResultUtils.success(user.getId());
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    @ResponseBody
    public Result findById(int id){
        User user=userMapper.findById(id);
        return ResultUtils.success(user);
    }

    /**
     * 删除一个员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteOne",method = RequestMethod.GET)
    @ResponseBody
    public Result deleteOne(int id){
        User user= userMapper.findById(id);
        if(user==null){
            return ResultUtils.error(Message.USER_NOT_EXIST);
        }
        userMapper.deleteOne(id);
        return ResultUtils.success();
    }

    /**
     * 更新员工信息
     * @param user
     * @return
     */
    @RequestMapping(value = "updateOne",method=RequestMethod.GET)
    @ResponseBody
    public Result updateOne(User user){
        User user0= userMapper.findById(user.getId());
        if(user0==null){
            return ResultUtils.error(Message.USER_NOT_EXIST);
        }
        userMapper.updateOne(user);
        return ResultUtils.success(user.getId());
    }
}

