package com.decathlon.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.decathlon.dto.ProductDto;
import com.decathlon.dto.StoresDto;
import com.decathlon.entities.Product;
import com.decathlon.entities.Store;
import com.decathlon.repository.ProductRepository;
import com.decathlon.service.ProductService;
import com.decathlon.service.StoreServiceFeignProxy;

@RestController
@RequestMapping(value = "/v1/public/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StoreServiceFeignProxy storeServiceProxy;

	// @Autowired
	// private StoreRepository storeRepository;

	@Autowired
	private ProductService productService;

	// TODO: Need to fetch storedetails using feign client
	@PostMapping("/{storeId}/products")
	public ResponseEntity<Product> createProduct(@PathVariable Integer storeId,
			@RequestBody Product product) throws Exception {

		StoresDto storesDto = storeServiceProxy.findByStoreId(storeId);
		product.setAssociatedStores(importDo(storesDto));
		Product savedProdcut = productRepository.save(product);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{productId}")
				.buildAndExpand(savedProdcut.getProductId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public List<ProductDto> retriveAllProducts() {
		return productService.fetchAllProducts();
	}

	@GetMapping("/{productId}")
	public ProductDto retriveProduct(
			@PathVariable("productId") Integer productId) throws Exception {
		StoresDto storesDto = storeServiceProxy.findByStoreId(productId);
		System.err.println("stores id0" + storesDto.getStoreId() + "name "
				+ storesDto.getStoreName());
		return productService.fetchProductDetailsById(productId);
	}

	@DeleteMapping("/{productId}")
	public void deleteProduct(@PathVariable("productId") Integer productId)
			throws Exception {

		productService.deleteProductDetailsById(productId);

	}

	private Store importDo(StoresDto storesDto) {
		Store store = new Store();
		store.setStoreCity(storesDto.getStoreCity());
		store.setStoreName(storesDto.getStoreName());
		store.setStoreId(storesDto.getStoreId());
		return store;
	}

}
