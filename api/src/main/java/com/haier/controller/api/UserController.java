package com.haier.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.haier.common.response.ResponseBody;
import com.haier.common.response.ResponseConstantCode;
import com.haier.common.response.ResponseMsg;
import com.haier.controller.BaseController;
import com.haier.domain.User;
import com.haier.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by bright on 16-6-10.
 */
@Controller
@RequestMapping("/haier/1.0/user")
public class UserController extends BaseController{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(path="/register",method= RequestMethod.POST)
    @org.springframework.web.bind.annotation.ResponseBody
    public ResponseBody register(@RequestBody User user){
        logger.debug("===========User Register receive Message======= | User :"+user.toString());
        if(user.check()){
            return new ResponseMsg(ResponseConstantCode.INVALID_PARAMETER_CODE,ResponseConstantCode.INVALID_PARAMETER_DESC);
        }
        try{
            user = userService.addNewUser(user);
        }catch(Exception ex){
            logger.error(ex.getMessage());
            return new ResponseMsg(ResponseConstantCode.INTERNAL_ERROR_CODE,ex.getMessage());
        }
        ResponseMsg message = new ResponseMsg(ResponseConstantCode.SUCCESS_CODE,ResponseConstantCode.SUCCESS_DESC);
        JSONObject info = new JSONObject();
        info.put("user_id",user.getId());
        info.put("access_token",user.getAccessToken());
        message.setInfo(info);
        return message;
    }
    @RequestMapping(path="/login",method= RequestMethod.POST)
    @org.springframework.web.bind.annotation.ResponseBody
    public ResponseBody login(@RequestBody User user){
        logger.debug("===========User Login receive Message======= | User :"+user.toString());
        if(Strings.isNullOrEmpty(user.getUserName()) || Strings.isNullOrEmpty(user.getPassword()) || Strings.isNullOrEmpty(user.getMac())){
            return new ResponseMsg(ResponseConstantCode.INVALID_PARAMETER_CODE,ResponseConstantCode.INVALID_PARAMETER_DESC);
        }
        try{
            user = userService.isExistsUser(user);
            if(user==null){
                return new ResponseMsg(ResponseConstantCode.INTERNAL_ERROR_CODE,"用户名或密码不正确");
            }
        }catch(Exception ex){
            logger.error(ex.getMessage());
            return new ResponseMsg(ResponseConstantCode.INTERNAL_ERROR_CODE,ex.getMessage());
        }
        ResponseMsg message = new ResponseMsg(ResponseConstantCode.SUCCESS_CODE,ResponseConstantCode.SUCCESS_DESC);
        JSONObject info = new JSONObject();
        info.put("user_id",user.getId());
        info.put("access_token",user.getAccessToken());
        message.setInfo(info);
        return message;
    }
    @RequestMapping(path="/logout",method= RequestMethod.POST)
    @org.springframework.web.bind.annotation.ResponseBody
    public ResponseBody logout(@RequestBody User user){
        logger.debug("===========User Logout receive Message======= | User :"+user.toString());
        if(user.getId()==0){
            return new ResponseMsg(ResponseConstantCode.INVALID_PARAMETER_CODE,ResponseConstantCode.INVALID_PARAMETER_DESC);
        }
        try{
            userService.clearAccessToken(user);
        }catch(Exception ex){
            logger.error(ex.getMessage());
            return new ResponseMsg(ResponseConstantCode.INTERNAL_ERROR_CODE,ex.getMessage());
        }
        return getSuccess();
    }
}
