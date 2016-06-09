package com.haier.service.impl;

import com.haier.qrcode.domain.GetOIDProductInfoInput;
import com.haier.qrcode.domain.GetOIDProductInfoOutput;
import com.haier.qrcode.service.QRFacade;
import com.haier.service.QRService;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/9.
 */
@Service("qrService")
public class QRServiceImpl implements QRService{
    @Autowired
    private QRFacade facade;
    @Override
    public GetOIDProductInfoOutput getOIDProductInfo(String oidContent) throws Exception{
        GetOIDProductInfoInput input = new GetOIDProductInfoInput();
        input.setOidContent(oidContent);
        GetOIDProductInfoOutput output = facade.getOIDProductInfo(input);
        return output;
    }
}
