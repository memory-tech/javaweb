package com.example.web.mapper;

import com.example.web.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author Memory
 * @Date 2021/12/2 13:21
 * @Version 1.0
 */
@Mapper
public interface UserMapper {
    //查询全部
    public List<User> findAll();
    //添加一个员工
    public void addOne(User user);
    //根据id查找员工
    public User findById(int id);
    //根据no查找员工
    public User findByNo(String no);
    //根据id删除员工
    public void deleteOne(int id);
    //根据no删除员工
    public void deleteByNo(String no);
    //更新员工信息
    public void updateOne(User user);

    public int findSex(String sex);
}