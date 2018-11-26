package com.decathlon.dao;

import java.util.List;

import com.decathlon.entities.Product;

public interface ProductDao {

	List<Product> fetchProductDetailsByStoreId(Integer productId,
			Integer storeId);
}
