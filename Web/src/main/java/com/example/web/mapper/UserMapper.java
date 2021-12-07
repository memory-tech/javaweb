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
    public List<User> findAll();
    public void addOne(User user);
    public User findById(int id);
    public void deleteOne(int id);
    public void updateOne(User user);
}