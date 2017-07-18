package com.codetest.spring.message.model;

import java.io.Serializable;

/**
* Message model for sales Message Type - 1
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
public class Sale implements Serializable{

	private static final long serialVersionUID = -2524666823628288920L;
	
	private String productType;
	private double value;
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Sale [productType=" + productType + ", value=" + value + "]";
	}
	

	
}
