package com.ssafit.pjt.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.UserDao;
import com.ssafit.pjt.model.dto.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	ResourceLoader resLoader;

	/**
	 * setter를 통해 UserDao를 주입받는다.
	 * 
	 * @Autowired를 통해 UserDao 타입의 빈을 주입 받는다.
	 * @param uDao
	 */
	
	private UserDao uDao;
	
	@Autowired
	public void setUserDao(UserDao uDao) {
		this.uDao = uDao;
	}

	@Override
	public User findUser(String id) {
		return uDao.selectOne(id);
	}

	@Override
	public int signUser(User user) {
		return uDao.insert(user);
	}

	@Override
	public int withdrawalUser(String id) {
		return uDao.delete(id);
	}

	@Override
	public int updateUser(User user) {
		return uDao.update(user);
	}
	
	
	
}
