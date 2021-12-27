package com.example.web.enm;

/**
 * @Author Memory
 * @Date 2021/11/8 21:20
 * @Version 1.0
 */

//返回的信息
public enum Message {
    USER_NOT_EXIST(1,"该员工不存在"),
    ATTEND_NOT_EXIST(2, "没有该员工的考勤情况"),
    Check_IN_TOO_MUCH(4, "今日已签到不可重复签到"),
    NOT_CHECK_IN(3, "未签到，不可签退"),
    USER_ERR_PASS(9, "用户名或密码错误！"),
    CHECK_OUT_TOO_MUCH(5,"不可多次签退"),
    DIS_ATTEND_NOT_EXIST(13,"该员工没有缺卡情况"),
    APPLY_IS_EXIST(14,"申请已存在，不可重复提交"),
    PHONE_NOT_LEGAL(16,"电话不合法！"),
    EMAIL_NOT_LEGAL(17,"邮箱不合法！"),
    CHARACTER_EMPTY(18,"有空字符串"),
    HAS_CHINESE(19,"有中文！"),
    PASSWORD_STYLE(20,"密码必须有字母和数字!"),
    CARDID_NOT_LEGAL(21,"卡号不合法!"),
    IMG_ERROR(22,"头像修改失败！"),
    EXIT_SUCCESS(23,"退出成功！"),
    FILE_DOWNLOAD_ERROR(24,"文件下载失败！"),
    EMPLOY_NOT_COMMENT(25,"未对该员工进行评价！"),
    ;
    private Integer code;
    private String message;

    Message(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
