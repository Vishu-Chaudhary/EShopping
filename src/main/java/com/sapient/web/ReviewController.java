package com.sapient.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {
	
	@RequestMapping("showreviews")
	public String showReviews(@RequestParam("pid") Integer pid, Model model) {
		model.addAttribute("pid",pid);
		return "Review";
	}
}
