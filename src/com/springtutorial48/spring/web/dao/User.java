package com.springtutorial48.spring.web.dao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.springtutorial48.spring.web.validation.ValidEmail;

public class User {

	@NotBlank
	@Size(min=5, max=20)
	@Pattern(regexp="^\\w{4,}$")
	private String username;
	
	@NotBlank(message="Password cannot be blank.")
	@Pattern(regexp="^\\S+$")
	@Size(min=6)
	private String password;
	
	@ValidEmail
	private String email;
	private boolean enabled;
	private String authority;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public User() {

	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", enabled=" + enabled
				+ ", authority=" + authority + "]";
	}

	public User(String username, String password, String email, boolean enabled, String authority) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
	}

}
