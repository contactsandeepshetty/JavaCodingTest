package com.codetest.spring.service;

import com.codetest.spring.exception.BaseAppException;
import com.codetest.spring.message.model.Adjustment;
import com.codetest.spring.message.model.Sale;
import com.codetest.spring.message.model.Sales;

/** This interface defines the methods of sales message processor
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
public interface ISalesMessageService {

	/**
	 * This method processes Message Type-1 (Sale)
	 * @param sale
	 * @return
	 * @throws BaseAppException
	 */
	public Sale createSaleRecord(Sale sale) throws BaseAppException;

	/**
	 * This method processes Message Type-2 (Sales)
	 * @param sale
	 * @return
	 * @throws BaseAppException
	 */
	public Sales createSalesRecord(Sales sales) throws BaseAppException;

	/**
	 * This method processes Message Type-3 (Adjustment)
	 * @param sale
	 * @return
	 * @throws BaseAppException
	 */
	public Adjustment adjustSalesRecord(Adjustment adjustment) throws BaseAppException;

}
