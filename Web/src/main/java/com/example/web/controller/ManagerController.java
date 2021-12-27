package com.example.web.controller;

import com.example.web.domain.Result;
import com.example.web.domain.User;
import com.example.web.domain.UserAttendance;
import com.example.web.enm.Message;
import com.example.web.mapper.UserAttendanceMapper;
import com.example.web.mapper.UserMapper;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Memory
 * @Date 2021/12/7 10:43
 * @Version 1.0
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private UserMapper userMapper;

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
        Calendar now = Calendar.getInstance();
        //String no=now.get(Calendar.YEAR)+now.get(Calendar.MONTH)+now.get(Calendar.DAY_OF_MONTH)+
        //        now.get(Calendar.HOUR_OF_DAY)+now.get(Calendar.MINUTE)+now.get(Calendar.SECOND);
        String noStyle = new SimpleDateFormat("yyyyMMddHHmmSS")
                .format(Calendar.getInstance().getTime());
        user.setNo(noStyle);
        user.setPassword("123456");
        userMapper.addOne(user);
        ArrayList<String> strings = new ArrayList<>();
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
     * 根据no查询员工信息
     * @param no
     * @return
     */
    @RequestMapping(value = "findByNo",method = RequestMethod.GET)
    @ResponseBody
    public Result findByNo(String no){
        User user=userMapper.findByNo(no);
        return ResultUtils.success(user);
    }

    /**
     * 根据id删除一个员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteById",method = RequestMethod.GET)
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
     * 根据no删除一个员工信息
     * @param no
     * @return
     */
    @RequestMapping(value = "deleteByNo",method = RequestMethod.GET)
    @ResponseBody
    public Result deleteByNo(String no){
        User user= userMapper.findByNo(no);
        if(user==null){
            return ResultUtils.error(Message.USER_NOT_EXIST);
        }
        userMapper.deleteByNo(no);
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
        User user0= userMapper.findByNo(user.getNo());
        if(user0==null){
            return ResultUtils.error(Message.USER_NOT_EXIST);
        }
        userMapper.updateOne(user);
        return ResultUtils.success("员工编号"+user.getNo()+"修改成功");
    }

}
