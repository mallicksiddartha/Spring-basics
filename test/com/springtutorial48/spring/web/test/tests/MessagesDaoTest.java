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

import com.springtutorial48.spring.web.dao.Message;
import com.springtutorial48.spring.web.dao.MessagesDao;
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
public class MessagesDaoTest {

	@Autowired
	private MessagesDao messagesDao;
	
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from notices");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testNotices() {
		User user = new User("Luibui", "Luibui Ninetail", "china1234", "luibui@springtest.com", true, "ROLE_USER");
		User user1 = new User("Jacky", "Jacky William", "jack123456", "jacky@springtest.com", true, "ROLE_USER");
		User user2 = new User("Billy", "Bill James", "bill123456", "billy@springtest.com", true, "ROLE_USER");
		User user3 = new User("James", "James Snow", "snow123456", "james@springtest.com", true, "ROLE_USER");

		usersDao.create(user);
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		
		Message message1 = new Message("Test message subject 1", "Test message content 1", "Artemis", "artemis@springtest.com", "Luibui");
		
		messagesDao.create(message1);
	}
}
