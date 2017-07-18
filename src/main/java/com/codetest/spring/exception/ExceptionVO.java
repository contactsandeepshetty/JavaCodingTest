package com.codetest.spring.exception;

/**
* Exception VO is a class that holds error codes and error descriptions for a given user defined exception. 
*
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
public class ExceptionVO {
	
	private String errorCode; 
	private String errorMessage; 	

	/**
	 * Constructor with error stuff 
	 * 
	 * @param ec error code
	 * @param em error message
	 */
	ExceptionVO(String ec, String em) {
		this.errorCode = ec;
		this.errorMessage = em;
	}
	
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}	
	
	/**
	 * The method is used to get a String object representing the value of the Object.
	 */
	@Override
	public String toString() {
		return "ExceptionVO [errorCode=" + errorCode + ", errorMessage="
				+ errorMessage + "]";
	}
	
}
