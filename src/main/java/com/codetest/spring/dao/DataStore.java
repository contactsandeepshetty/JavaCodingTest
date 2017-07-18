package com.codetest.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codetest.spring.message.model.Sale;
import com.codetest.spring.util.MessageType;
import com.codetest.spring.util.ProductType;

/** 
* This class acts as in memory database. The data store has two partitions. Partition-1 stores the sales data. Partition-1 stores the sales messages. 
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
public class DataStore {

		/*
		 * Map to store sales. This map shall hold sales data of all the products.
		 */
		private static Map<String, List<Sale>> salesData = new HashMap<String, List<Sale>>();
		/*
		 * Map to store messages.  This map shall hold messages all the messages.
		 */
		private static Map<String, List<Object>> salesMessageData = new HashMap<String, List<Object>>();		
		
		static
	    {
			/*
			 * Initialize sales data store
			 */
	        for(ProductType productType : ProductType.values())
	        {
	        	salesData.put(productType.getProductTypeDesc(), new ArrayList<Sale>());
	        }    
	        /*
	         * Initialize message data store
	         */
	        for(MessageType messageType : MessageType.values())
	        {
	        	salesMessageData.put(messageType.getMessageTypeDesc(), new ArrayList<Object>());
	        } 
	       
	    }

		/**
		 * This returns a map of sales data
		 * @return
		 */
		public static Map<String, List<Sale>> getSalesData() {
			return salesData;
		}

		/**
		 * This returns a map of sales messages
		 * @return
		 */
		public static Map<String, List<Object>> getSalesMessageData() {
			return salesMessageData;
		}
				
}
