package com.codetest.spring.util;

/** 
* This enumeration lists all the supported product types.
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
public enum ProductType {

	APPLE("APPLE"),
    ORANGE("ORANGE");

    private String productTypeDesc;

    ProductType(String productTypeDesc) {
        this.productTypeDesc = productTypeDesc;
    }
    
    public String getProductTypeDesc() {
        return this.productTypeDesc;
    }

}
