package com.springtutorial48.spring.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springtutorial48.spring.web.dao.Notice;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showHome() {
		return "Home";
	}
}
