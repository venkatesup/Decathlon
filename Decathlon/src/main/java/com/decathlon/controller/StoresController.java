package com.decathlon.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.decathlon.dto.ProductDto;
import com.decathlon.entities.Product;
import com.decathlon.entities.Store;
import com.decathlon.repository.StoreRepository;
import com.decathlon.service.ProductService;

@RestController
@RequestMapping(value = "/v1/public")
public class StoresController {

	private static final Logger logger = Logger
			.getLogger(StoresController.class);

	@Autowired
	ProductService productService;

	@Autowired
	private StoreRepository storeRepository;

	@GetMapping(value = "/stores/{storeId}/products/{productId}")
	public @ResponseBody List<ProductDto> fetchProductDetailsByStoreId(
			@PathVariable("storeId") Integer storeId,
			@PathVariable("productId") Integer productId) {
		logger.log(Level.INFO,
				"callling service method fetchProductDetailsByStoreId"
						+ storeId);
		return productService.fetchProductDetailsByStoreId(productId, storeId);
	}

	@GetMapping("/stores/{storeId}/products")
	public List<Product> fetchAllProductsByStoreId(@PathVariable Integer storeId)
			throws Exception {
		Optional<Store> store = storeRepository.findById(storeId);
		if (!store.isPresent()) {
			throw new Exception("storeId" + storeId);
		}
		List<Product> products = store.get().getProducts();
		return products;
	}

	@PostMapping("/stores")
	public ResponseEntity<Store> createStore(@RequestBody Store store) {
		Store savedStore = storeRepository.save(store);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{storeId}").buildAndExpand(savedStore.getStoreId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/stores")
	public List<Store> retriveAllStores() {
		return storeRepository.findAll();
	}

}
