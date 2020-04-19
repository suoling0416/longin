package com.mr.logingit.mapper;

import com.mr.logingit.entity.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by 索鹏辉 on 2019/10/13.
 */
public interface UserMapper {
    //注册
    int toLogin(UserVO userVO);
    //验证账号是否存在
    UserVO findByName(String uname);

    int addLog(Map<String,Object> map);
}
