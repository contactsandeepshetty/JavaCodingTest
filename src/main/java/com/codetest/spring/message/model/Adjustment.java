package com.codetest.spring.message.model;

import java.io.Serializable;

/**
* Message model for sales Message Type - 3
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
public class Adjustment implements Serializable{

	private static final long serialVersionUID = -5535736543434506759L;
	
	private String operation;
	private String productType;
	private double value;	
	
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
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
		return "Adjustment [operation=" + operation + ", productType="
				+ productType + ", value=" + value + "]";
	}
	

	
}
