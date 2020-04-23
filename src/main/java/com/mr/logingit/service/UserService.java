package com.mr.logingit.service;
import com.mr.logingit.entity.LogVO;
import com.mr.logingit.entity.UserVO;
import com.mr.logingit.mapper.UserMapper;
import com.mr.logingit.util.MD5Util;
import com.mr.logingit.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 索鹏辉 on 2019/10/13.
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    //注册
    @Override
    public Map<String, Object> toLogin(UserVO userVO) {
        //生成盐值信息
        String saltCode = ValidateUtil.createCheckCode();
        userVO.setUsalt(saltCode);
        //将密码与盐值信息拼接加密
        String newPwd = MD5Util.string2MD5(userVO.getUpass() + saltCode);
        //将输入密码放入明文密码中
        userVO.setObservePassword(userVO.getUpass());
        userVO.setUpass(newPwd);
        //添加新密码
        Map<String,Object> retMap = new HashMap<>();
        int status = userMapper.toLogin(userVO);
        retMap.put("errorCode",status);
        return retMap;
    }

    //增加日志信息数据
    @Override
    public void addLog(String uname, String upass, String format) {
        Map<String,Object> map=new HashMap<>();
        map.put("uname",uname);
        map.put("upass",upass);
        map.put("format",format);
       int i= userMapper.addLog(map);
    }

    //展示用户登录日志信息
    @Override
    public List<LogVO> findLog(LogVO logvo) {
        return userMapper.findLog();
    }

    //验证账号是否存在
    @Override
    public Map<String, Object> findByName(String uname) {
        Map<String,Object> retMap = new HashMap<>();
        UserVO userVO = userMapper.findByName(uname);
        //200可以注册
        if (null==userVO){
            retMap.put("errorCode",200);
            return retMap;
        }
        if (null!=userVO){
            retMap.put("errorCode",201);
        }
        return retMap;
    }

    //登陆
    @Override
    public Map<String, Object> loginInfo(HttpServletRequest req, UserVO userVO) {
        Map<String,Object> retMap = new HashMap<>();
       String checkCode = (String) req.getSession().getAttribute("CheckCode");
       //第一步验证验证码是否一致
        if (userVO==null||StringUtils.isEmpty(checkCode)||StringUtils.isEmpty(userVO.getCheckCode())||!checkCode.equals(userVO.getCheckCode())){
            //201 验证码不通过
            retMap.put("errorCode",201);
            return retMap;
        }
        //验证数据库中是否有该账号
        //获取去数据库账号
        UserVO userVoold = userMapper.findByName(userVO.getUname());
        if (null==userVoold){
            //202行号或密码错误
            retMap.put("errorCode",202);
            return retMap;
        }
        //验证验证码是否匹配
        String checkPass = MD5Util.string2MD5(userVO.getUpass() + userVoold.getUsalt());
        if(StringUtils.isEmpty(checkPass)||StringUtils.isEmpty(userVoold.getUpass())||!checkPass.equals(userVoold.getUpass())){
            //203账号或密码错误
            retMap.put("errorCode",203);
            return retMap;
        }
        //200通过
        retMap.put("errorCode",200);
        //登陆成功后保存到session
        req.getSession().setAttribute("userVO",userVoold);
        return retMap;

    }

}
