package com.springtutorial48.spring.web.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.springtutorial48.spring.web.dao.User;
import com.springtutorial48.spring.web.dao.UsersDao;

@Service("usersService")
public class UsersService {

	private UsersDao usersDao;
	
	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}



	public void create(@Valid User user) {
		usersDao.create(user);
		
	}



	public boolean exists(String username) {
		
		return usersDao.exists(username);
	}



	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}

}
