package com.springtutorial48.spring.web.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.springtutorial48.spring.web.dao.Notice;
import com.springtutorial48.spring.web.dao.NoticesDao;

@Service("noticesService")
public class NoticesService {

	private NoticesDao noticesDao;

	@Autowired
	public void setNoticesDao(NoticesDao noticesDao) {
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


	public boolean hasNotice(String name) {
		if(name == null) return false;
		
		List<Notice> notices = noticesDao.getNotices(name);
		
		if(notices.isEmpty()) {
			return false;
		}
		
		return true;
	}


	public Notice getUserNotice(String userName) {
		if(userName == null) {
			return null;
		}
		
		List<Notice> notices = noticesDao.getNotices(userName);
		if(notices == null || notices.size() == 0) {
			return null;
		}
		return notices.get(0);
	}


	public void saveOrUpdate(@Valid Notice notice) {
		//newly created notice object, id is not modified means not in database, so create
		//in database, id is auto increment, so id have value, so update
		if(notice.getId() == 0) {
			noticesDao.create(notice);
		} else {
			noticesDao.update(notice);
		}
		
	}


	public void deleteNotice(int id) {
		noticesDao.delete(id);		
	}
}
