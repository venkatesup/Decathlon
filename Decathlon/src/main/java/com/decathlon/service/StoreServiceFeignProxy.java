package com.decathlon.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.decathlon.dto.StoresDto;

@FeignClient(name = "decathlon-store", url = "localhost:8003")
public interface StoreServiceFeignProxy {

	@GetMapping("/decathlon-store/v1/public/stores/{storeId}")
	public StoresDto findByStoreId(@PathVariable("storeId") Integer storeId);
}
