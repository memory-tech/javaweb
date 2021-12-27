package com.example.web.controller;

import com.example.web.domain.Result;
import com.example.web.domain.UserAttendance;
import com.example.web.mapper.UserAttendanceMapper;
import com.example.web.mapper.UserMapper;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author Memory
 * @Date 2021/12/17 13:21
 * @Version 1.0
 */
@Controller
@RequestMapping("/count")
public class CountController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserAttendanceMapper userAttendanceMapper;
    /**
     * 查找本公司的男女人数，返回男女员工数量，前端画饼图
     * @return
     */
    @RequestMapping(value = "findSex",method = RequestMethod.GET)
    @ResponseBody
    public Result manWoman(){
        int a=userMapper.findSex("男");
        int b=userMapper.findSex("女");
        int[] sum={a,b};
        return ResultUtils.success(sum);
    }

    /**
     * 查找本公司的出勤情况
     * @return
     */
    @RequestMapping(value = "findAttendance",method = RequestMethod.GET)
    @ResponseBody
    public Result attendance(){
        int a=userAttendanceMapper.attendance("正常");
        int b=userAttendanceMapper.attendance("缺卡");
        int[] sum={a,b};
        return ResultUtils.success(sum);
    }
}
