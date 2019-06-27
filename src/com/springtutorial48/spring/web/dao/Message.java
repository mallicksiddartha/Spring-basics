package com.springtutorial48.spring.web.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.springtutorial48.spring.web.validation.ValidEmail;

@Entity
@Table(name="messages")
public class Message implements Serializable{
	
	private static final long serialVersionUID = 3058308311071805785L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name="subject")
	@Size(min=5, max=100)
	private String subject;
	
	@Column(name="content")
	@Size(min=5, max=1000)
	private String content;

	//email sender's name
	@Column(name="name")
	@Size(min=5, max=60)
	private String name;
	
	//email sender's email
	@Column(name="email")
	@ValidEmail
	private String email;
	
	@Column(name="username")
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Message() {

	}
	public Message(String subject, String content, String name, String email, String username) {
		this.subject = subject;
		this.content = content;
		this.name = name;
		this.email = email;
		this.username = username;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", subject=" + subject + ", content=" + content + ", name=" + name + ", email="
				+ email + ", username=" + username + "]";
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

}
