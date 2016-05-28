package com.jofa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "login_attempt", catalog = "db_user")
public class LoginAttempt implements java.io.Serializable
{

	private static final long serialVersionUID = -2274506872798547691L;
	private Integer id;
	private String userName;
	private String userAgent;
	private boolean successful;
	private String ip;
	private Date attemptDate;

	public LoginAttempt()
	{
	}

	public LoginAttempt(String userName, String userAgent, boolean successful, String ip, Date attemptDate)
	{
		this.userName = userName;
		this.userAgent = userAgent;
		this.successful = successful;
		this.ip = ip;
		this.attemptDate = attemptDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Column(name = "username", nullable = false)
	public String getUsername()
	{
		return this.userName;
	}

	public void setUsername(String userName)
	{
		this.userName = userName;
	}

	@Column(name = "user_agent", nullable = false, length = 1024)
	public String getUserAgent()
	{
		return this.userAgent;
	}

	public void setUserAgent(String userAgent)
	{
		this.userAgent = userAgent;
	}

	@Column(name = "successful", nullable = false)
	public boolean isSuccessful()
	{
		return this.successful;
	}

	public void setSuccessful(boolean successful)
	{
		this.successful = successful;
	}

	@Column(name = "ip", nullable = false, length = 45)
	public String getIp()
	{
		return this.ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "attempt_date", length = 19,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable=false, updatable=false)
	public Date getAttemptDate() {
		return this.attemptDate;
	}

	public void setAttemptDate(Date attemptDate) {
		this.attemptDate = attemptDate;
	}

}
