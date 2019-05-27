package com.springtutorial48.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.jdbc.MysqlParameterMetadata;

@Component("noticesDao")
public class NoticesDao {
	private NamedParameterJdbcTemplate jdbc;
	
	
	
	public NoticesDao() {
		System.out.println("Creating notices dao.");
	}

	@Autowired
	public void setDataSource(DataSource source) {
		this.jdbc = new NamedParameterJdbcTemplate(source);
	}
	
	public List<Notice> getNotices(){		
		return jdbc.query("SELECT * FROM notices, users where notices.username=users.username and users.enabled=true"
				, new NoticeRowMapper());
	}
	
	public List<Notice> getNotices(String username){		
		return jdbc.query("SELECT * FROM notices, users where notices.username=users.username and users.enabled=true and notices.username=:username"
				, new MapSqlParameterSource("username", username), new NoticeRowMapper());
	}
	
	public Notice getNotice(int id){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return jdbc.queryForObject("SELECT * FROM notices, users where notices.username=users.username and users.enabled=true and id = :id", params
				, new NoticeRowMapper());
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		System.out.println("************111111111444444444*********" + id);
		params.addValue("id", id);
		return jdbc.update("delete from notices where id = :id", params) == 1;
	}
	
	public boolean create(Notice notice) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$"+notice);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(notice);
		
		return jdbc.update("insert into notices (username, text) values (:username, :text)", params) == 1;
	}
	
	public boolean update(Notice notice) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(notice);
		
		return jdbc.update("update notices set text=:text where id=:id;", params) == 1;
	}
	
	@Transactional
	public int[] batchCreate(List<Notice> notices) {
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(notices);

		return jdbc.batchUpdate("insert into notices (username, text) values (:username, :text)", params);
	}

}
