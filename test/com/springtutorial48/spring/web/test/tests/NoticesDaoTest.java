package com.springtutorial48.spring.web.test.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springtutorial48.spring.web.dao.Notice;
import com.springtutorial48.spring.web.dao.NoticesDao;
import com.springtutorial48.spring.web.dao.User;
import com.springtutorial48.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/springtutorial48/spring/web/config/dao-config.xml"
		, "classpath:com/springtutorial48/spring/web/config/security-context.xml"
		, "classpath:com/springtutorial48/spring/web/test/config/data-source.xml", 
})
@RunWith(SpringJUnit4ClassRunner.class)
public class NoticesDaoTest {

	@Autowired
	private NoticesDao noticesDao;
	
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		//jdbc.execute("delete from notices");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testNotices() {
		User user = new User("Luibui", "Luibui Ninetail", "china1234", "luibui@springtest.com", true, "ROLE_USER");

		usersDao.create(user);
		
		Notice notice = new Notice(user, "This is a test notice..");
		
		noticesDao.create(notice);
		
		List<Notice> notices = noticesDao.getNotices();
		
		assertEquals("Should be only 1 notice in the database", 1, notices.size());
		assertEquals("Created notice should be identical to the notice in the notice list.", notice, notices.get(0));
		
		notice = notices.get(0);
		notice.setText("Updated notice text!!!");
		noticesDao.update(notice);
		
		Notice updated = noticesDao.getNotice(notice.getId());
		assertEquals("Retrived updated notice should be identical to the created notice", notice, updated);
		
		Notice notice2 = new Notice(user, "New test notice for testing, ching ching ching");
		noticesDao.create(notice2);
		
		List<Notice> userSpecificNotice = noticesDao.getNotices(user.getUsername());
		assertEquals("Should be 2 notice for luibui", 2, userSpecificNotice.size());
		
		assertTrue("Deleting notice, should return true from delete method from noticeDao ", noticesDao.delete(notice.getId()));
		
		assertTrue("Deleting notice2, should return true from delete method from noticeDao ", noticesDao.delete(notice2.getId()));
		
		List<Notice> empty = noticesDao.getNotices();
		assertEquals("Notices list should be empty after notice is deleted", 0, empty.size());
	}
}
