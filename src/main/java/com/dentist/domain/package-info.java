/**
 * 
 *
 * @author Satyanandana Srikanthvarma Vadapalli
 * @email srikanthvarma.vadapalli@gmail.com
 * @version 1.0
 * @since Mar 17, 20161:10:28 AM
 * 
 */

@TypeDefs({ @TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = {
		@Parameter(name = "encryptorRegisteredName", value = "HibernateStringEncryptor") }) })
package com.dentist.domain;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.annotations.Parameter;
import org.jasypt.hibernate4.type.EncryptedStringType;
