package com.sapient.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.dao.IDException;
import com.sapient.dao.IProductDao;

import com.sapient.entity.Product;

@RestController
public class ProductRestController {
	private Logger logger = LoggerFactory.getLogger("empapp");
	@Autowired
	private IProductDao  dao;

	@RequestMapping(value = "viewbyeid")
	public Product getProduct(@RequestParam("eid") int pid) throws IDException {
		Product  prod = dao.getProductById(pid);
		if (prod != null)
			prod.getCat().setProds(null);
		return prod;

	}
}
