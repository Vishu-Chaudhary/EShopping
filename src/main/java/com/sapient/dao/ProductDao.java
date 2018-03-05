package com.sapient.dao;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sapient.entity.Category;
import com.sapient.entity.Product;

@Repository("productdao")
public class ProductDao implements IProductDao {
	
	private Logger logger=LoggerFactory.getLogger("eshopapp");
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
	public Product getProductById(int pid) throws IDException {
		Session sess = null;
		try{
			sess = sfac.openSession();
		    Product prod = sess.get(Product.class, pid);
		    if(prod == null)
		    	throw new IDException("Product with id "+ pid+" does not Exists");
		    return prod;
		}
		finally{
		 sess.close();
		}
		
	}

	@Override
	public List<Product> getProductByCat(int cat) {
		Session sess = sfac.openSession();
		String hql = "from Product p left join fetch p.cat where p.cat.catId=?";
		logger.debug(hql);
		Query qry = sess.createQuery(hql);
		qry.setInteger(0, cat);
		List<Product> lst = qry.list();
		sess.close();
		return lst;
	}

	@Override
	public int updatePrice(int pid, double price) throws IDException {
		logger.debug("Update product price");
//		logger.debug("{},{}",pid,price);
		Session sess = null;
		Transaction tx=null;
		try{
			sess=sfac.openSession();
			tx = sess.beginTransaction();
			Product prod=sess.get(Product.class, pid);
			logger.debug(prod.toString());
			prod.setPrice(price);
//			logger.debug("{}",prod.getPrice());
			tx.commit();
			logger.info("product updated");
			return 1;
		}
		catch(HibernateException ex){
			logger.error(ex.getMessage());
			logger.error(ex.toString());
			throw new IDException("Product with id "+ pid+" does not Exists");
		}
		finally{
		 sess.close();
		}
	}

	@Override
	public List<Category> getCategories() {
		logger.debug("View all the categories");
		Session sess=sfac.openSession();
		Query qry=sess.createQuery("from Category");
		List<Category> lst=qry.list();
		sess.close();
		return lst;
	}

	@Override
	public int addProducts(Product prod) throws IDException {
		logger.debug("Add Product");
		Session sess=null;
		Transaction tx=null;
		try {
			sess=sfac.openSession();
			tx=sess.beginTransaction();
			logger.debug(prod.toString());
			sess.save(prod);
			tx.commit();
			logger.info("Product added");
			return 1;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			logger.error(e.toString());
			if(tx!=null)
				tx.rollback();
			throw new IDException("Product Id Already exists",e);
		}
		finally {
			sess.close();
		}
	}

	@Override
	public int addCategory(Category cat) throws IDException {
		logger.debug("Add Category");
		Session sess=null;
		Transaction tx=null;
		try {
			sess=sfac.openSession();
			tx=sess.beginTransaction();
			logger.debug(cat.toString());
			sess.save(cat);
			tx.commit();
			logger.info("Category added");
			return 1;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			logger.error(e.toString());
			if(tx!=null)
				tx.rollback();
			throw new IDException("Category Id Already exists",e);
		}
		finally {
			sess.close();
		}
	}

	@Override
	public int getMaxProdId() {
		Session sess = sfac.openSession();
		String hql = "select max(pid) from Product";
		logger.debug(hql);
		Query qry = sess.createQuery(hql);
		Integer idx = (Integer)qry.uniqueResult();
		return idx+1;
	}


	@Override
	public int getMaxCartId() {
		Session sess = sfac.openSession();
		String hql = "select max(catId) from Category";
		logger.debug(hql);
		Query qry = sess.createQuery(hql);
		Integer idx = (Integer)qry.uniqueResult();
		return idx+1;
	}

	

}
