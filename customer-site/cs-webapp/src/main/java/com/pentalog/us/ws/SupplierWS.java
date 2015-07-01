package com.pentalog.us.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pentalog.us.model.Supplier;
import com.pentalog.us.service.SupplierService;

@Component
@Path("/suppliers")
public class SupplierWS {

	@Autowired 
	SupplierService supplierService;
	
	@POST
	@Path("/sync")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void syncSuppliers(List<Supplier> suppliers){
		supplierService.syncSuppliers(suppliers);
	}
	
}
