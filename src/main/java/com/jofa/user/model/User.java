package com.jofa.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "id")})
public class User implements java.io.Serializable {

	private static final long serialVersionUID = -972384603595328514L;
	
	private Integer id;
	private String email;
	private String username;
	private String password;
	private boolean isAdmin;

	public User() {
	}

	public User(Integer id, String email, String username, String password, boolean isAdmin) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.setPassword(password);
		this.setAdmin(isAdmin);
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false, length = 9)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "email", unique = true, nullable = false, length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	@Column(name = "password", nullable = false, length = 45)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "admin", nullable = false,unique = false)
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", id=" + id + ", userName=" + username + "]";
	}
}
