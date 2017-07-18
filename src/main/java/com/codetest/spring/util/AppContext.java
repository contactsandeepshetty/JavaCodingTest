package com.codetest.spring.util;

/** 
* This class acts as application context.
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
public class AppContext {

	private static boolean pauseRequests = false;

	public static boolean isPauseRequests() {
		return AppContext.pauseRequests;
	}

	public static void setPauseRequests(boolean pauseRequests) {
		AppContext.pauseRequests = pauseRequests;
	}	
	
}
