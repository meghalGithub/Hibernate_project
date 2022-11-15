package com.jbk.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.jbk.config.HibernateUtility;
import com.jbk.entity.Product;

public class ProductDao {

	//get session factory
	SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	//SAVE PRODUCT
	public boolean saveProduct(Product product) {
		
		boolean isAdded = false;
		
		//get session from session factory
		Session session = sessionFactory.openSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			 if(product==null) {
				session.save(product);
				transaction.commit();
				isAdded = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		
		return isAdded;
	}
	
	//GET PRODUCT BY ID
	public Product getProductById(int productId) {
		
		 Session session = sessionFactory.openSession();
         Product product = null;
		 try {
			
		 product = session.get(Product.class, productId);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session!=null) 
				session.close();
		}
		
		return product;	
	}
	
	//DELETE PRODUCT BY ID
	public boolean deleteProductById(int productId) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isDeleted = false;
		Product product = null;
		
		try {
			
		 product = session.get(Product.class, productId);
			if(product!=null) {
				session.delete(product);
				transaction.commit();
				isDeleted = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return isDeleted;
	}
 
	//UPDATE PRODUCT
	public boolean updateProductById(Product product) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isUpdated = false;
		
		try {
			
			Product prd = session.get(Product.class, product.getProductId());
			session.evict(prd);
			if(prd != null) {
				session.update(product);
				transaction.commit();
			    isUpdated= true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return isUpdated;
	}
	
  //RESTRICTION EXAMPLE
  //GET LIST OF ALL PRODUCTS
  public List<Product> getAllProducts() {
		
		Session session = sessionFactory.openSession();
		List<Product> list = null;
	
		try {
			
			Criteria criteria = session.createCriteria(Product.class);
			
			//criteria.setFirstResult(4);  
			//criteria.setMaxResults(3);   
			
			list = criteria.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
  
  //GET PRODUCT NAME IN ASCENDING ORDER
  public List<Product> getProductsByAscName() {
		
		Session session = sessionFactory.openSession();
		List<Product> list = null;
	
		try {
			
			Criteria criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.asc("productName"));
			list = criteria.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
  
//GET PRODUCT NAME IN DESCENDING ORDER
  public List<Product> getProductsByDescName() {
		
		Session session = sessionFactory.openSession();
		List<Product> list = null;
	
		try {
			
			Criteria criteria = session.createCriteria(Product.class);
			
			criteria.addOrder(Order.desc("productName"));
			list = criteria.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
  //GET LIST OF PRODUCTS BY NAME
  public List<Product> getProductByName(String productName) {
		Session session = sessionFactory.openSession();
		List<Product> list=null;
		Product product=null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			
			criteria.add(Restrictions.eq("productName", productName));
			list = criteria.list();
			if(!list.isEmpty()) {
				 product= list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return  list;

	}
  
 
  
  //GET PRODUCT BY PRICE
  public List<Product> getProductByPrice(double productPrice) {
		Session session = sessionFactory.openSession();
		List<Product> list=null;
		Product product=null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			
			criteria.add(Restrictions.eq("productPrice", productPrice));
					
			list = criteria.list();
			if(!list.isEmpty()) {
				 product= list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return  list;

	}
  
  //GET PRODUCT BY PRICE USING GREATER THAN FUNCTION
  public List<Product> getProductByGreaterPrice(double productPrice) {
		Session session = sessionFactory.openSession();
		List<Product> list=null;
		Product product=null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			
			criteria.add(Restrictions.gt("productPrice", productPrice));
			
			list = criteria.list();
			if(!list.isEmpty()) {		
				product= list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close(); 
			}
		}
		return  list;  
 }
  
//GET PRODUCT BY PRICE USING LESS THAN FUNCTION
  public List<Product> getProductByLessThanPrice(double productPrice) {
		Session session = sessionFactory.openSession();
		List<Product> list=null;
		Product product=null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
		
			 criteria.add(Restrictions.lt("productPrice", productPrice));
			
			list = criteria.list();
			if(!list.isEmpty()) {		
				product= list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close(); 
			}
		}
		return  list;  
}
  
  //PROJECTION EXAMPLE
  //GET AVERAGE OF PRODUCT PRICE USING PROJECTIONS
  
  public double getAvgOfThePrice() {
	  
	  Session session = sessionFactory.openSession();
	  double average = 0;
	  try {
		
		  Criteria criteria = session.createCriteria(Product.class);
		  
		  //criteria.setProjection(Projections.avg("productPrice"));
		  criteria.setProjection(Projections.sum("productPrice"));
		  
		  List<Double> list = criteria.list();
		  if(!list.isEmpty()) {
			  average = list.get(0);
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (session != null) {
			session.close(); 
		}
	}
	return average;
	  
  }
  
  //GET MAX VALUE OF PRICE
public double getMaxOfThePrice() {
	  
	  Session session = sessionFactory.openSession();
	  double maxvalue = 0;
	  try {
		
		  Criteria criteria = session.createCriteria(Product.class);
		  
		  criteria.setProjection(Projections.max("productPrice"));
		  
		  List<Double> list = criteria.list();
		  if(!list.isEmpty()) {
			  maxvalue = list.get(0);
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (session != null) {
			session.close(); 
		}
	}
	return maxvalue;
	  
  }

//GET MIN VALUE OF PRICE
public double getMinOfThePrice() {
	  
	  Session session = sessionFactory.openSession();
	  double minvalue = 0;
	  try {
		
		  Criteria criteria = session.createCriteria(Product.class);
		  
		  criteria.setProjection(Projections.min("productPrice"));
		  
		  List<Double> list = criteria.list();
		  if(!list.isEmpty()) {
			  minvalue = list.get(0);
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (session != null) {
			session.close(); 
		}
	}
	return minvalue;
	  
}

//SHOW ROW COUNT USING PROJECTIONS
public long getRowCount() {
	Session session = sessionFactory.openSession();
	long count = 0;
	try {
		
		Criteria criteria = session.createCriteria(Product.class);
		criteria.setProjection(Projections.rowCount());
		List<Long> list = criteria.list();
		if(!list.isEmpty())
			count = list.get(0);
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (session != null) {
			session.close(); 
		}
	}
	return count;
	
}



//DELETE BY USING QUERY
public int deleteQuery(int productId) {
	Session session = sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	int row = 0;
	try {
		Query query = session.createQuery("DELETE FROM Product WHERE productId=:id");
		query.setParameter("id", productId);
		//or Query query = session.createQuery("DELETE FROM Product WHERE productId="+productId);
		row = query.executeUpdate();
		transaction.commit();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return row;
	
}

//query.setParameter("name", productName);
public String deleteQuery1(String productName) {
	Session session = sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	String row1 = null;
	try {
		Query query = session.createQuery("DELETE FROM Product WHERE productId=:name");
		query.setParameter("name", productName);
		//or Query query = session.createQuery("DELETE FROM Product WHERE productName="+productName);
		//row1 = query.setText("productName", productName);
		transaction.commit();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return row1;
}
}