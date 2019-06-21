package com.springtutorial48.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

import com.springtutorial48.spring.web.dao.User;
import com.springtutorial48.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/springtutorial48/spring/web/config/dao-config.xml"
		, "classpath:com/springtutorial48/spring/web/config/security-context.xml"
		, "classpath:com/springtutorial48/spring/web/test/config/data-source.xml", 
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {
	
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from notices");
		jdbc.execute("delete from users");
	}
	
	//@Rollback(false)
	@Test
	public void testCreateUser() {
		User user = new User("Luibui", "Luibui Ninetail", "china1234", "luibui@springtest.com", true, "ROLE_USER");
		User user1 = new User("Hayako", "Shinjiro Hayako", "hellohello", "hayako@springtest.com", true, "ROLE_USER");
		User user2 = new User("Hanada", "Honda Hanada", "hellothere", "hanada@springtest.com", true, "ROLE_USER");
		User user3 = new User("Senpai", "Senpai Noticeme", "getthemoney", "senpai@springtest.com", true, "ROLE_USER");
		
		usersDao.create(user);
		
		List<User> users = usersDao.getAllUsers();
		
		
		assertEquals("User size needs to be 1. ", 1, users.size());
		
		assertTrue("User lui bui should exist", usersDao.exists(user.getUsername()));
		assertFalse("User akaimma should not be presebt", usersDao.exists("akaimma"));
		
		assertEquals("Created user should be identical to the user in the user list.", user, users.get(0));
		
		users = usersDao.getAllUsers();
		
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		
		//assertEquals("User size needs to be 1. ", 4, users.size());
		
		assertTrue("User lui bui should exist", usersDao.exists(user.getUsername()));
		assertTrue("User Hayako should exist", usersDao.exists(user1.getUsername()));
		assertTrue("User Hanada should exist", usersDao.exists(user2.getUsername()));
		assertTrue("User Senpai should exist", usersDao.exists(user3.getUsername()));
		
	}
}
