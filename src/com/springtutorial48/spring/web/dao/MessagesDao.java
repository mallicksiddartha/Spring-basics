package com.springtutorial48.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.jdbc.MysqlParameterMetadata;

@Repository
@Transactional
@Component("messagesDao")
public class MessagesDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	public MessagesDao() {

	}
	
	public List<Message> getMessages(){
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<Message> crit = builder.createQuery(Message.class);
		Root<User> rootUser =  crit.from(User.class);
		Root<Message> rootMessage =  crit.from(Message.class);
		crit.select(rootMessage).where(builder.and(
				builder.equal(rootMessage.get("user"), rootUser.get("username")),
				builder.equal(rootUser.get("enabled"), true)
				));		
		Query<Message> query = session().createQuery(crit);
		List<Message> result = query.getResultList();
		return result;
	}
	
	public List<Message> getMessages(String username){
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<Message> crit = builder.createQuery(Message.class);
		Root<Message> rootMessage =  crit.from(Message.class);
		crit.select(rootMessage).where(builder.equal(rootMessage.get("username"), username));		
		Query<Message> query = session().createQuery(crit);
		List<Message> result = query.getResultList();
		return result;
	}
	
	public Message getMessage(int id){
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<Message> crit = builder.createQuery(Message.class);
		Root<Message> rootMessage =  crit.from(Message.class);
		crit.select(rootMessage).where(builder.equal(rootMessage.get("id"), id));		
		Query<Message> query = session().createQuery(crit).setMaxResults(1);
		List<Message> result = query.getResultList();
		return result.get(0);
	}
	
	public boolean delete(int id) {
		
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaDelete<Message> crit = builder.createCriteriaDelete(Message.class);
		Root<Message> rootMessage =  crit.from(Message.class);
		crit.where(builder.equal(rootMessage.get("id"), id));	
		
		int result = session().createQuery(crit).executeUpdate();
		return result == 1;
	}
	
	public void saveOrUpdate(Message message) {
		session().saveOrUpdate(message);
	}

}
