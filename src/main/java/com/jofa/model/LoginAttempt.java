package com.jofa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "login_attempt", catalog = "db_user")
public class LoginAttempt implements java.io.Serializable
{

	private static final long serialVersionUID = -2274506872798547691L;
	private Integer id;
	private Integer userId;
	private String userAgent;
	private boolean successful;
	private String ip;

	public LoginAttempt()
	{
	}

	public LoginAttempt(Integer userId, String userAgent, boolean successful, String ip)
	{
		this.userId = userId;
		this.userAgent = userAgent;
		this.successful = successful;
		this.ip = ip;
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

	@Column(name = "user_id", nullable = false)
	public Integer getUserId()
	{
		return this.userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
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

}
