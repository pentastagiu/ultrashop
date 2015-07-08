package com.pentalog.sc.job;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.pentalog.sc.service.OrderService;

public class JobFactory {

    /**
     *  The order service - contains methods used to fetch data regarding orders from the DAO layer.
     */
    @Autowired
    private OrderService orderService;
    
    
    
    public void checkForDelivredOrders(){
        Date lastDateChecked = null;
        
        lastDateChecked.setTime(999999);
        
        orderService.checkForDelivredOrders(lastDateChecked);
    }
}
