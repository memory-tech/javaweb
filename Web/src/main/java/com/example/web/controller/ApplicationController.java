package com.example.web.controller;

import com.example.web.domain.Application;
import com.example.web.domain.Result;
import com.example.web.domain.UserAttendance;
import com.example.web.enm.Message;
import com.example.web.mapper.ApplicationMapper;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Memory
 * @Date 2021/12/8 14:34
 * @Version 1.0
 */
@Controller
@RequestMapping("/deal")
public class ApplicationController {
    @Autowired
    ApplicationMapper applicationMapper;
    @Autowired
    AttendenceController attendenceController;
    /**
     * 提交补卡申请
     * @param application
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    @ResponseBody
    public Result add(Application application){
        Application application1=applicationMapper.findByNoAndDate(application);
        if(application1!=null) {
            return ResultUtils.error(Message.APPLY_IS_EXIST);
        }
        applicationMapper.add(application);
        return ResultUtils.success("已提交申请");
    }

    /**
     * 批准补卡申请
     * @param application
     * @return
     */
    @RequestMapping(value = "/yes",method = RequestMethod.GET)
    @ResponseBody
    public Result yes(Application application){
        UserAttendance userAttendance=new UserAttendance();
        userAttendance.setNo(application.getNo());
        userAttendance.setDate(application.getDate());
        userAttendance.setState("正常");
        attendenceController.add(userAttendance);
        applicationMapper.delete(application);
        return ResultUtils.success("申请已批准");
    }

    /**
     * 不批准申请
     * @param application
     * @return
     */
    @RequestMapping(value = "/no",method = RequestMethod.GET)
    @ResponseBody
    public Result no(Application application){
        applicationMapper.delete(application);
        return ResultUtils.success("申请已驳回");
    }

    /**
     * 查看所有补卡申请
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public Result query(){
        return ResultUtils.success(applicationMapper.findAll());
    }
}
