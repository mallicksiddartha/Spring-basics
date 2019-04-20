package com.springtutorial48.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
	}
	@Test
	public void testCreateUser() {
		User user = new User("Luibui", "china123", "luibui@springtest.com", true, "ROLE_USER");
		
		assertTrue("Lui bui usr created returns true", usersDao.create(user));
	}
}
