package com.codetest.spring.service;

import org.springframework.web.client.RestTemplate;

import com.codetest.spring.controller.SalesMessageRestURIConstants;
import com.codetest.spring.message.model.Adjustment;
import com.codetest.spring.message.model.Sale;
import com.codetest.spring.message.model.Sales;

/** This test class has a list of REST end point tests.
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
public class TestSpringRestExample {

	public static final String SERVER_URI = "http://localhost:8080/SalesMessageProcessorService";
	
	public static void main(String args[]){
		
		testCreateSale();
		System.out.println("*****");
		testCreateSales();
		System.out.println("*****");
		testCreateAdjustment();
		System.out.println("*****");

	}


	private static void testCreateSale() {
		RestTemplate restTemplate = new RestTemplate();
		Sale sale = new Sale();
		sale.setProductType("APPLE"); sale.setValue(10);
		Sale response = restTemplate.postForObject(SERVER_URI+SalesMessageRestURIConstants.CREATE_SALE_RECORD, sale, Sale.class);
		printSaleData(response);
	}
	
	private static void testCreateSales() {
		RestTemplate restTemplate = new RestTemplate();
		Sale sale = new Sale();
		sale.setProductType("APPLE"); sale.setValue(10);
		Sales sales = new Sales();
		sales.setSale(sale);sales.setCount(10);
		Sales response = restTemplate.postForObject(SERVER_URI+SalesMessageRestURIConstants.CREATE_SALES_RECORD, sales, Sales.class);
		printSalesData(response);
	}
	
	private static void testCreateAdjustment() {
		RestTemplate restTemplate = new RestTemplate();
		Adjustment adjustment = new Adjustment();
		adjustment.setProductType("APPLE"); adjustment.setValue(10);adjustment.setOperation("ADD");
		Adjustment response = restTemplate.postForObject(SERVER_URI+SalesMessageRestURIConstants.ADJUST_SALES_RECORD, adjustment, Adjustment.class);
		printAdjustmentData(response);
	}


	
	public static void printSaleData(Sale sale){
		System.out.println("productType="+sale.getProductType()+",Vale="+sale.getValue());
	}
	
	public static void printSalesData(Sales sales){
		System.out.println("sale="+sales.getSale()+",count="+sales.getCount());
	}
	
	public static void printAdjustmentData(Adjustment adjustment){
		System.out.println("productType="+adjustment.getProductType()+",Vale="+adjustment.getValue()+",Operation="+adjustment.getOperation());
	}
}
