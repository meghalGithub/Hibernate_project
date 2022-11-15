package com.jbk;

import java.util.List;
import java.util.Scanner;

import com.jbk.dao.ProductDao;
import com.jbk.entity.Product;
import com.jbk.service.ProductService;

public class ProductController {

	public static void main(String[] args) {
		             
		int choice;
		char ch;
		Scanner scanner = new Scanner(System.in);
		ProductDao dao = new ProductDao();
		Product product = null;
		List<Product> list = null;
		do {
			
			System.out.println("Select 1 for Save Product");
			System.out.println("Select 2 for Get Product By Id");
			System.out.println("Select 3 for Delete Product");
			System.out.println("Select 4 for Update Product");
			System.out.println("Select 5 for Get All Products");
			System.out.println("Select 6 for Get Name By Ascending Order");
			System.out.println("Select 7 for Get Name By Descending Order");
			System.out.println("Select 8 for Get Product By Name ");
			System.out.println("Select 9 for Get Product By Price ");
			System.out.println("Select 10 for Get Product By Price Greater Than ");
			System.out.println("Select 11 for Get Product By Price Less Than ");
			System.out.println("Select 12 for Get Average Of Price ");
			System.out.println("Select 13 for Get Maximum Value Of Price ");
			System.out.println("Select 14 for Get Minimum Value Of Price ");
			System.out.println("Select 15 for Get Row Count ");
			System.out.println("Select 16 for Delete Row Using Query By Id");
			System.out.println("Select 17 for Delete Row Using Query By Name");
			
			choice = scanner.nextInt();
			
			switch(choice) {
			case 1: 
				product = ProductService.productInfo();
				boolean isAdded = dao.saveProduct(product);
				if(isAdded) 
					System.out.println("Product Saved....");
				else
					System.out.println("Product Already Exist with Id...Can't Save");
				break;
				
			case 2:
				System.out.println("Enter Product Id ->");
			 	int id = scanner.nextInt();
			 	product = dao.getProductById(id);
			 	if(product!=null)
			 		System.out.println(product);
			 	else
			 		System.out.println("Product not found for this Id.."+id);
				break;
				
			case 3:
				System.out.println("Enter Product Id to Delete -> ");
				int id1 = scanner.nextInt();
				boolean isDeleted = dao.deleteProductById(id1);
				if(isDeleted)
		           System.out.println("Product is deleted");
				else
					System.out.println("Product Id not found to delete the product"+id1);
				break;
				
			case 4:
			    product = ProductService.productInfo();
				Boolean isUpdated = dao.updateProductById(product);
				if(isUpdated)
					System.out.println("Product Updated..!");
				else
					System.out.println("Product not found for this Id"+product.getProductId());
				
			case 5:
				list = dao.getAllProducts();
				if(!list.isEmpty()) {
					for (Product product2 : list) {
						System.out.println(product2);
					} 
				}
					else
						System.out.println("Product Not Found");
				break;
				
			case 6:
				list = dao.getProductsByAscName();
				if(!list.isEmpty()) {
					for (Product product2 : list) 
					{
						System.out.println(product2);
					} 
				}else
					System.out.println("Product Not Found");
				break;
				
			case 7:
				list = dao.getProductsByDescName();
				if(!list.isEmpty()) {
					for (Product product2 : list) {
						System.out.println(product2);
					} 
				}else
						System.out.println("Product Not Found");
				break;
				
			case 8:
				System.out.println("Enter Product Name");
				String productName=scanner.next();
		   list = dao.getProductByName(productName);
			if(list!=null) {
				System.out.println(list);
			}else {
				System.out.println(" Product Not Found For This Name ");
			}
				break;
				
			case 9:
				System.out.println("Enter Product Price");
				double productPrice=scanner.nextDouble();
			List<Product> prod = dao.getProductByPrice(productPrice);
			if(prod!=null) {
				System.out.println(prod);
			}else {
				System.out.println(" Product Not Found For This Price "+prod);
			}
				break;
				
			case 10:
				System.out.println("Enter Product Price");
				double productPrice1=scanner.nextDouble();
			List<Product> prod1 = dao.getProductByGreaterPrice(productPrice1);
			if(prod1!=null) {
				System.out.println(prod1);
			}else {
				System.out.println(" Product Not Found For This Price "+prod1);
			}
				break;
				
			case 11:
				System.out.println("Enter Product Price");
				double productPrice2=scanner.nextDouble();
			List<Product> prod2 = dao.getProductByLessThanPrice(productPrice2);
			if(prod2!=null) {
				System.out.println(prod2);
			}else {
				System.out.println(" Product Not Found For This Price "+prod2);
			}
				break;
				
			case 12:
				double average =dao.getAvgOfThePrice();
				if(average>0)
					System.out.println(average);
				else
					System.out.println("Average Can't Be Done");
				break;
			
			case 13:
				double maxvalue =dao.getMaxOfThePrice();
				if(maxvalue>0)
					System.out.println(maxvalue);
				else
					System.out.println("Operation Can't Be Done");
				break;
				
			
			case 14:
				double minvalue =dao.getMinOfThePrice();
				if(minvalue>0)
					System.out.println(minvalue);
				else
					System.out.println("Can't Get The Min Value");
				break;
				
			case 15:
				long count =     dao.getRowCount(); 
				if(count>0)
					System.out.println(count);
				else
					System.out.println("Can't Get Row Count");
				break;
				
				
			case 16:
				System.out.println("Enter Id");
				int id2 = scanner.nextInt();
				int row =   dao.deleteQuery(id2);
				System.out.println(row+ "Row Deleted");
				break;
				
			case 17:
				System.out.println("Enter Name");
				String id3 = scanner.next();
				String row1 = dao.deleteQuery1(id3);
				System.out.println(row1+ "Row Deleted");
				break;
				
			default:
				break;
		}	
			System.out.println("Do You Want To Continue y/n?");
			ch = scanner.next().charAt(0);
			
	}while(ch=='Y' || ch=='y');
		
		System.out.println("Loop Terminated");
		
  }

}

