package com.springtutorial48.spring.web.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.springtutorial48.spring.web.dao.Message;
import com.springtutorial48.spring.web.dao.MessagesDao;
import com.springtutorial48.spring.web.dao.User;
import com.springtutorial48.spring.web.dao.UsersDao;

@Service("usersService")
public class UsersService {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private MessagesDao messagesDao;
	
	public void create(@Valid User user) {
		usersDao.create(user);
		
	}

	public boolean exists(String username) {
		return usersDao.exists(username);
	}


	public User getUser(String username) {
		if(username == null) {
			return null;
		}
		return usersDao.getUser(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}

	public void sendMessage(Message message) {
		System.out.println(message);
		messagesDao.saveOrUpdate(message);
	}
}
