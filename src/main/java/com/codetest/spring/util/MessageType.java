package com.codetest.spring.util;

import com.codetest.spring.message.model.Adjustment;
import com.codetest.spring.message.model.Sale;
import com.codetest.spring.message.model.Sales;

/** 
* This enumeration lists all the supported message types.
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
public enum MessageType {

	SALE(Sale.class.toString()),
    SALES(Sales.class.toString()),
    ADJUSTMENT(Adjustment.class.toString());

    private String messageTypeDesc;

    MessageType(String messageTypeDesc) {
        this.messageTypeDesc = messageTypeDesc;
    }
    
    public String getMessageTypeDesc() {
        return this.messageTypeDesc;
    }

}
