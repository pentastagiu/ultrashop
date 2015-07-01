package com.pentalog.sc.job;

import org.springframework.beans.factory.annotation.Autowired;

import com.pentalog.sc.service.SupplierService;

public class SchedulerJobHandler {

	@Autowired
	SupplierService supplierService;
	
	public void syncSuppliers(){
		supplierService.syncSuppliers();
	}
}
