package com.sapient.dao;

import java.util.List;

import com.sapient.entity.Category;

import com.sapient.entity.Product;

public interface IProductDao {
	List<Product> getProducts();
	Product getProductById(int pid) throws IDException;
	List<Product> getProductByCat(int cat);
	int updatePrice(int pid, double price) throws IDException;
	List<Category> getCategories();
	int addProducts(Product prod) throws IDException;
	int addCategory(Category cat) throws IDException;
	int getMaxProdId();
	int getMaxCartId();

}
