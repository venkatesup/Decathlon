package com.decathlon.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ProductDto {

	Integer productId;
	
	@ApiModelProperty(required = true)
	@NotNull(message = "productName can't be null")
	String productName;
	
	@ApiModelProperty(required=true)
	@NotNull(message="productDesc can't be null")
	String productDecription;
	
	@ApiModelProperty(required=true)
	@NotNull(message="productLevel can't be null")
	String productLevel;
	
	@ApiModelProperty(required=true)
	@NotNull(message="productSupport can't be null")
	String productSport;

	public ProductDto() {
	
	}

	public ProductDto(Integer productId,String productName, String productDecription,
			String productLevel, String productSport) {
		super();
		this.productId=productId;
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
