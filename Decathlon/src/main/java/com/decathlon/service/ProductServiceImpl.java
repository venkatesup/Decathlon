package com.decathlon.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decathlon.dto.ProductDto;
import com.decathlon.entities.Product;
import com.decathlon.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<ProductDto> fetchProductDetailsByStoreId(Integer productId,
			Integer storeId) {

		List<ProductDto> productDtos = null;
		// TODO : Need to fetch storeid details using feign client
		
		List<Product> productList = productRepository.fetchProductDetailsByStoreId(
				productId, storeId);

		if(productList!=null && !productList.isEmpty()){
			
			productDtos=productList.stream().map(pro -> new ProductDto(pro.getProductId(),pro.getProductName(),
										pro.getProductDescription(),pro.getProductLevel(),pro.getProductSport()) )
								.collect(Collectors.toList());
		}
		return productDtos;
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
