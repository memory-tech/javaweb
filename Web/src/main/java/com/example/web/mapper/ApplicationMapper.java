package com.example.web.mapper;

import com.example.web.domain.Application;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author Memory
 * @Date 2021/12/8 14:26
 * @Version 1.0
 */
@Mapper
public interface ApplicationMapper {
    //提交补卡申请
    public void add(Application application);
    //根据no和date查询补卡申请
    public Application findByNoAndDate(Application application);
    //查询所有补卡申请
    public List<Application> findAll();
    //删除补卡申请
    public void delete(Application application);
}
