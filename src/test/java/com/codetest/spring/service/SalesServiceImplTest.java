package com.codetest.spring.service;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.codetest.spring.exception.BaseAppException;
import com.codetest.spring.exception.ExceptionConstants;
import com.codetest.spring.message.model.Adjustment;
import com.codetest.spring.message.model.Sale;
import com.codetest.spring.message.model.Sales;
import com.codetest.spring.util.AppContext;

/** This test class has a list of JUnits with PowerMocks
* @author  Sandeep Shetty
* @version 1.0
* @since   2017-07-18 
*/
@RunWith(PowerMockRunner.class)
@PrepareForTest({SalesMessageServiceImpl.class, ExceptionConstants.class, AppContext.class})
public class SalesServiceImplTest {
	
	private SalesMessageServiceImpl salesServiceImpl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		salesServiceImpl = new SalesMessageServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		salesServiceImpl = null;
	}
	
	@Test
	public void  testCreateSaleRecord() throws Exception{
		
		 SalesMessageServiceImpl salesServiceImpl = PowerMock.createPartialMock(SalesMessageServiceImpl.class, "updateMessageCounter");
		 Sale sale = new Sale();
		 sale.setProductType("APPLE"); 
		 sale.setValue(10);
		 
		 PowerMock.mockStatic(AppContext.class);
		 EasyMock.expect(AppContext.isPauseRequests()).andReturn(false);	 
		 
		 
		 PowerMock.expectPrivate(salesServiceImpl, "updateMessageCounter", sale);
		 EasyMock.expectLastCall();			
		
		
		 PowerMock.replayAll();
		 salesServiceImpl.createSaleRecord(sale);
		 PowerMock.verifyAll();
	}
	
	@Test(expected=BaseAppException.class)
	public void  testCreateSaleRecord_SalesOrderProcessingIsPaused() throws Exception{
		
		 SalesMessageServiceImpl salesServiceImpl = PowerMock.createPartialMock(SalesMessageServiceImpl.class, "updateMessageCounter");
		 Sale sale = new Sale();
		 sale.setProductType("APPLE"); 
		 sale.setValue(10);
		 
		 PowerMock.mockStatic(AppContext.class);
		 EasyMock.expect(AppContext.isPauseRequests()).andReturn(true);	 

		 PowerMock.replayAll();
		 salesServiceImpl.createSaleRecord(sale);
		 PowerMock.verifyAll();
	}
	
	@Test
	public void  testCreateSalesRecord() throws Exception{
		
		 SalesMessageServiceImpl salesServiceImpl = PowerMock.createPartialMock(SalesMessageServiceImpl.class, "updateMessageCounter");
		 Sales sales = new Sales();
		 Sale sale = new Sale();
		 sale.setProductType("APPLE"); 
		 sale.setValue(10);
		 sales.setSale(sale); 
		 sales.setCount(10);
		 
		 PowerMock.mockStatic(AppContext.class);
		 EasyMock.expect(AppContext.isPauseRequests()).andReturn(false);	 
		 
		 
		 PowerMock.expectPrivate(salesServiceImpl, "updateMessageCounter", sales);
		 EasyMock.expectLastCall();			
		
		
		 PowerMock.replayAll();
		 salesServiceImpl.createSalesRecord(sales);
		 PowerMock.verifyAll();
	}
	
	@Test(expected=BaseAppException.class)
	public void  testCreateSalesRecord_SalesOrderProcessingIsPaused() throws Exception{
		
		 SalesMessageServiceImpl salesServiceImpl = PowerMock.createPartialMock(SalesMessageServiceImpl.class, "updateMessageCounter");
		 Sales sales = new Sales();
		 Sale sale = new Sale();
		 sale.setProductType("APPLE"); 
		 sale.setValue(10);
		 sales.setSale(sale); 
		 sales.setCount(10);
		 
		 PowerMock.mockStatic(AppContext.class);
		 EasyMock.expect(AppContext.isPauseRequests()).andReturn(true);	 

		 PowerMock.replayAll();
		 salesServiceImpl.createSalesRecord(sales);
		 PowerMock.verifyAll();
	}
	
	@Test
	public void  testAdjustSalesRecord() throws Exception{
		
		 SalesMessageServiceImpl salesServiceImpl = PowerMock.createPartialMock(SalesMessageServiceImpl.class, "updateMessageCounter");
		 Adjustment adjustment = new Adjustment();
		 adjustment.setProductType("APPLE");
		 adjustment.setValue(10);
		 adjustment.setOperation("ADD");
		 
		 PowerMock.mockStatic(AppContext.class);
		 EasyMock.expect(AppContext.isPauseRequests()).andReturn(false);	 
		 
		 
		 PowerMock.expectPrivate(salesServiceImpl, "updateMessageCounter", adjustment);
		 EasyMock.expectLastCall();			
		
		
		 PowerMock.replayAll();
		 salesServiceImpl.adjustSalesRecord(adjustment);
		 PowerMock.verifyAll();
	}
	
	@Test(expected=BaseAppException.class)
	public void  testAdjustSalesRecord_SalesOrderProcessingIsPaused() throws Exception{
		
		 SalesMessageServiceImpl salesServiceImpl = PowerMock.createPartialMock(SalesMessageServiceImpl.class, "updateMessageCounter");
		 Adjustment adjustment = new Adjustment();
		 adjustment.setProductType("APPLE");
		 adjustment.setValue(10);
		 adjustment.setOperation("ADD");
		 
		 PowerMock.mockStatic(AppContext.class);
		 EasyMock.expect(AppContext.isPauseRequests()).andReturn(true);	 

		 PowerMock.replayAll();
		 salesServiceImpl.adjustSalesRecord(adjustment);
		 PowerMock.verifyAll();
	}
	
	@Test
	public void  testGetNewValue_ADD() throws Exception{
		
		 SalesMessageServiceImpl salesServiceImpl = PowerMock.createPartialMock(SalesMessageServiceImpl.class, "updateMessageCounter");
		 
		 Sale sale = new Sale();
		 sale.setProductType("APPLE");
		 sale.setValue(100);
		 
		 Adjustment adjustment = new Adjustment();
		 adjustment.setProductType("APPLE");
		 adjustment.setValue(10);
		 adjustment.setOperation("ADD");	 
		
		
		 PowerMock.replayAll();
		 Double newValue = Whitebox.invokeMethod(salesServiceImpl, sale, adjustment);
		 assertEquals(new Double(110.0),newValue);
		 PowerMock.verifyAll();
		 
	}
	
	@Test
	public void  testGetNewValue_SUBTRACT() throws Exception{
		
		 SalesMessageServiceImpl salesServiceImpl = PowerMock.createPartialMock(SalesMessageServiceImpl.class, "updateMessageCounter");
		 
		 Sale sale = new Sale();
		 sale.setProductType("APPLE");
		 sale.setValue(100);
		 
		 Adjustment adjustment = new Adjustment();
		 adjustment.setProductType("APPLE");
		 adjustment.setValue(10);
		 adjustment.setOperation("SUBTRACT");	 
		
		
		 PowerMock.replayAll();
		 Double newValue = Whitebox.invokeMethod(salesServiceImpl, sale, adjustment);
		 assertEquals(new Double(90.0), newValue);
		 PowerMock.verifyAll();
	}
	
	@Test
	public void  testGetNewValue_MULTIPLY() throws Exception{
		
		 SalesMessageServiceImpl salesServiceImpl = PowerMock.createPartialMock(SalesMessageServiceImpl.class, "updateMessageCounter");
		 
		 Sale sale = new Sale();
		 sale.setProductType("APPLE");
		 sale.setValue(100);
		 
		 Adjustment adjustment = new Adjustment();
		 adjustment.setProductType("APPLE");
		 adjustment.setValue(10);
		 adjustment.setOperation("MULTIPLY");	 
		
		
		 PowerMock.replayAll();
		 Double newValue = Whitebox.invokeMethod(salesServiceImpl, sale, adjustment);
		 assertEquals(new Double(1000.0), newValue);
		 PowerMock.verifyAll();
	}
	

}
