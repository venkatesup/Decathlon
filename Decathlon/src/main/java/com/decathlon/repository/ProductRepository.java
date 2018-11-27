package com.decathlon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.decathlon.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "Select * from product where product_id=?1 and associated_stores=?2 ", nativeQuery = true)
	List<Product> fetchProductDetailsByStoreId(Integer productId,
			Integer storeId);
	

}
