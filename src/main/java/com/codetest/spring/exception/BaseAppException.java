package com.codetest.spring.exception;

/**
* Base exception class that keeps a chain of parent exceptions. 
*
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
public class BaseAppException extends Exception {

	private static final long serialVersionUID = 5232252651242051106L;
	private String errorCode; 
	private String errorMessage; 
	
	/**
	 * Constructor with only exception as argument
	 *      
	 * @param e
	 */
	public BaseAppException(Exception e) {
		super(e);
	}
	
	/**
	 * Constructor with exception & error stuff
	 * 
	 * @param e throwable exception
	 * @param ec error code
	 * @param em error message
	 */
	public BaseAppException(Exception e, String ec, String em) {
		super(e);
		this.errorCode = ec;
		this.errorMessage = em;
	}
	
	/**
	 * Constructor with error stuff
	 * 
	 * @param ec error code
	 * @param em error message
	 */
	public BaseAppException(String ec, String em) {
		super();
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
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
