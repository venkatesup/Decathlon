package com.decathlon.dto;

public class ProductDto {

	Integer productId;
	String productName;
	String productDecription;
	String productLevel;
	String productSport;

	public ProductDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductDto(Integer productId,String productName, String productDecription,
			String productLevel, String productSport) {
		super();
		this.productName = productName;
		this.productDecription = productDecription;
		this.productLevel = productLevel;
		this.productSport = productSport;
	}
	
	public ProductDto(String productName, String productDecription,
			String productLevel, String productSport) {
		super();
		this.productName = productName;
		this.productDecription = productDecription;
		this.productLevel = productLevel;
		this.productSport = productSport;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDecription() {
		return productDecription;
	}

	public void setProductDecription(String productDecription) {
		this.productDecription = productDecription;
	}

	public String getProductLevel() {
		return productLevel;
	}

	public void setProductLevel(String productLevel) {
		this.productLevel = productLevel;
	}

	public String getProductSport() {
		return productSport;
	}

	public void setProductSport(String productSport) {
		this.productSport = productSport;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
}
