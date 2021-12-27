package com.example.web.controller;

import com.example.web.domain.Result;
import com.example.web.domain.UserAttendance;
import com.example.web.enm.Message;
import com.example.web.mapper.UserAttendanceMapper;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @Author Memory
 * @Date 2021/12/8 12:07
 * @Version 1.0
 */
@Controller
@RequestMapping("/attend")
public class AttendenceController {
    @Autowired
    private UserAttendanceMapper userAttendanceMapper;

    /**
     * 查询所有员工的考勤情况
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
    public Result findAllAttendance(){
        List<UserAttendance> userAttendanceList=userAttendanceMapper.findAll();
        return ResultUtils.success(userAttendanceList);
    }

    /**
     * 根据no查询一个员工的考勤情况
     * @param no
     * @return
     */
    @RequestMapping(value = "/findByNo",method = RequestMethod.GET)
    @ResponseBody
    public Result findAttendanceByNo(String no){
        List<UserAttendance> userAttendanceList=userAttendanceMapper.findByNo(no);
        if(userAttendanceList.size()==0){
            return ResultUtils.error(Message.ATTEND_NOT_EXIST);
        }
        return ResultUtils.success(userAttendanceList);
    }

    /**
     * 查询该员工的缺卡情况
     * @param no
     * @return
     */
    @RequestMapping(value = "/findDisAttend",method = RequestMethod.GET)
    @ResponseBody
    public Result findAttendanceError(String no){
        List<UserAttendance> userAttendanceList=userAttendanceMapper.findDisAttend(no);
        if(userAttendanceList.size()==0){
            return ResultUtils.error(Message.DIS_ATTEND_NOT_EXIST);
        }
        return ResultUtils.success(userAttendanceList);
    }
    /**
     * 根据个人username查询考勤情况
     * @param username
     * @return
     */
    @RequestMapping(value = "/findByName",method = RequestMethod.GET)
    @ResponseBody
    public Result findAttendanceByName(String username){
        List<UserAttendance> userAttendanceList=userAttendanceMapper.findByName(username);
        if(userAttendanceList.size()==0){
            return ResultUtils.error(Message.ATTEND_NOT_EXIST);
        }
        return ResultUtils.success(userAttendanceList);
    }

    /**
     * 签到
     * @param no
     * @param username
     * @param department
     * @return
     */
    @RequestMapping(value = "/checkIn",method = RequestMethod.GET)
    @ResponseBody
    public Result checkIn(String no,String username,String department){
        UserAttendance userAttendance=new UserAttendance();
        String date = new SimpleDateFormat("yyyy-MM-dd")
                .format(Calendar.getInstance().getTime());
        userAttendance.setDate(date);
        userAttendance.setNo(no);
        if(userAttendanceMapper.findByNoAndDate(userAttendance)!=null){
            return ResultUtils.error(Message.Check_IN_TOO_MUCH);
        }

        userAttendance.setUsername(username);
        userAttendance.setDepartment(department);
        userAttendance.setBegin(new SimpleDateFormat("HH:mm:ss")
                .format(Calendar.getInstance().getTime()));
        userAttendance.setState("缺卡");

        userAttendanceMapper.checkIn(userAttendance);
        return ResultUtils.success("签到成功");
    }

    /**
     * 签退
     * @param no
     * @param username
     * @param department
     * @return
     */
    @RequestMapping(value = "/checkOut",method = RequestMethod.GET)
    @ResponseBody
    public Result checkOut(String no,String username,String department){
        UserAttendance userAttendance=new UserAttendance();
        String date = new SimpleDateFormat("yyyy-MM-dd")
                .format(Calendar.getInstance().getTime());
        userAttendance.setDate(date);
        userAttendance.setNo(no);
        //未签到不可签退
        if(userAttendanceMapper.findByNoAndDate(userAttendance)==null){
            return ResultUtils.error(Message.NOT_CHECK_IN);
        }
        if(userAttendanceMapper.findByNoAndDate(userAttendance).getEnd()!=null){
            return ResultUtils.error(Message.CHECK_OUT_TOO_MUCH);
        }
        userAttendance.setEnd(new SimpleDateFormat("HH:mm:ss")
                .format(Calendar.getInstance().getTime()));
        userAttendance.setState("正常");
        userAttendanceMapper.checkOut(userAttendance);
        return ResultUtils.success("签退成功");
    }

    /**
     * 修改考勤
     * @param userAttendance
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    @ResponseBody
    public Result add(UserAttendance userAttendance){
        userAttendanceMapper.update(userAttendance);
        return ResultUtils.success("考勤修改成功");
    }

    @RequestMapping(value = "/editbyno",method = RequestMethod.GET)
    @ResponseBody
    public Result addbyno(UserAttendance userAttendance){
        userAttendanceMapper.updatebyno(userAttendance);
        return ResultUtils.success("考勤修改成功");
    }
    /**
     * 删除一个考勤
     * @param no
     * @param date
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public Result delete(String no,String date){
        UserAttendance userAttendance=new UserAttendance();
        userAttendance.setDate(date);
        userAttendance.setNo(no);
        if(userAttendanceMapper.findByNoAndDate(userAttendance)==null){
                return ResultUtils.error(Message.ATTEND_NOT_EXIST);
        }
        userAttendanceMapper.delete(userAttendance);
        return ResultUtils.success("考勤删除成功");
    }
}
