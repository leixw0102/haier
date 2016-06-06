package com.haier.service.impl;

import com.haier.common.ObjectUtils;
import com.haier.common.exception.ApiException;
import com.haier.dao.OrderDao;
import com.haier.domain.ServiceOrder;
import com.haier.domain.hp.HPAddWoDataRequest;
import com.haier.domain.hp.HPAddWoDataRequestBuilder;
import com.haier.domain.hp.HPAddWoDataResponse;
import com.haier.service.OrderService;
import com.haier.service.hp.HPFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

/**
 * Created by bright on 16-6-5.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private HPFacade hpFacade;
    @Autowired
    private OrderDao orderDao;
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public void saveOrder(ServiceOrder serviceOrder) throws Exception {

        HPAddWoDataRequest request = build(serviceOrder);

        HPAddWoDataResponse json=hpFacade.executeAddWoData(ObjectUtils.toMap(request));
        if(!json.getCode().equals("200")){
            throw new  Exception(json.getMsg()+" hp 对接失败");
        }
        serviceOrder.setOrder_code(json.getData());
        logger.debug(serviceOrder.toString());
        orderDao.save(serviceOrder);
    }

    private HPAddWoDataRequest build(ServiceOrder serviceOrder){
        return HPAddWoDataRequestBuilder.create()
                .setApply_id(serviceOrder.getApply_id()).setProduct_id(serviceOrder.getProduct_id())
                .setService_type(serviceOrder.getService_type()).setRequire_service_date(serviceOrder.getRequire_time().getTime() + "")
                .setCustomer_name(serviceOrder.getCotact_name()).setMobile_phone(serviceOrder.getMobile_phone())
                .setDistrict(serviceOrder.getDistrict()).setAddress(serviceOrder.getService_address()).setRequire_service_desc(serviceOrder.getRequire_service_desc())
                .setService_time(serviceOrder.getService_time()).build();
    }
}
