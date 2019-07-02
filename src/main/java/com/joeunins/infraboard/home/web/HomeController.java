package com.joeunins.infraboard.home.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joeunins.infraboard.home.domain.HomeMapper;

@Controller
public class HomeController {
	
	@Autowired
	private HomeMapper HomeMapper;
	
	@RequestMapping(value="/infraboard/home", method= {RequestMethod.GET, RequestMethod.POST})
	private String index(@RequestParam Map<String,Object> allRequestParams, Model model, HttpSession sess, RedirectAttributes rttr) throws Exception {
		
		return "home/index";
	}
	

}
