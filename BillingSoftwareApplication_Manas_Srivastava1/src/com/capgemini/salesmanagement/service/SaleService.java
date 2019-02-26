package com.capgemini.salesmanagement.service;

import java.time.LocalDate;
import java.util.HashMap;

import com.capgemini.salesmanagement.bean.Sale;
import com.capgemini.salesmanagement.dao.ISaleDAO;
import com.capgemini.salesmanagement.exceptions.SaleCannotBeNullException;
import com.capgemini.salesmanagement.exceptions.WrongProductNameException;

public class SaleService implements ISaleService {

	ISaleDAO saleDAO;
	public SaleService(ISaleDAO saleDAO) {
		this.saleDAO=saleDAO;
	}

	/*	when details are envoked from user interface side
	 * 	and bill id is created
	 * 	and date is set to the bill
	 * 	then the sale object is passed to DAO layer to get stored in database
	 */
	
	@Override
	public HashMap<Integer, Sale> insertSalesDetails(Sale sale) throws SaleCannotBeNullException {
		HashMap<Integer, Sale> hashMap; 
		LocalDate saleDate = LocalDate.now();
		int saleId;
		saleId = (int)(Math.random()*10000);
		sale.setLineTotal(sale.getQuantity()*sale.getPrice());
		sale.setSaleDate(saleDate);
		sale.setSaleId(saleId);
		hashMap = saleDAO.insertSaleDetails(sale);
		if(hashMap==null)
			throw new SaleCannotBeNullException("No sale is done");
		else
			return hashMap; 
	}

	@Override
	public boolean validateProductCode(int productId) {
		if(productId==1001 || productId==1002 || productId==1003 || productId==1004)
			return true;
		else 
			return false;
	}

	@Override
	public boolean validateQuantity(int qty) {
		if(qty>0 && qty<5)
			return true;
		else
			return false;
	}

	@Override
	public boolean validateProductCat(String prodCat) {
		if(prodCat.equals("Electronics") || prodCat.equals("Toys"))
			return true;
		else
			return false;	
	}

	@Override
	public boolean validateProductName(String prodCat, String prodName) throws WrongProductNameException {
		if(prodCat.equals("Electronics"))
		{
			if(prodName.equals("TV") || prodName.equals("Smart Phone") || prodName.equals("Vedio Game"))
				return true;
			else
				throw new WrongProductNameException("Exception : enter correct product name");
		}
		else if(prodCat.equals("Toys"))
		{
			if(prodName.equals("Soft Toy") || prodName.equals("Telescope") || prodName.equals("Barbee Doll"))
				return true;
			else
				throw new WrongProductNameException("Exception : enter correct product name");
		}
		else
			return false;
	}

	@Override
	public boolean validateProductPrice(float price) {
		if(price>200)
		{
			return true;
		}
		else
			return false;
	}

}
