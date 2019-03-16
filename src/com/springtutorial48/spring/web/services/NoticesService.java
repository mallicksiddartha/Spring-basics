package com.springtutorial48.spring.web.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.springtutorial48.spring.web.dao.Notice;
import com.springtutorial48.spring.web.dao.NoticesDAO;

@Service("noticesService")
public class NoticesService {

	private NoticesDAO noticesDao;

	@Autowired
	public void setNoticesDao(NoticesDAO noticesDao) {
		this.noticesDao = noticesDao;
	}
	
	
	public List<Notice> getCurrentData(){
		return this.noticesDao.getNotices();
	}


	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public void create(@Valid Notice notice) {
		noticesDao.create(notice);
		
	}


	public void getTestException() {
		noticesDao.getNotice(-14);
		
	}
}
