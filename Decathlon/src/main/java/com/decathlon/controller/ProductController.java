package com.decathlon.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.decathlon.dto.ProductDto;
import com.decathlon.entities.Product;
import com.decathlon.entities.Store;
import com.decathlon.repository.ProductRepository;
import com.decathlon.repository.StoreRepository;
import com.decathlon.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private ProductService productService;

	
	@PostMapping("/{storeId}/products")
	public ResponseEntity<Product> createProduct(@PathVariable Integer storeId, @RequestBody Product product)
			throws Exception {
		
		Optional<Store> store = storeRepository.findById(storeId);
		
		if (!store.isPresent()) {
			throw new Exception("storeId" + storeId);
		}
		
		product.setAssociatedStores(store.get());
		Product savedProdcut = productRepository.save(product);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}")
				.buildAndExpand(savedProdcut.getProductId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/products")
	public List<ProductDto> retriveAllProducts() {
		
		return productService.fetchAllProducts();
	
	}
	
	@GetMapping("/products/{productId}")
	public ProductDto retriveProduct(@PathVariable("productId") Integer productId) throws Exception {

		return productService.fetchProductDetailsById(productId);
	}


	@DeleteMapping("/products/{productId}")
	public void deleteProduct(@PathVariable("productId") Integer productId) throws Exception {
		
		productService.deleteProductDetailsById(productId);
		
	}
	
	
}
