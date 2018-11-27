package com.decathlon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decathlon.dao.ProductDao;
import com.decathlon.dto.ProductDto;
import com.decathlon.entities.Product;
import com.decathlon.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductRepository productRepository;

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

	@Override
	public ProductDto fetchProductDetailsById(Integer productId) throws Exception {
		
		ProductDto productDto=new ProductDto();
		
		Optional<Product> productdb = productRepository.findById(productId);
		
		if (!productdb.isPresent()) {
			 
			throw new Exception("productId" + productId);
			
		}else{
			Product product=productdb.get();
			
			productDto.setProductId(product.getProductId());
			productDto.setProductName(product.getProductName());
			productDto.setProductDecription(product.getProductDescription());
			productDto.setProductLevel(product.getProductLevel());
			productDto.setProductSport(product.getProductSport());
		}
		return productDto;
	}

	@Override
	public List<ProductDto> fetchAllProducts() {
		
		List<Product> productList=productRepository.findAll();
		List<ProductDto> productDtos=null;
		
		if(productList!=null && !productList.isEmpty()){
			
			productDtos=productList.stream().map(pro -> new ProductDto(pro.getProductId(),pro.getProductName(),
										pro.getProductDescription(),pro.getProductLevel(),pro.getProductSport()) )
								.collect(Collectors.toList());
		}
		return productDtos;
	}

	@Override
	public void deleteProductDetailsById(Integer productId) throws Exception {
		
		Optional<Product> productdb = productRepository.findById(productId);
		
		if(!productdb.isPresent()){
			
			throw new Exception("Invalid ProductId");
		}
		else{
			productRepository.deleteById(productId);
		}
	}
	
	
}
