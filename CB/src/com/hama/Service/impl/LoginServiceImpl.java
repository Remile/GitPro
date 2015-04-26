package com.hama.Service.impl;

import com.hama.DAO.LoginDAO;
import com.hama.DAO.impl.LoginDaoImpl;
import com.hama.Service.LoginService;
import com.hama.vo.User;

public class LoginServiceImpl implements LoginService {
	
	LoginDAO dao = new LoginDaoImpl();

	@Override
	public boolean Login(User user) {
		// TODO Auto-generated method stub
		
		return dao.login(user);
	}

}
