package com.springtutorial48.spring.web.controllers;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springtutorial48.spring.web.dao.Notice;
import com.springtutorial48.spring.web.services.NoticesService;

@Controller
public class NoticesController {

	private NoticesService noticesService;
	
	@Autowired
	public void setNoticesService(NoticesService noticesService) {
		this.noticesService = noticesService;
	}
	
//	@ExceptionHandler(DataAccessException.class)
//	public String handleDatabaseException(DataAccessException ex) {
//		return "databaseError";
//	}

	@RequestMapping(value="/test")
	public String showTest(Model model, @RequestParam("id") String id) {
		
		System.out.println("The id is: " + id);
		
		return "Home";
	}

	@RequestMapping(value="/notices")
	public String showNotices(Model model) {
		
		/**
		 * This random variable is randomly throwing a 
		 * custom made exception to trigger the 
		 * database exception handling class
		 */
		Random rand = new Random();
		if(rand.nextInt(100) == 58) {
			noticesService.getTestException();
		}
		
		List<Notice> notices = this.noticesService.getCurrentData();
		
		model.addAttribute("notices", notices);
		
		return "notices";
	}
	
	@RequestMapping(value="/createnotice")
	public String createNotice(Model model) {
		model.addAttribute(new Notice());
		return "create";
	}
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(Model model,@Valid Notice notice, BindingResult result) {
		
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors) {
				System.out.println("eita dekhay: " + error.getDefaultMessage());
			}
			return "create";
		} else {
			System.out.println("Form validated");
		}
		
		noticesService.create(notice);
		System.out.println("Created notice: " + notice);
		return "noticeCreated";
	}
}
