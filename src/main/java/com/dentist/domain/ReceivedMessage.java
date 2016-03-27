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

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Transient
	private long receiverID;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "receiverID", nullable = false,updatable=false)
	private Patient receiver;
	@Column(nullable=false)
	// @Type(type = "encryptedString")
	private String msg;
	@Column(nullable=false)
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
    
	@JsonGetter
	public long getReceiverID() {
		if(this.receiver!=null){
			this.receiverID = receiver.getUserID();
		}
		return receiverID;
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
		ReceivedMessage other = (ReceivedMessage) obj;
		if (messageID != other.messageID)
			return false;
		return true;
	}
	
	

}
