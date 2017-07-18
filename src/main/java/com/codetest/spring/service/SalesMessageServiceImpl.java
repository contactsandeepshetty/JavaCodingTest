package com.codetest.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.codetest.spring.dao.DataStore;
import com.codetest.spring.exception.BaseAppException;
import com.codetest.spring.exception.ExceptionConstants;
import com.codetest.spring.exception.ExceptionVO;
import com.codetest.spring.message.model.Adjustment;
import com.codetest.spring.message.model.Sale;
import com.codetest.spring.message.model.Sales;
import com.codetest.spring.util.AppContext;
import com.codetest.spring.util.ProductType;

/** 
* This class provides implementation for the methods of sales message processor
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
@Service
public class SalesMessageServiceImpl implements ISalesMessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(SalesMessageServiceImpl.class);
	
	/**
	 * This method processes Message Type-1 (Sale)
	 * @param sale
	 * @return
	 * @throws BaseAppException
	 */
	@Override
	public Sale createSaleRecord(Sale sale) throws BaseAppException {
		logger.info("Start createSaleRecord."+ sale.toString());
		if(AppContext.isPauseRequests()){
			ExceptionVO vo = ExceptionConstants.getExceptionInfo(ExceptionConstants.SALES_SERVICE_PAUSED);
			throw new BaseAppException(vo.getErrorCode(), vo.getErrorMessage());
		}
		DataStore.getSalesData().get(sale.getProductType()).add(sale);
		updateMessageCounter(sale);		
		return sale;
	}

	/**
	 * This method processes Message Type-2 (Sales)
	 * @param sale
	 * @return
	 * @throws BaseAppException
	 */
	@Override
	public Sales createSalesRecord(Sales sales) throws BaseAppException {
		logger.info("Start createSalesRecord."+ sales.toString());
		if(AppContext.isPauseRequests()){
			ExceptionVO vo = ExceptionConstants.getExceptionInfo(ExceptionConstants.SALES_SERVICE_PAUSED);
			throw new BaseAppException(vo.getErrorCode(), vo.getErrorMessage());
		}
		for (int i = 0; i < sales.getCount(); i++) {
			DataStore.getSalesData().get(sales.getSale().getProductType()).add(sales.getSale());
		}			
		updateMessageCounter(sales);
		return sales;
	}

	/**
	 * This method processes Message Type-3 (Adjustment)
	 * @param sale
	 * @return
	 * @throws BaseAppException
	 */
	@Override
	public Adjustment adjustSalesRecord(Adjustment adjustment) throws BaseAppException {
		logger.info("Start adjustSalesRecord."+ adjustment.toString());
		if(AppContext.isPauseRequests()){
			ExceptionVO vo = ExceptionConstants.getExceptionInfo(ExceptionConstants.SALES_SERVICE_PAUSED);
			throw new BaseAppException(vo.getErrorCode(), vo.getErrorMessage());
		}
		List<Sale> sales = DataStore.getSalesData().get(adjustment.getProductType());
		for (Sale sale : sales) {
			sale.setValue(getNewValue(sale, adjustment));
		}		
		updateMessageCounter(adjustment);
		return adjustment;
	}
	
	/**
	 * This method returns the adjusted value of each sales record.
	 * @param sale
	 * @param adjustment
	 * @return
	 */
	private double getNewValue(Sale sale, Adjustment adjustment) {
		logger.info("Start getNewValue.");
		double adjustedVale = 0;
		switch (adjustment.getOperation()) {
		case "ADD":
			adjustedVale = sale.getValue() + adjustment.getValue();
			break;
		case "SUBTRACT":
			adjustedVale = sale.getValue() - adjustment.getValue();
			break;
		case "MULTIPLY":
			adjustedVale = sale.getValue() * adjustment.getValue();
			break;
		}
		return adjustedVale;
	}
	
	/**
	 * This method updates the message data store. This method logs the summary report for
	 * 	1. After every 10th message received your application should log a report detailing the number of sales of each product and their total value. 
	 *  2. After 50 messages your application should log that it is pausing, stop accepting new messages and log a report of the adjustments that have been made to each sale type while the application was running.
	 * @param sale
	 * @param adjustment
	 * @return
	 */
	private void updateMessageCounter(Object object) {
		logger.info("Start updateMessageCounter."+ object.toString());
		if (object instanceof Sale) {
			DataStore.getSalesMessageData().get(Sale.class.toString()).add(object);
		} else if (object instanceof Sales) {
			DataStore.getSalesMessageData().get(Sales.class.toString()).add(object);
		} else if (object instanceof Adjustment) {
			DataStore.getSalesMessageData().get(Adjustment.class.toString()).add(object);
		}

		long counter = 0;
		Map<String, List<Object>> salesMessageData = DataStore.getSalesMessageData();			
		for (String key :salesMessageData.keySet()) {
			List<Object> list = salesMessageData.get(key);
			counter = counter + list.size();
		}
		
		if ((counter % 10) == 0) {
			logger.info(":::::::::::::::Sales summary of at every 10th message::::::::::::::::::::::::");
			for (ProductType productType : ProductType.values()) {
				List<Sale> sales = DataStore.getSalesData().get(productType.getProductTypeDesc());
				double totalValue = 0;
				for (Sale sale : sales) {
					totalValue = totalValue + sale.getValue();
				}				
				logger.info("Product type : " + productType.toString() +", Total value : " + totalValue);				
			}
			logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		}	
		
		if ((counter % 50) == 0) {

			logger.info("Sales service is pausing.");
			AppContext.setPauseRequests(true);
			Map<String, List<Adjustment>> adjustmentMap = new HashMap<String, List<Adjustment>>();
			for(ProductType productType : ProductType.values())
		    {
			    adjustmentMap.put(productType.getProductTypeDesc(), new ArrayList<Adjustment>());
		    }
			List<Object> adjustmensts = DataStore.getSalesMessageData().get(Adjustment.class.toString());
			for (Object adjustmentObject : adjustmensts) {
				Adjustment adj = (Adjustment)adjustmentObject;
				adjustmentMap.get(adj.getProductType()).add(adj);
			}				
			logger.info(":::::::::::::::Adjustment summary of at every 50th message::::::::::::::::::::::::");
			for (String key : adjustmentMap.keySet()) {
				logger.info("Product type : " + key +", Number of adjustments : " + adjustmentMap.get(key).size());			
			}
			logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			AppContext.setPauseRequests(false);
			logger.info("Sales service is resuming.");
		}
	}	

}
