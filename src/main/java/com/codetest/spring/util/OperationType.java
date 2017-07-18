package com.codetest.spring.util;

/** 
* This enumeration lists all the supported operation types.
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
public enum OperationType {

	ADD("ADD"),
    SUBTRACT("SUBTRACT"),
    MULTIPLY("MULTIPLY");

    private String operationTypeDesc;

    OperationType(String operationTypeDesc) {
        this.operationTypeDesc = operationTypeDesc;
    }
    
    public String getOperationTypeDesc() {
        return this.operationTypeDesc;
    }

}
