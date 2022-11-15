package com.jbk.service;

import java.util.Scanner;

import com.jbk.entity.Product;

public class ProductService {

	public static Product productInfo() {
		 
		Product product = new Product();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Id");
		int id = scanner.nextInt();
		
		System.out.println("Enter Product Name -> ");
		String name = scanner.next();
		
		System.out.println("Enter Product Price -> ");
		double price = scanner.nextDouble();
		
		System.out.println("Enter Product Type ->");
		String type = scanner.next();
		
		product = new Product(id, name, price, type);
		
		return product;	
		 
		}
}
