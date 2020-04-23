package com.mr.logingit.service;

import com.mr.logingit.entity.LogVO;
import com.mr.logingit.entity.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 索鹏辉 on 2019/10/13.
 */
public interface IUserService {
    //注册
    Map<String,Object> toLogin(UserVO userVO);
    //验证账号是否存在
    Map<String,Object> findByName(String uname);

    //登陆
    Map<String,Object> loginInfo(HttpServletRequest req, UserVO userVO);

    //增加用户日志信息
    void addLog(String uname, String upass, String format);
    //展示用户登录日志信息
    List<LogVO> findLog(LogVO logvo);

}
