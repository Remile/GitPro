package com.hama.DAO.impl;

import com.hama.DAO.LoginDAO;
import com.hama.vo.User;

public class LoginDaoImpl implements LoginDAO {

	@Override
	public boolean login(User user) {
		// TODO Auto-generated method stub
		if((user.getUserName().equals("test"))&&(user.getPassWord().equals("test"))) {
			return true;
		}
		return false;
	}

}
