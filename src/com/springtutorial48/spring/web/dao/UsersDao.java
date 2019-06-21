package com.springtutorial48.spring.web.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	public UsersDao() {
		
	}

	public void create(User user) {
		
		/*params.addValue("username", user.getUsername());
		params.addValue("password", passwordEncoder.encode(user.getPassword()));
		params.addValue("email", user.getEmail());
		params.addValue("name", user.getName());
		params.addValue("authority", user.getAuthority());
		params.addValue("enabled", user.isEnabled());
		
		//insert into users table
		return jdbc.update("insert into users (username, name, password, email, authority, enabled) values (:username, :name, :password, :email, :authority, :enabled);"
				, params) == 1;*/
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			session().save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean exists(String username) {
		
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<User> crit = builder.createQuery(User.class);
		Root<User> root =  crit.from(User.class);
		crit.select(root).where(builder.equal(root.get("username"), username));
		
		Query<User> query = session().createQuery(crit);
		List<User> results = query.getResultList();
		return !results.isEmpty();
		
		/*return jdbc.queryForObject("select count(*) from users where username=:username"
				, new MapSqlParameterSource("username", username), Integer.class) > 0;*/
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		/*return jdbc.query("select * from users"
				, BeanPropertyRowMapper.newInstance(User.class));*/
		
		return session().createQuery("from User").list();
	}
	


}
