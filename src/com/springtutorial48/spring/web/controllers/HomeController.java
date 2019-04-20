package com.springtutorial48.spring.web.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springtutorial48.spring.web.dao.Notice;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);
	@RequestMapping("/")
	public String showHome() {
		logger.info("Showing home...");
		return "Home";
	}
	
}
