package com.decathlon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.decathlon.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	// @Query(value =
	// "select pro.* from product pro inner join store sto on pro.associatedStores=sto.store_id where pro.product_id=?1 and sto.store_id=?2",
	// nativeQuery = true)
	@Query(value = "Select * from product where product_id=?1 and associated_stores=?2 ", nativeQuery = true)
	List<Product> fetchProductDetailsByStoreId(Integer productId,
			Integer storeId);
	

}
