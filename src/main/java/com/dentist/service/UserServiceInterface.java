package com.dentist.service;

import com.dentist.domain.UserAuthentication;

public interface UserServiceInterface {

	public UserAuthentication UserAuthenticationInfoById(long id);

	public void setUserAuthenticationInfo(UserAuthentication userAuthentication);

}
