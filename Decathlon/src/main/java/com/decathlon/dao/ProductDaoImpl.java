package com.decathlon.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.decathlon.entities.Product;
import com.decathlon.repository.ProductRepository;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> fetchProductDetailsByStoreId(Integer productId,
			Integer storeId) {
		return productRepository.fetchProductDetailsByStoreId(productId,
				storeId);
	}

}
