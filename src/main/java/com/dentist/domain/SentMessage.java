package com.dentist.domain;

/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Mar 17, 20161:10:28 AM
*       
*/
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Scope("prototype")
@Entity
@Table(name = "sent_messages")
public class SentMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7992099178155998939L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long messageID;
	@Transient
	private long senderID;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "senderID", nullable = false)
	private Patient sender;
	@Column(nullable = false, length = 6000)
	// @Type(type = "encryptedString")
	private String msg;
	@Column(nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime sentTime;

	public SentMessage() {

	}

	public long getMessageID() {
		return messageID;
	}

	public void setMessageID(long messageID) {
		this.messageID = messageID;
	}

	public long getSenderID() {
		if (this.sender != null) {
			this.senderID = sender.getUserID();
		}
		return senderID;
	}

	public Patient getSender() {
		return sender;
	}

	public void setSender(Patient sender) {
		this.sender = sender;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public DateTime getSentTime() {
		return sentTime;
	}

	public void setSentTime(DateTime sentTime) {
		this.sentTime = sentTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (messageID ^ (messageID >>> 32));
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
		SentMessage other = (SentMessage) obj;
		if (messageID != other.messageID)
			return false;
		return true;
	}

}
