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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("prototype")
@Embeddable
public class Address implements Serializable {

	private static final long serialVersionUID = 7797213038067009978L;
	@Column(name = "address1",length=3000)
	//@Type(type = "encryptedString")
	private String address1;
	@Column(name = "address2",length=3000)
	//@Type(type = "encryptedString")
	private String address2;
	@Column(name = "city")
	//@Type(type = "encryptedString")
	private String city;
	@Column(name = "state")
	//@Type(type = "encryptedString")
	private String state;
	@Column(name = "zipcode")
	//@Type(type = "encryptedString")
	private String zipcode;

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
