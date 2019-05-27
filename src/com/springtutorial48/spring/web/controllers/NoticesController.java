package com.springtutorial48.spring.web.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

	
	@RequestMapping(value="/createnotice")
	public String createNotice(Model model, Principal principal) {
		Notice notice = null;
		
		if(principal != null) {
			String userName = principal.getName();
			notice = noticesService.getUserNotice(userName);
		}
		
		if(notice == null) {
			notice = new Notice();
		}
		model.addAttribute(notice);
		return "create";
	}
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(Model model,@Valid Notice notice, BindingResult result, Principal principal
			,@RequestParam(value="delete", required=false) String delete) {
		
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors) {
				System.out.println("eita dekhay: " + error.getDefaultMessage());
			}
			return "create";
		} else {
			System.out.println("Form validated");
		}
		notice.getUser().setUsername(principal.getName());
		System.out.println("** notice controller, get username from notice: " + notice.getUsername());
		if(delete != null) {
			System.out.println("Delete is not null");
			noticesService.deleteNotice(notice.getId());
			return "noticeDeleted";
		} else {
			System.out.println("Delete is null");
			noticesService.saveOrUpdate(notice);
			System.out.println("Created notice: " + notice);
			return "noticeCreated";
		}
		
	}
}
