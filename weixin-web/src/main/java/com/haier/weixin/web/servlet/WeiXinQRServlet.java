package com.haier.weixin.web.servlet;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.haier.common.httpclient.HEHttpClients;
import com.haier.common.response.PropertiesLoaderUtils;
import com.haier.weixin.web.utils.ResponseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Administrator on 2016/6/9.
 */
public class WeiXinQRServlet extends HttpServlet{
    private String serviceUrl;
    private Properties properties;
    @Override
    public void init() throws ServletException{
        try {
            properties= PropertiesLoaderUtils.loadAllProperties("config.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        doPost(req,resp);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        String oidContent = req.getParameter("oidContent");

        if(Strings.isNullOrEmpty(oidContent)){
            ResponseUtils.returnInfo(resp, 500, "{'code':-3,'message':'parameter is null'}");
            return;
        }
        serviceUrl = properties.getProperty("haier.service.qr.getproductinfo.url","http://localhost:9090/api/haier/1.0/qr/getOidProductInfo");
        HashMap param = Maps.newHashMap();
        param.put("oidContent",oidContent);
        try {
            String result = HEHttpClients.httpJsonPostExecute(serviceUrl,param);
            ResponseUtils.returnInfo(resp,200,result);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtils.returnInfo(resp,500,"{'code':-3,'message':'系统异常'}");
        }
    }
}
