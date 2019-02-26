package com.capgemini.salesmanagement.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.capgemini.salesmanagement.bean.Sale;
import com.capgemini.salesmanagement.dao.ISaleDAO;
import com.capgemini.salesmanagement.dao.SaleDAO;
import com.capgemini.salesmanagement.exceptions.SaleCannotBeNullException;
import com.capgemini.salesmanagement.exceptions.WrongProductNameException;
import com.capgemini.salesmanagement.service.ISaleService;
import com.capgemini.salesmanagement.service.SaleService;

public class Client {
	private static Scanner scanner = new Scanner(System.in);
	static ISaleDAO saleDAO = new SaleDAO();
	static ISaleService saleService = new SaleService(saleDAO);
	public static void main(String[] args) {
		showBill();
	}

	private static void showBill() {
		List<Sale> list = new ArrayList<>();
		Sale sale;
		HashMap<Integer, Sale> hashMap = null;
		System.out.println("Billing Software");
		System.out.println("___________________________________________________________");
		
		sale=acceptDetails();
		if(sale==null)
			System.out.println("wrong credentials");
		else
		{
		System.out.println("\nCost of each product");
		System.out.println("______________________________________");
		System.out.println("Product Name : "+sale.getProductName());
		System.out.println("Product Price : "+sale.getPrice());
		System.out.println("Product quantity : "+sale.getQuantity());
		System.out.println("Line Total(Rs.) : "+sale.getPrice()*sale.getQuantity());
		
		try {
			hashMap = saleService.insertSalesDetails(sale);
		} catch (SaleCannotBeNullException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nFinal Bill");
		System.out.println("___________________________________________");
		hashMap.forEach((k,v) -> {
				list.add(v);
		});
		System.out.println(list);
		}
	}
	private static Sale acceptDetails() {
		Sale sale = new Sale();
		int prodCode;
		String productName;
		String category;
		int quantity;
		int price;
		System.out.println("Enter the Product Code : ");
		prodCode = scanner.nextInt();
		scanner.nextLine();
		if(saleService.validateProductCode(prodCode))
		{
			System.out.println("Enter the Quantity : ");
			quantity = scanner.nextInt();
			scanner.nextLine();
			if(saleService.validateQuantity(quantity)) 
			{
				System.out.println("Enter the Product Category : ");
				category = scanner.nextLine();
				if(saleService.validateProductCat(category)) 
				{
					System.out.println("Enter the Product Name : ");
					productName = scanner.nextLine();
					try {
						if(saleService.validateProductName(category, productName))
						{
							System.out.println("Enter the Product Price : ");
							price = scanner.nextInt();
							if(saleService.validateProductPrice(price))
							{
								sale.setProdCode(prodCode);
								sale.setQuantity(quantity);
								sale.setCategory(category);
								sale.setProductName(productName);
								sale.setPrice(price);
								return sale;
							}
						}
					} catch (WrongProductNameException e) {
						System.out.println(e.getMessage());
					}
				}
				
			}
			
		}
	return null;
	
	}

}
