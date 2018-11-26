package com.decathlon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decathlon.dao.ProductDao;
import com.decathlon.dto.ProductDto;
import com.decathlon.entities.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public List<ProductDto> fetchProductDetailsByStoreId(Integer productId,
			Integer storeId) {

		List<ProductDto> productDto = new ArrayList<ProductDto>();
		List<Product> productList = productDao.fetchProductDetailsByStoreId(
				productId, storeId);
		// productList.stream().map(productDto -> new
		// ProductDto(productDto.getProductName(),productDto.getProductLevel(),productDto.getProductSport())).collect(Collectors.toList());
		for (Product dbProduct : productList) {
			ProductDto temp = new ProductDto();
			temp.setProductName(dbProduct.getProductName());
			temp.setProductSport(dbProduct.getProductSport());
			temp.setProductLevel(dbProduct.getProductLevel());
			temp.setProductDecription(dbProduct.getProductDescription());
			productDto.add(temp);
		}

		return productDto;
	}

}
