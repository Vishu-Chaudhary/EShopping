package com.sapient.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.dao.IDException;
import com.sapient.dao.IProductDao;
import com.sapient.entity.Category;
import com.sapient.entity.Product;

@Controller
@PropertySource("classpath:eshop.properties")
public class ProductController {

	private Logger logger = LoggerFactory.getLogger("eshopapp");
	@Value("${photo_folder}")
	private String photo_folder;
	@Autowired
	@Qualifier("productdao")
	private IProductDao dao;

	@RequestMapping(value = "main")
	public String getMainpage(Model model) {
		List<Category> lst = dao.getCategories();

		model.addAttribute("catlist", lst);

		return "Main";
	}

	@RequestMapping(value = "viewall")
	public String viewAllProducts(@RequestParam(value = "pgno", required = false) Integer pgno, Model model) {
		List<Product> lst = dao.getProducts();
		logger.debug(lst.toString());
		if (pgno == null)
			pgno = 1;
		String pgurl = "viewall?";

		return prepareProdList(pgno, pgurl, lst, model);
	}

	@RequestMapping(value = "viewbycategory")
	public String viewByCategory(@RequestParam(value = "pgno", required = false) Integer pgno,
			@RequestParam(value = "txtcat") int catId, Model model) {
		List<Product> lst = dao.getProductByCat(catId);
		logger.debug(lst.toString());
		if (pgno == null)
			pgno = 1;
		String pgurl = "viewbycategory?txtcat=" + catId + "&";
		return prepareProdList(pgno, pgurl, lst, model);
	}

	public String prepareProdList(int pgno, String pgurl, List<Product> lst, Model model) {
		int end = pgno * 3;
		int start = end - 3;
		if (end > lst.size())
			end = lst.size();
		int pgs = (int) Math.ceil(lst.size() / 3.0);
		List<Product> sublist = lst.subList(start, end);
		model.addAttribute("currpg", pgno);
		model.addAttribute("totpgs", pgs);
		model.addAttribute("prodlist", sublist);
		model.addAttribute("pgurl", pgurl);
		return "ProductView";
	}

	public String viewCategories(Model model) {
		List<Category> lst = dao.getCategories();
		model.addAttribute("catlist", lst);
		return "CatView";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String editEmployee(@RequestParam("txtpid") int pid, @RequestParam("txtprice") double price,
			@RequestParam("txtpg") int pgno, Model model) throws IDException {
		int res = dao.updatePrice(pid, price);
		if (res > 0) {
			model.addAttribute("msg", "Row updated for Emp Id" + pid);
		} else {
			model.addAttribute("msg", "Row not updated");
		}
		return viewAllProducts(pgno, model);
	}

	@RequestMapping(value = "img")
	public void downimg(@RequestParam("pimg") String img, HttpServletResponse resp) {
		resp.setContentType("image/jpg");
		FileInputStream fis;
		try {
			fis = new FileInputStream(photo_folder + img);
			ServletOutputStream out = resp.getOutputStream();
			byte[] arr = new byte[fis.available()];
			fis.read(arr);
			out.write(arr);
			out.flush();// output stream should not be closed it should be
						// flushed
			fis.close();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "addtocart")
	public String addToCart(@RequestParam("pid") Integer pid, @RequestParam("pgurl") String pgurl,
			HttpServletRequest req, Model model) {
		logger.debug("Add to Cart");
		HttpSession sess = req.getSession(false);
		logger.debug("session " + sess);
		Set<Integer> cart = (HashSet<Integer>) sess.getAttribute("cart");
		if (cart.contains(pid)) {
			sess.setAttribute("msg", "you already have this product in you cart");
		} else {
			cart.add(pid);
			sess.setAttribute("msg", "");
		}
		sess.setAttribute("cartcount", cart.size());
		logger.debug("cart size{}", cart.size());
		return "redirect:" + pgurl;

	}

	@RequestMapping(value = "cartfrm")
	public String displayCartFrm( HttpServletRequest req, Model model)
			throws IDException {
		HttpSession sess= req.getSession();
		Set<Integer> cart = (HashSet<Integer>) sess.getAttribute("cart");
		List<Product> lst = new ArrayList<>();
		for (Integer pid : cart) {
			lst.add(dao.getProductById(pid));
		}
		model.addAttribute("cartitems", lst);
		return "Cart";

	}

}
