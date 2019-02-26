package com.capgemini.salesmanagement.service;

import java.util.HashMap;

import com.capgemini.salesmanagement.bean.Sale;
import com.capgemini.salesmanagement.exceptions.SaleCannotBeNullException;
import com.capgemini.salesmanagement.exceptions.WrongProductNameException;

public interface ISaleService {
	public HashMap<Integer, Sale> insertSalesDetails(Sale sale) throws SaleCannotBeNullException;
	public boolean validateProductCode(int productId);
	public boolean validateQuantity(int qty);
	public boolean validateProductCat(String prodCat);
	public boolean validateProductName(String prodCat, String prodName) throws WrongProductNameException;
	public boolean validateProductPrice(float price);

}
