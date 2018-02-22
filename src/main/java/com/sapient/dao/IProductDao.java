package com.sapient.dao;

import java.util.List;

import com.sapient.entity.Product;

public interface IProductDao {
	List<Product> getProducts();
	Product getProductById(int pid);
	Product getProductByCat(int catId);
	int updatePrice(int pid, double price);
	

}
