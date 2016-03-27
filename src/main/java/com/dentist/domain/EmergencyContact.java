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
import javax.persistence.Embeddable;

@Embeddable
public class EmergencyContact implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3266512225087243884L;
	
	@Column
	//@Type(type = "encryptedString")
	private String name;
	@Column
	//@Type(type = "encryptedString")
	private String phoneNumber;
	@Column
	//@Type(type = "encryptedString")
	private String relation;
	
	public EmergencyContact() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String number) {
		this.phoneNumber = number;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
		
	
}
