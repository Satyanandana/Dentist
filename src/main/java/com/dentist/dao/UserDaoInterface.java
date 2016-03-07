package com.dentist.dao;

import com.dentist.domain.UserAuthentication;

public interface UserDaoInterface {

	public UserAuthentication UserAuthenticationInfoById(long id);

	public void SetUserAuthenticationInfo(UserAuthentication userAuthentication);

}
