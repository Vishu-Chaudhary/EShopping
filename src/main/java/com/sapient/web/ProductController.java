package com.sapient.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.dao.IProductDao;
import com.sapient.entity.Product;

@Controller
public class ProductController {
	
	@Autowired
	private IProductDao dao;
	
	@RequestMapping(value="/")
	public String getMainpage(){
		return "Main";
	}
	
	@RequestMapping(value="viewall")
	public String viewAllEmployee(@RequestParam(value="pgno", required=false )Integer pgno,
									Model model){
		List<Product> lst=dao.getProducts();
		if(pgno==null)
			pgno=1;
		int end = pgno*3;
		int start=end-3;
		if(end>lst.size())
			end=lst.size();
		int pgs = (int)Math.ceil(lst.size()/3.0);
		List<Product> sublist = lst.subList(start, end);
		
		model.addAttribute("currpg", pgno);
		model.addAttribute("totpgs",pgs);
		model.addAttribute("prodlist",sublist);
		return "ProductView";
	}

}
