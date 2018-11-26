package com.decathlon.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "product_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;

	@Column(name = "product_Name")
	private String productName;

	@Column(name = "product_sport")
	private String productSport;

	@Column(name = "product_Level")
	private String productLevel;

	@Column(name = "product_description")
	private String productDescription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "associatedStores")
	@JsonIgnore
	public Store associatedStores;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductSport() {
		return productSport;
	}

	public void setProductSport(String productSport) {
		this.productSport = productSport;
	}

	public String getProductLevel() {
		return productLevel;
	}

	public void setProductLevel(String productLevel) {
		this.productLevel = productLevel;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Store getAssociatedStores() {
		return associatedStores;
	}

	public void setAssociatedStores(Store associatedStores) {
		this.associatedStores = associatedStores;
	}
	

}
