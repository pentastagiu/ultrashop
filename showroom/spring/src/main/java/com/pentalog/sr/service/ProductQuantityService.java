package com.pentalog.sr.service;

import java.util.List;
import com.pentalog.sr.model.ProductQuantity;

public interface ProductQuantityService {
	
	List<ProductQuantity> getProductQuantity(); 
	
	ProductQuantity saveProductQuantity(ProductQuantity productQuantity);
}
