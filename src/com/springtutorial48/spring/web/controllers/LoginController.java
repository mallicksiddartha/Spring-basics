package com.springtutorial48.spring.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springtutorial48.spring.web.dao.Notice;
import com.springtutorial48.spring.web.dao.User;
import com.springtutorial48.spring.web.services.UsersService;

@Controller
public class LoginController {
	private UsersService usersService;

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
	@RequestMapping("/admin")
	public String showAdminPage(Model model) {
		List<User> users = usersService.getAllUsers();
		model.addAttribute("users", users);
		
		return "adminPage";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedOut";
	}
	
	@RequestMapping("/createaccount")
	public String showCreateAccount(Model model) {
		
		model.addAttribute("user", new User());
		return "createAccount";
	}
	
	
	@RequestMapping(value="/accountcreated", method=RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {
		if(result.hasErrors()) {
			return "createAccount";
		}
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		if(usersService.exists(user.getUsername())) {
			result.rejectValue("username", "Duplicate.Key.user.username");
			return "createAccount";
		}
		
		/* Try catch block for duplicate username database exception
		 * 
		try {
			usersService.create(user);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			result.rejectValue("username", "Duplicate.Key.user.username", "This username already exists.");
			return "createAccount";
		}*/
		System.out.println("###################################################  in login controller create user method");
		usersService.create(user);
		System.out.println("Created notice: " + user);
		return "accountCreated";
	}
}
