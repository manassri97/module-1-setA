package com.capgemini.salesmanagement.dao;

import java.util.HashMap;

import com.capgemini.salesmanagement.bean.Sale;

public class SaleDAO implements ISaleDAO {

/*	when details are envoked from client side to server side
 *  and data is stored by insertSaleDetails method
 *  */
	
	public static HashMap<Integer, Sale> sales = new HashMap<Integer, Sale>();
	@Override
	public HashMap<Integer, Sale> insertSaleDetails(Sale sale) {
		if(sale==null)
			return null;
		else {
			sales.put(sale.getSaleId(), sale);
			return sales;
		}
	}

}
