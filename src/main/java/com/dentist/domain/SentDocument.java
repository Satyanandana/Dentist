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

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Scope("prototype")
@Entity
@Table(name = "documents_sent")
public class SentDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3133118092042068053L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long docID;
	@Transient
	private long senderID;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "senderID", nullable = false)
	private Patient sender;
	@Column(nullable = false)
	@Type(type = "encryptedString")
	private String fileName;
	@Column(nullable = false)
	@Type(type = "encryptedString")
	private String fileExt;
	@Column(nullable = false)
	@Type(type = "encryptedString")
	private String path;
	@Column(nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime sentTime;

	public SentDocument() {

	}

	public long getDocID() {
		return docID;
	}

	public void setDocID(long docID) {
		this.docID = docID;
	}

	@JsonGetter
	public long getSenderID() {
		if (this.sender != null) {
			this.senderID = sender.getUserID();
		}
		return senderID;
	}

	public void setSenderID(long uploaderID) {
		this.senderID = uploaderID;
	}

	public Patient getSender() {
		return sender;
	}

	public void setSender(Patient sender) {
		this.sender = sender;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
		result = prime * result + (int) (docID ^ (docID >>> 32));
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
		SentDocument other = (SentDocument) obj;
		if (docID != other.docID)
			return false;
		return true;
	}

}
