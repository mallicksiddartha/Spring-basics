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
@Component("noticesDao")
public class NoticesDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	public NoticesDao() {

	}
	
	public List<Notice> getNotices(){
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<Notice> crit = builder.createQuery(Notice.class);
		Root<User> rootUser =  crit.from(User.class);
		Root<Notice> rootNotice =  crit.from(Notice.class);
		crit.select(rootNotice).where(builder.and(
				builder.equal(rootNotice.get("user"), rootUser.get("username")),
				builder.equal(rootUser.get("enabled"), true)
				));		
		Query<Notice> query = session().createQuery(crit);
		List<Notice> result = query.getResultList();
		return result;
	}
	
	public List<Notice> getNotices(String username){
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<Notice> crit = builder.createQuery(Notice.class);
		Root<User> rootUser =  crit.from(User.class);
		Root<Notice> rootNotice =  crit.from(Notice.class);
		crit.select(rootNotice).where(builder.and(
				builder.equal(rootNotice.get("user"), rootUser.get("username")),
				builder.equal(rootUser.get("enabled"), true),
				builder.equal(rootUser.get("username"), username)
				));		
		Query<Notice> query = session().createQuery(crit);
		List<Notice> result = query.getResultList();
		return result;
	}
	
	public Notice getNotice(int id){
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<Notice> crit = builder.createQuery(Notice.class);
		Root<Notice> rootNotice =  crit.from(Notice.class);
		//crit.multiselect(rootNotice, rootUser).where(builder.equal(rootUser.get("enabled"), true));
		crit.select(rootNotice).where(builder.equal(rootNotice.get("id"), id));		
		Query<Notice> query = session().createQuery(crit).setMaxResults(1);
		List<Notice> result = query.getResultList();
		return result.get(0);
	}
	
	public boolean delete(int id) {
		
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaDelete<Notice> crit = builder.createCriteriaDelete(Notice.class);
		Root<Notice> rootNotice =  crit.from(Notice.class);
		crit.where(builder.equal(rootNotice.get("id"), id));	
		
		int result = session().createQuery(crit).executeUpdate();
		return result == 1;
	}
	
	public void create(Notice notice) {
		session().saveOrUpdate(notice);
	}
	
	public void update(Notice notice) {
		session().saveOrUpdate(notice);
	}

}
