package com.sapient.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sapient.entity.UserDetails;

@Repository("registerdao")
public class RegisterDao implements IRegisterDao {
	
	private Logger logger=LoggerFactory.getLogger("eshopapp");
	
	@Autowired
	SessionFactory sfac ;
	


	@Override
	public int addUser(UserDetails reg) throws UserNameExistsException {
		logger.debug("add User Method");
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sfac.openSession();
			tx=  sess.beginTransaction();
			sess.save(reg);
			tx.commit();
			return 1;
			
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			logger.error(e.toString());
			if(tx!=null){
				tx.rollback();
			}
			throw new UserNameExistsException("User Name Already Exists");
		}
		finally {
			sess.close();
		}
		
	}



	@Override
	public UserDetails getUser(String userName) throws NotFoundException{
		logger.debug("get User Method");
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sfac.openSession();
			UserDetails user = sess.get(UserDetails.class, userName);
			if(user == null){
				throw new NotFoundException("User Name does not  Exists");
			}
			return user;
			
		}
		finally {
			sess.close();
		}
	}

}
