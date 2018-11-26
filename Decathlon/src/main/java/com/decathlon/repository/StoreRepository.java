package com.decathlon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.decathlon.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {

	
}
