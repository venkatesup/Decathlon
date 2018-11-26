package com.decathlon.service;

import java.util.List;

import com.decathlon.dto.ProductDto;

public interface ProductService {
	
	List<ProductDto> fetchProductDetailsByStoreId(Integer productId,Integer storeId);
	
}
