package com.example.web.mapper;

import com.example.web.domain.UserAttendance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author Memory
 * @Date 2021/12/7 11:01
 * @Version 1.0
 */
@Mapper
public interface UserAttendanceMapper {
    //查询全部考勤情况
    public List<UserAttendance> findAll();
    //根据个人no查询考勤情况
    public List<UserAttendance> findByNo(String no);
    //根据名字查看考勤情况
    public List<UserAttendance> findByName(String username);
    //员工查找自己的缺卡情况
    public List<UserAttendance> findDisAttend(String no);
    //根据日期和no查询今天是否打过卡
    public UserAttendance findByNoAndDate(UserAttendance userAttendance);
    //签到
    public void checkIn(UserAttendance userAttendance);
    //签退
    public void checkOut(UserAttendance userAttendance);
    //修改考勤情况
    public void update(UserAttendance userAttendance);
    public void updatebyno(UserAttendance userAttendance);
    //删除一个考勤
    public void delete(UserAttendance userAttendance);

    public int attendance(String state);
}
