package com.codetest.spring.exception;

/**
* Exception constants is a class that keeps a collection of all user defined exceptions, error codes and error descriptions. 
*
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
public enum ExceptionConstants {
	
	UNKNOWN_ERROR,
	SALES_SERVICE_PAUSED;

	private static String SALESSERVERRORCODE001= "Unknown error.";
	private static String SALESSERVERRORCODE002= "Sales service is paused.";
	
	/**
	 * For a given friendly name of exception enum, receive all error & resolution information
	 * 
	 * @param ec error code
	 * @return ExceptionVO with all information required to log or send error above layers
	 */
	public static ExceptionVO getExceptionInfo(ExceptionConstants ec) {
		
		switch(ec) {
		
		case SALES_SERVICE_PAUSED: return new ExceptionVO("SALESSERVERRORCODE002",SALESSERVERRORCODE002);
		
 		default: return new ExceptionVO("SALESSERVERRORCODE001",SALESSERVERRORCODE001);
 		
		}
	}
}



