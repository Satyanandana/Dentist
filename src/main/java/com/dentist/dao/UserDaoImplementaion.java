package com.dentist.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dentist.domain.UserAuthentication;

@Repository
public class UserDaoImplementaion extends DbDao implements UserDaoInterface {

	public UserAuthentication UserAuthenticationInfoById(long id) {

		Criteria criteria = getSession().createCriteria(UserAuthentication.class).add(Restrictions.idEq(id));
		return (UserAuthentication) criteria.uniqueResult();
	}

	public void SetUserAuthenticationInfo(UserAuthentication userAuthentication) {
		persist(userAuthentication);

	}

}
