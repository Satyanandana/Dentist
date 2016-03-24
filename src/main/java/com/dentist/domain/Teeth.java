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
import javax.persistence.Table;

@Entity
@Table
public class Teeth implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6175288134531677286L;
	@Id
	private int teethID;
	@Column(nullable=false)
	private String teethName;
	@Column
	private String Description;
	
	public Teeth() {
		
	}

	public int getTeethID() {
		return teethID;
	}

	public void setTeethID(int teethID) {
		this.teethID = teethID;
	}

	public String getTeethName() {
		return teethName;
	}

	public void setTeethName(String teethName) {
		this.teethName = teethName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + teethID;
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
		Teeth other = (Teeth) obj;
		if (teethID != other.teethID)
			return false;
		return true;
	}
	
}
