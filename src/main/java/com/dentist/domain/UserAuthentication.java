package com.dentist.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")

@Entity
@Table(name = "user_auth")
public class UserAuthentication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9192065925357347057L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userID;
	@Column(nullable = false, unique = true, updatable = false)
	private String userEmail;
	@Column(nullable = false)
	@Type(type = "encryptedString")
	private String userPwd;
	@Column(nullable = false)
	private String userIp;
	@Column(nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime creationTime;
	@Column(nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime lastLoginTime;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role userRole;
	@Column(nullable = false)
	@Type(type = "encryptedString")
	private String verifyKey;

	public UserAuthentication() {

	}

	public UserAuthentication(String userEmail, String userPwd, String userIp, DateTime creationTime,
			DateTime lastLoginTime, AccountStatus accountStatus, Role useRole, String verifyKey) {
		super();
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.userIp = userIp;
		this.creationTime = creationTime;
		this.lastLoginTime = lastLoginTime;
		this.accountStatus = accountStatus;
		this.userRole = useRole;
		this.verifyKey = verifyKey;
	}

	// required for CustomUserDetails object in spring security
	public UserAuthentication(long userID, String userEmail, String userPwd, String userIp, DateTime creationTime,
			DateTime lastLoginTime, AccountStatus accountStatus, Role useRole, String verifyKey) {
		this(userEmail, userPwd, userIp, creationTime, lastLoginTime, accountStatus, useRole, verifyKey);
		this.userID = userID;

	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public DateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(DateTime creationTime) {
		this.creationTime = creationTime;
	}

	public DateTime getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(DateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role useRole) {
		this.userRole = useRole;
	}

	public String getVerifyKey() {
		return verifyKey;
	}

	public void setVerifyKey(String verifyKey) {
		this.verifyKey = verifyKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + (int) (userID ^ (userID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAuthentication other = (UserAuthentication) obj;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}
	
	
}
