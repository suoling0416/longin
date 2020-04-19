package com.mr.logingit.util;

import com.mr.logingit.entity.CommonConstant;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

/**
 * Created by yangx on 2019/8/29.
 * 发送各种手机验证码
 */
public class MessageUtil {

    public static String voiceSend(){
        // just replace key here
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
                "api","key-551e9de80bfa1d078cada7d501497bc8"));
        WebResource webResource = client.resource(
                "http://voice-api.luosimao.com/v1/verify.json");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("mobile", "18330056250");
        formData.add("code", "321123");
        ClientResponse response =  webResource.type( MediaType.APPLICATION_FORM_URLENCODED ).
                post(ClientResponse.class, formData);
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        //System.out.print(textEntity);
        //System.out.print(status);
        return textEntity;
    }


    public static String msgSend(String msgCode, String phoneNum, HttpServletRequest req){
        // just replace key here
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
                "api","key-aa1546b53ab575576977dfe0478c951f"));
        WebResource webResource = client.resource(
                "http://sms-api.luosimao.com/v1/send.json");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("mobile", phoneNum);
        formData.add("message", "验证码："+msgCode+"【铁壳测试】");
        ClientResponse response =  webResource.type( MediaType.APPLICATION_FORM_URLENCODED ).
                post(ClientResponse.class, formData);
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        // 将验证码放入session中
        req.getSession().setAttribute(CommonConstant.MSG_CODE,msgCode);
        //System.out.print(textEntity);
        //System.out.print(status);
        return textEntity;
    }

}
