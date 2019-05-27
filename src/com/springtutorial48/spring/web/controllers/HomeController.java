package com.springtutorial48.spring.web.controllers;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springtutorial48.spring.web.dao.Notice;
import com.springtutorial48.spring.web.services.NoticesService;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	NoticesService noticesService;

	@RequestMapping("/")
	public String showHome(Model model, Principal principal) {
		logger.info("Showing home...");
		List<Notice> notices = noticesService.getCurrentData();

		model.addAttribute("notices", notices);
		
		boolean hasNotice = false;
		if(principal != null) {
			hasNotice = noticesService.hasNotice(principal.getName());
		}
		model.addAttribute("hasNotice", hasNotice);

		return "Home";
	}

}
