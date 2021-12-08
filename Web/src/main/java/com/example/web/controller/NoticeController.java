package com.example.web.controller;

import com.example.web.domain.Notice;
import com.example.web.domain.Result;
import com.example.web.mapper.NoticeMapper;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author Memory
 * @Date 2021/12/8 17:24
 * @Version 1.0
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    NoticeMapper noticeMapper;

    private static Notice init(String title,String content){
        Notice notice=new Notice();
        notice.setContent(content);
        notice.setTitle(title);
        return notice;
    }

    /**
     * 发布公告
     * @param title
     * @param content
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    @ResponseBody
    public Result add(String title,String content){
        noticeMapper.add(init(title,content));
        return ResultUtils.success("公告发布成功");
    }

    /**
     * 删除公告
     * @param title
     * @param content
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public Result delete(String title,String content){
        noticeMapper.delete(init(title,content));
        return ResultUtils.success("公告删除成功");
    }

    /**
     * 修改公告
     * @param title
     * @param content
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    @ResponseBody
    public Result edit(String title,String content){
        noticeMapper.edit(init(title,content));
        return ResultUtils.success("公告修改成功");
    }

    /**
     * 查询所有公告
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public Result query(){
        List<Notice> noticeList=noticeMapper.query();
        return ResultUtils.success(noticeList);
    }
}


