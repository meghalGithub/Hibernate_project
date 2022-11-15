package com.jbk.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jbk.entity.Product;

public class HibernateUtility {

	public static SessionFactory getSessionFactory() {
		
		SessionFactory sessionFactory = null;
		
		try {
			
			Configuration configuration = new Configuration();
			configuration.configure().addAnnotatedClass(Product.class);
		sessionFactory =	configuration.buildSessionFactory();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sessionFactory;
	}
	
}
