package com.jbk.cache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jbk.config.HibernateUtility;
import com.jbk.entity.Product;

public class Cache {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		
		Session session = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		
		Product product = session.get(Product.class, 1002);
		System.out.println(product);
		
		Product product2 = session2.get(Product.class, 1002);
		System.out.println(product2);
		

	}

}
