package com.mr.logingit.entity;

/**
 * Created by 索鹏辉 on 2019/10/13.
 */
public class UserVO {
    private Integer uid;
    private String uname;
    private String upass;

    // 盐值
    private String usalt;
    private Integer ustatus;

    //明文密码
    private String observePassword;

    private String checkCode;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getUsalt() {
        return usalt;
    }

    public void setUsalt(String usalt) {
        this.usalt = usalt;
    }

    public Integer getUstatus() {
        return ustatus;
    }

    public void setUstatus(Integer ustatus) {
        this.ustatus = ustatus;
    }

    public String getObservePassword() {
        return observePassword;
    }

    public void setObservePassword(String observePassword) {
        this.observePassword = observePassword;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upass='" + upass + '\'' +
                ", usalt='" + usalt + '\'' +
                ", ustatus=" + ustatus +
                ", observePassword='" + observePassword + '\'' +
                ", checkCode='" + checkCode + '\'' +
                '}';
    }
}
