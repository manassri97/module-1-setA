package com.capgemini.salesmanagement.testcase;


import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.salesmanagement.bean.Sale;
import com.capgemini.salesmanagement.dao.ISaleDAO;
import com.capgemini.salesmanagement.dao.SaleDAO;
import com.capgemini.salesmanagement.exceptions.SaleCannotBeNullException;
import com.capgemini.salesmanagement.service.ISaleService;
import com.capgemini.salesmanagement.service.SaleService;

public class TestCases {

	
	
	static ISaleDAO saleDAO = new SaleDAO();
	static ISaleService saleService = new SaleService(saleDAO);
	@Before
	public void setUp() throws Exception {
	}

	/*when details of product are entered 
	and the product details are stored in HashMap
	then if we find the same details in HashMap
	then it should return same*/
	
	@Test
	public void findDeatilsSuccessfully() throws SaleCannotBeNullException {
		HashMap<Integer, Sale> hashMap;
		Sale sale = new Sale();
		sale.setCategory("Electronics");
		sale.setPrice(5000);
		sale.setProdCode(1001);
		sale.setProductName("Smart Phone");
		sale.setQuantity(2);
		hashMap = saleService.insertSalesDetails(sale);
		assertEquals(hashMap, saleService.insertSalesDetails(sale));
	}
	

}
