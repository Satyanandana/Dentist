package com.dentist.service;

/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Mar 17, 20161:10:28 AM
*       
*/
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.dentist.domain.UserAuthentication;

/**
 * 
 */

public class CustomUserDetails extends UserAuthentication implements UserDetails {

	private static final long serialVersionUID = -7118647664323838181L;
	private long user_id;

	public CustomUserDetails(UserAuthentication userAuth) {
		super(userAuth.getUserID(), userAuth.getUserEmail(), userAuth.getUserPwd(), userAuth.getUserIp(),
				userAuth.getCreationTime(), userAuth.getLastLoginTime(), userAuth.getAccountStatus(),
				userAuth.getUserRole(), userAuth.getVerifyKey());
		this.user_id = userAuth.getUserID();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(getUserRole().toString());
		return authorities;
	}

	@Override
	public String getPassword() {
		return getUserPwd();
	}

	@Override
	public String getUsername() {
		return getUserEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (user_id ^ (user_id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomUserDetails other = (CustomUserDetails) obj;
		if (user_id != other.user_id)
			return false;
		return true;
	}

}
