package com.dentist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dentist.dao.UserDaoInterface;
import com.dentist.domain.UserAuthentication;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInterface {
	@Autowired
	private UserDaoInterface userDaoInterface;

	public UserAuthentication UserAuthenticationInfoById(long id) {

		return userDaoInterface.UserAuthenticationInfoById(id);
	}

	public void setUserAuthenticationInfo(UserAuthentication userAuthentication) {
		userDaoInterface.SetUserAuthenticationInfo(userAuthentication);
	}

}
