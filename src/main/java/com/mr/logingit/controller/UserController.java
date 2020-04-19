package com.mr.logingit.controller;

import com.mr.logingit.entity.CommonConstant;
import com.mr.logingit.entity.MailBean;
import com.mr.logingit.entity.UserVO;
import com.mr.logingit.service.IUserService;
import com.mr.logingit.util.MailUtil;
import com.mr.logingit.util.MessageUtil;
import com.mr.logingit.util.ValidateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private IUserService userSer;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    //邮箱
    @Autowired
    private MailUtil mailUtil;

    //邮箱
    @ResponseBody
    @RequestMapping("sendMail")
    public String sendMail(MailBean mailBean){
        mailUtil.sendSimpleMail(mailBean);
        return "邮件发送成功";
    }




    //主页面
    @RequestMapping("/")
    public String index(){
        return "login";
    }

    //去注册页面
    @RequestMapping("goRegister")
    public String goRegister(){
        return "register";
    }

    //验证账号是否存在
    @ResponseBody
    @RequestMapping("findByName")
    public Map<String,Object> findByName(String uname){
        Map<String,Object> retMap = userSer.findByName(uname);
        return retMap;
    }

    //注册方法
    @ResponseBody
    @RequestMapping("toLogin")
    public Map<String,Object> toLogin(UserVO userVO){
        Map<String,Object> retMap = userSer.toLogin(userVO);
        return retMap;
    }



    //去登陆页面
    @RequestMapping("goLogin")
    public String goLogin(){
        return "login";
    }

    //去主页面
    @RequestMapping("indexs")
    public String indexs(){
        return "index";
    }

    //登陆
    @ResponseBody
    @RequestMapping("/loginInfo")
    public Map<String,Object> loginInfo(HttpServletRequest req, UserVO userVO){
        logger.info("登录的用户信息：用户名："+userVO.getUname()+"密码"+
                userVO.getUpass()+"登录时间："+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        userSer.addLog(userVO.getUname(),userVO.getUpass(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return userSer.loginInfo(req,userVO);
    }

    //图片验证码
    @ResponseBody
    @RequestMapping("readCheckImg")
    public void readCheckImg(HttpServletRequest req, HttpServletResponse resp){
        //将验证码存到缓存中
        HttpSession session = req.getSession();
        Object[] image = ValidateUtil.createImage(resp);
        session.setAttribute("CheckCode",image[0].toString());
    }

    //验证验证码
    @ResponseBody
    @RequestMapping("checkCodeMethod")
    public Map<String,Object> checkCodeMethod(UserVO userVO,HttpServletRequest req){
        Map<String,Object> retMap = new HashMap<>();
        //通过session获取验证码  对比
        String checkCodeOld = (String) req.getSession().getAttribute("CheckCode");
        if (StringUtils.isNotEmpty(checkCodeOld)&&StringUtils.isNotEmpty(userVO.getCheckCode())
                &&checkCodeOld.equals(userVO.getCheckCode())){
            retMap.put("errorCode",200);
            return  retMap;
        }else{
            retMap.put("errorCode",201);
        }
        return retMap;
    }

    //获取验证码
    @ResponseBody
    @RequestMapping("getCheckCode")
    public String getCheckCode(HttpServletRequest req,String phoneNum){
        //调用ValidateUtil 获取msgCode 验证码
        String msgCode = ValidateUtil.getPointCode(6);//生成6位
        //发送结束并获取结果用以返回
        String s = MessageUtil.msgSend(msgCode, phoneNum, req);
        return s;
    }

    //点击失去焦点 验证验证码是否正确
    @ResponseBody
    @RequestMapping("/checkMsgCode")
    public Map<String,Object> checkMsgCode(HttpServletRequest req,String msgCode){
        Map<String,Object> retMap = new HashMap<>();
        //获取session
        String session = (String) req.getSession().getAttribute(CommonConstant.MSG_CODE);
        //对比验证码是否一致
        //验证一致通过
        if (session.equals(msgCode)){
            retMap.put("error",0);
            retMap.put("msg","手机验证码验证通过");
            return retMap;
        }
        //不一致验证失败
        retMap.put("error",1);
        retMap.put("msg","验证不通过");
        return retMap;
    }
}
