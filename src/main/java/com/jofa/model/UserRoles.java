package com.jofa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@Entity
@Table(name = "user_roles", catalog = "db_user")
public class UserRoles implements java.io.Serializable
{
	
	private static final long serialVersionUID = -2787167093972940166L;
	private int userId;
	private User user;
	private String role;

	public UserRoles()
	{
	}

	public UserRoles(User user, String role)
	{
		this.user = user;
		this.role = role;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "user_id", unique = true, nullable = false)
	public int getUserId()
	{
		return this.userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	@JsonIgnore
	@XmlTransient
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getUser()
	{
		return this.user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Column(name = "role", nullable = false, length = 45)
	public String getRole()
	{
		return this.role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

}
