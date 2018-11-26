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

import com.decathlon.entities.Product;
import com.decathlon.entities.Store;
import com.decathlon.repository.ProductRepository;
import com.decathlon.repository.StoreRepository;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StoreRepository storeRepository;

	@GetMapping("/products")
	public List<Product> retriveAllProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/products/{productId}")
	public Product retriveProduct(@PathVariable Integer productId)
			throws Exception {

		Optional<Product> product = productRepository.findById(productId);
		if (!product.isPresent()) {
			throw new Exception("productId" + productId);
		}
		return product.get();
	}

	@PostMapping("/{storeId}/products")
	public ResponseEntity<Product> createProduct(@PathVariable Integer storeId,
			@RequestBody Product product) throws Exception {
		Optional<Store> store = storeRepository.findById(storeId);
		if (!store.isPresent()) {
			throw new Exception("storeId" + storeId);
		}
		product.setAssociatedStores(store.get());
		Product savedProdcut = productRepository.save(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{productId}")
				.buildAndExpand(savedProdcut.getProductId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/products/{productId}")
	public void deleteProduct(@PathVariable Integer productId) throws Exception {
		productRepository.deleteById(productId);
	}
	
}
