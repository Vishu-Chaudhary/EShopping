package com.sapient.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sapient.entity.Product;

@Repository("productdao")
public class ProductDao implements IProductDao {
	@Autowired
	private SessionFactory sfac;

	@Override
	public List<Product> getProducts() {
		Session sess = sfac.openSession();
		Query qry = sess.createQuery("from Product");
		List<Product> lst = qry.list();
		sess.close();
		return lst;
	}

	@Override
	public Product getProductById(int pid) {
		Session sess = sfac.openSession();
		Product prod = sess.get(Product.class, pid);	
		sess.close();
		return prod;
	}

	@Override
	public Product getProductByCat(int catId) {
		Session sess = sfac.openSession();
		Product prod = sess.get(Product.class, catId);	
		sess.close();
		return prod;
	}

	@Override
	public int updatePrice(int pid, double price) {
		// TODO Auto-generated method stub
		return 0;
	}

}
