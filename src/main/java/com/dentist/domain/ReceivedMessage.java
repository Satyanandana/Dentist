package com.dentist.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "recieved_messages")
public class ReceivedMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9026008944956737494L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long messageID;
	@ManyToOne
	@JoinColumn(name = "receiverID", nullable = false)
	private Patient receiver;
	@Column
	private String msg;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime receivedTime;

	public ReceivedMessage() {

	}

	public long getMessageID() {
		return messageID;
	}

	public void setMessageID(long messageID) {
		this.messageID = messageID;
	}

	public Patient getReceiver() {
		return receiver;
	}

	public void setReceiver(Patient receiver) {
		this.receiver = receiver;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public DateTime getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(DateTime receivedTime) {
		this.receivedTime = receivedTime;
	}

}
