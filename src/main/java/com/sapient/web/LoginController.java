package com.sapient.web;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.dao.IProductDao;
import com.sapient.dao.IRegisterDao;
import com.sapient.dao.NotFoundException;
import com.sapient.dao.UserNameExistsException;
import com.sapient.entity.UserDetails;

@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger("eshopapp");
	@Autowired
	private IRegisterDao rdao;
	
	@Autowired
	private IProductDao pdao;
	
	@RequestMapping(value = "/")
	public String showLoginForm() {
		return "LoginFrm";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String doLogin(@RequestParam("txtuname") String userName, @RequestParam("txtpwd") String pwd, Model model, HttpServletRequest req) throws NotFoundException  {

		UserDetails user  =rdao.getUser(userName);
		if(user!=null && user.getPwd().equals(pwd)){
			HttpSession sess = req.getSession();
			sess.setAttribute("cart", new HashSet<Integer>());
			logger.debug("session created " + sess.getAttribute("cart"));
			sess.setAttribute("login", user);
			sess.setMaxInactiveInterval(10*60);
			model.addAttribute("catlist",pdao.getCategories());
			sess.setAttribute("cartcount", 0);
			return "Main";
			}else{
				throw new NotFoundException("LoginFailed");
			}
	}
	
	@RequestMapping(value="getregister")
	public String showRegisterForm() {
		return "RegisterFrm";
	}
	
	@RequestMapping(value="register")
	public String doRegister(@RequestParam("txtuname") String userName, 
						     @RequestParam("txtpwd") String pwd,  
						     HttpServletRequest req) throws UserNameExistsException {
		UserDetails user = new UserDetails();
		user.setUserName(userName);
		user.setPwd(pwd);
		rdao.addUser(user);
		return "Main";
	}
	
	@RequestMapping("logout")
	public String doLogout(HttpServletRequest req){
		HttpSession sess = req.getSession(false);
		if(sess !=null){
			sess.invalidate();
		}
		return "LoginFrm";
	}

}
