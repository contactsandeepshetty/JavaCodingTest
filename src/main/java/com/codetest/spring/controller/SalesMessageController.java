package com.codetest.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codetest.spring.exception.BaseAppException;
import com.codetest.spring.exception.ExceptionConstants;
import com.codetest.spring.exception.ExceptionVO;
import com.codetest.spring.message.model.Adjustment;
import com.codetest.spring.message.model.Sale;
import com.codetest.spring.message.model.Sales;
import com.codetest.spring.service.ISalesMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/** 
* Sales and Messages 
*	1. A sale has a product type field and a value – you should choose sensible types for these. 
*	2. Any number of different product types can be expected. There is no fixed set. 
*	3. A message notifying you of a sale could be one of the following types 
*		o Message Type 1 – contains the details of 1 sale E.g apple at 10p 
*		o Message Type 2 – contains the details of a sale and the number of occurrences of that sale.E.g 20 sales of apples at 10p each. 
*		o Message Type 3 – contains the details of a sale and an adjustment operation to be applied to all stored sales of this product type. 
*		                   Operations can be add, subtract, or multiply e.g Add 20p apples would instruct your application to add 20p to each sale of apples you have recorded. 
*
* This controller class provides the REST end point for sales message processor
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
@Controller
public class SalesMessageController {
	
	private static final Logger logger = LoggerFactory.getLogger(SalesMessageController.class);
	
	@Autowired
	ISalesMessageService salesService;
	
	
	/**
	 * This method provides a REST end point for processing Message Type 1[contains the details of 1 sale]
	 * @param request
	 * @param response
	 * @param sale
	 * @return
	 */
	@RequestMapping(value = SalesMessageRestURIConstants.CREATE_SALE_RECORD, method = RequestMethod.POST)
	public ResponseEntity<String> createSaleRecord(HttpServletRequest request, HttpServletResponse response, @RequestBody Sale sale) {		
		logger.info("Start createSaleRecord."+ sale.toString());
		try{
		sale = salesService.createSaleRecord(sale);
		}catch(BaseAppException e){			
			logger.error("Exception occured in SalesController.createSaleRecord(): " + e.getMessage(), e);
			return new ResponseEntity<String>(handleException(e),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(convertObjToJSON(sale), HttpStatus.OK);
	}	
	
	/**
	 * This method provides a REST end point for processing Message Type 2[contains the details of a sale and the number of occurrences of that sale]
	 * @param request
	 * @param response
	 * @param sales
	 * @return
	 */
	@RequestMapping(value = SalesMessageRestURIConstants.CREATE_SALES_RECORD, method = RequestMethod.POST)
	public ResponseEntity<String> createSalesRecord(HttpServletRequest request, HttpServletResponse response, @RequestBody Sales sales) {
		logger.info("Start createSalesRecord."+ sales.toString());
		try{
		sales = salesService.createSalesRecord(sales);
		}catch(BaseAppException e){			
			logger.error("Exception occured in SalesController.createSalesRecord(): " + e.getMessage(), e);
			return new ResponseEntity<String>(handleException(e),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(convertObjToJSON(sales), HttpStatus.OK);
	}
	
	/**
	 * This method provides a REST end point for processing Message Type 3[contains the details of a sale and an adjustment operation to be applied to all stored sales of this product type. 
	 * Operations can be add, subtract, or multiply]
	 * @param request
	 * @param response
	 * @param adjustment
	 * @return
	 */
	@RequestMapping(value = SalesMessageRestURIConstants.ADJUST_SALES_RECORD, method = RequestMethod.POST)
	public ResponseEntity<String> adjustSalesRecord(HttpServletRequest request, HttpServletResponse response, @RequestBody Adjustment adjustment) {		
		logger.info("Start adjustSalesRecord."+ adjustment.toString());
		try{
		adjustment = salesService.adjustSalesRecord(adjustment);
		}catch(BaseAppException e){
			logger.error("Exception occured in SalesController.adjustSalesRecord(): " + e.getMessage(), e);
			return new ResponseEntity<String>(handleException(e),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(convertObjToJSON(adjustment), HttpStatus.OK);
	}
	
	/**
	 * This method converts a instance of POJO into JSON string. 
	 * @param obj
	 * @return
	 */
	private String convertObjToJSON(Object obj){
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "";
		try{
		jsonInString = mapper.writeValueAsString(obj);
		}catch(Exception e)
		{
			logger.error("Exception occured in SalesController.convertObjToJSON(): " + e.getMessage(), e);
		}
		return jsonInString;
	}
	
	/**
	 * This method converts a instance of Throwable into JSON string. The JSON string returned shall include erroCode and errorDescription 
	 * @param e
	 * @return
	 */
	private String handleException(Throwable e)
	{	String jsonErrorString = null;	
		ObjectMapper mapper = new ObjectMapper();
		if (e instanceof BaseAppException)
		{
			BaseAppException bae = (BaseAppException)e;
			ObjectNode object = mapper.createObjectNode();
			object.put("errorCode", bae.getErrorCode());
			object.put("errorMessage",bae.getErrorMessage());
			jsonErrorString = object.toString();
		}
		else
		{
			ExceptionVO exceptionVO = ExceptionConstants.getExceptionInfo(ExceptionConstants.UNKNOWN_ERROR);
			ObjectNode object = mapper.createObjectNode();
			object.put("errorCode", exceptionVO.getErrorCode());
			object.put("errorMessage",exceptionVO.getErrorMessage());
			jsonErrorString = object.toString();
		}		
		return jsonErrorString;
	}
}
