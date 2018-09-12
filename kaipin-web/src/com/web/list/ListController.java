package com.web.list;

import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ListController {

	public static String DETAIL = "detail";
	public static String LIST = "list";
	
	@RequestMapping("/list/{id}")
	public String list(@PathVariable String id){
		try {
			System.out.println(">>> list id = " + id);
			return LIST;
		} catch (Exception e) {
			return LIST;
		}
	}
	
	@RequestMapping("/{id}.html")
	public String detail(@PathVariable String id, Model model){
		try {
			model.addAttribute("articleId", id);
			return DETAIL;
		} catch (Exception e) {
			return DETAIL;
		}
	}
}
