package com.codetest.spring.message.model;

import java.io.Serializable;

/**
* Message model for Sales Message Type - 2
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
public class Sales implements Serializable{


	private static final long serialVersionUID = -3159778769961760905L;	
	
	private long count;
	private Sale sale;
	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	@Override
	public String toString() {
		return "Sales [count=" + count + ", sale=" + sale + "]";
	}
	
	
}
