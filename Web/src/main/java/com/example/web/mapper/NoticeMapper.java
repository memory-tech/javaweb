package com.example.web.mapper;

import com.example.web.domain.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author Memory
 * @Date 2021/12/8 17:16
 * @Version 1.0
 */
@Mapper
public interface NoticeMapper {
    public void add(Notice notice);
    public List<Notice> query();
    public void delete(Notice notice);
    public void edit(Notice notice);
}
