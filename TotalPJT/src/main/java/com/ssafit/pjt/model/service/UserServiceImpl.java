package com.ssafit.pjt.model.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		/*
		 * 이미지 파일 처리
		 * 
		 * */
//		fileHandling(user, file); //비즈니스 로직
		
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
	
	private void fileHandling(User user, MultipartFile file) throws IOException {
		// 파일을 저장할 폴더 지정
		Resource res = resLoader.getResource("resources/upload");
		//파일 저장할 때 이렇게 resource폴더에 하는게 좋음...
		if(!res.getFile().exists()) {
			res.getFile().mkdirs(); // 두 경로이므로 이겋게해야함.
		}
		
		if (file != null && file.getSize() > 0) {
			// prefix를 포함한 전체 이름
			user.setImg(System.currentTimeMillis() + "_" + file.getOriginalFilename());
			user.setOrgImg(file.getOriginalFilename());

			// 변경된 파일 이름이 적용된 Movie MovieService를 통해 DB에 저장한다.

			file.transferTo(new File(res.getFile().getCanonicalPath() + "/" + user.getImg()));
		}

	}

	@Override
	public User loginUser(User user) {
		return uDao.loginUser(user);
	}
	
}
