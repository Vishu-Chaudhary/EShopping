package com.sapient;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.sapient.dao.IProductDao;

@SpringBootApplication
public class EShoppingApplication extends SpringBootServletInitializer{
	
	@Autowired
	private EntityManagerFactory emf;
	
//	@Autowired
//	@Qualifier("productdao")
//	private IProductDao dao;
	
	@Bean
	public SessionFactory getSessionFactory(){
		return emf.unwrap(SessionFactory.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(EShoppingApplication.class, args);
	}

}
