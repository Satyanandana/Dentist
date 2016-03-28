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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("prototype")
@Entity
@Table
public class PatientTeethStatus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7658490316069224796L;
	
	@EmbeddedId
	private TeethStatusPK TeethStatusPK;
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private TeethStatus teethStatus;
	@Column(nullable=false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime lastModified;
	
	public PatientTeethStatus() {
		
	}

	public TeethStatusPK getTeethStatusPK() {
		return TeethStatusPK;
	}

	public void setTeethStatusPK(TeethStatusPK teethStatusPK) {
		TeethStatusPK = teethStatusPK;
	}

	public TeethStatus getTeethStatus() {
		return teethStatus;
	}

	public void setTeethStatus(TeethStatus teethStatus) {
		this.teethStatus = teethStatus;
	}

	public DateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(DateTime lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((TeethStatusPK == null) ? 0 : TeethStatusPK.hashCode());
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
		PatientTeethStatus other = (PatientTeethStatus) obj;
		if (TeethStatusPK == null) {
			if (other.TeethStatusPK != null)
				return false;
		} else if (!TeethStatusPK.equals(other.TeethStatusPK))
			return false;
		return true;
	}
	
	

}
