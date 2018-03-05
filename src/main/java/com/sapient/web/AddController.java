package com.sapient.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sapient.dao.IDException;
import com.sapient.dao.IProductDao;
import com.sapient.entity.Category;
import com.sapient.entity.Product;

@Controller
@PropertySource("classpath:eshop.properties")
public class AddController {

	@Autowired
	private IProductDao dao;
	@Value("${photo_folder}")
	private String photo_folder;
	
	private Logger logger = LoggerFactory.getLogger("eshopapp");

	@RequestMapping("addprodfrm")
	public String displayAddProductFrm(Model model) {
		logger.debug("in add product frm");
		Map<Integer, String> map = getCategories();
		model.addAttribute("pbean", new Product());
		model.addAttribute("cmap", map);
		model.addAttribute("idx", dao.getMaxProdId());
		return "AddProductFrm";
	}

	@RequestMapping(value = "addproduct", method = RequestMethod.POST)
	public String addProduct(@Valid @ModelAttribute("pbean") Product prod, BindingResult br, Model model,
			@RequestParam("fname") MultipartFile file) throws IDException, IOException {
		logger.debug("in add");
		// prod.setPid(dao.getMaxId());
		if (br.hasErrors()) {
			logger.debug("have errors {}", br.getAllErrors());
			model.addAttribute("idx", dao.getMaxProdId());
			model.addAttribute("cmap", getCategories());
			model.addAttribute("msg", "Product Not Added");
			return "AddProductFrm";
		}
		logger.debug("{}", prod.getPid());
		prod.setProdImg("prod"+prod.getPid() + ".jpg");
		dao.addProducts(prod);
		FileOutputStream fos = new FileOutputStream(photo_folder + prod.getProdImg());
		fos.write(file.getBytes());
		fos.close();
		model.addAttribute("msg", "Product Added");
		return "AddProductFrm";
	}

	public Map<Integer, String> getCategories() {
		List<Category> lst = dao.getCategories();
		Map<Integer, String> map = new HashMap<>();
		for (Category cat : lst) {
			map.put(cat.getCatId(), cat.getCatName());
		}
		return map;
	}

	@RequestMapping("addcatfrm")
	public String displayAddCategoryFrm(Model model) {
		logger.debug("in add category");
		model.addAttribute("cbean", new Category());
		model.addAttribute("idx", dao.getMaxCartId());
		return "AddCategoryFrm";
	}

	@RequestMapping(value = "addcategory", method = RequestMethod.POST)
	public String addCategory(@Valid @ModelAttribute("cbean") Category cat, BindingResult br, Model model,
			@RequestParam("fname") MultipartFile file) throws IDException, IOException {
		logger.debug("in add Category");
		if (br.hasErrors()) {
			logger.debug("have errors {}", br.getAllErrors());
			model.addAttribute("idx", dao.getMaxCartId());
			model.addAttribute("msg", "Category Not Added");
			return "AddCategoryFrm";
		}
		logger.debug("{}", cat.getCatId());
		cat.setCatImg("cat"+cat.getCatId() + ".jpg");
		dao.addCategory(cat);
		FileOutputStream fos = new FileOutputStream(photo_folder + cat.getCatImg());
		fos.write(file.getBytes());
		fos.close();
		model.addAttribute("msg", "Category Added");
		return "AddCategoryFrm";
	}
}
