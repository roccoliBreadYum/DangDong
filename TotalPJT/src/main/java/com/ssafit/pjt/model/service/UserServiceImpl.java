package com.ssafit.pjt.model.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafit.pjt.model.dao.RefreshTokenDao;
import com.ssafit.pjt.model.dao.UserDao;
import com.ssafit.pjt.model.dto.User;
import com.ssafit.pjt.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	ResourceLoader resLoader;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Value("${jwt.refreshtoken.expiretime}")
	private int refreshTokenExpireTime;
	
	/**
	 * setter를 통해 UserDao를 주입받는다.
	 * 
	 * @Autowired를 통해 UserDao 타입의 빈을 주입 받는다.
	 * @param uDao
	 */
	
	private UserDao uDao;
	private RefreshTokenDao rDao;
	
	@Autowired
	public void setUserDao(UserDao uDao, RefreshTokenDao rDao) {
		this.uDao = uDao;
		this.rDao = rDao;
	}

	@Override
	public User findUser(String id) {
		return uDao.selectOne(id);
	}

	@Override
	public int signUser(User user, MultipartFile file) {
		//이미지파일 처리
		try {
			return fileHandling(user, file);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return 0;
	}

	@Override
	public int withdrawalUser(String id) {
		return uDao.delete(id);
	}

	@Override
	public int updateUser(User user, MultipartFile file) {
		//이미지파일 처리
				try {
					return fileHandling2(user, file);
				} catch (IOException e) {
					e.printStackTrace();
				} 
				
				return 0;
	}
	
	private int fileHandling(User user, MultipartFile file) throws IOException {
		// 파일을 저장할 폴더 지정 (자바 폴더 내에 지정)
		Resource res = resLoader.getResource("classpath:/static/resources");
//		//파일 저장할 때 이렇게 resource폴더에 하는게 좋음...
//		if(!res.getFile().exists()) {
//			res.getFile().mkdirs(); // 두 경로이므로 이겋게해야함.
//		}
		
		if (file != null && file.getSize() > 0) {
			// prefix를 포함한 전체 이름
			user.setImg(System.currentTimeMillis() + "_" + file.getOriginalFilename());
			user.setOrgImg(file.getOriginalFilename());

			// 변경된 파일 이름이 적용된 Movie MovieService를 통해 DB에 저장한다.

			file.transferTo(new File(res.getFile(), user.getImg()));
		}
		
		
//		//로컬에 저장
//		if (file != null && !file.isEmpty()) {
//            String userHome = System.getProperty("user.home");
//            String uploadDirPath = userHome + "/Desktop/createUser";
//            Path uploadPath = Paths.get(uploadDirPath);
//            if (!Files.exists(uploadPath)) {
//                Files.createDirectories(uploadPath);
//            }
//            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//            file.transferTo(new File(uploadDirPath, filename));
//            user.setImg(filename); // 파일 이름을 img 필드에 설정
//            user.setOrgImg(file.getOriginalFilename());
//        }
		
		return uDao.insert(user);
	}
	
	private int fileHandling2(User user, MultipartFile file) throws IOException {
		// 파일을 저장할 폴더 지정 (자바 폴더 내에 지정)
		Resource res = resLoader.getResource("classpath:/static/resources");
		
		if (file != null && file.getSize() > 0) {
			// prefix를 포함한 전체 이름
			user.setImg(System.currentTimeMillis() + "_" + file.getOriginalFilename());
			user.setOrgImg(file.getOriginalFilename());

			// 변경된 파일 이름이 적용된 Movie MovieService를 통해 DB에 저장한다.

			file.transferTo(new File(res.getFile(), user.getImg()));
		}
		
		return uDao.update(user);
	}

	public Map<String, Object> loginUser(User user, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();

		// DB에서 유저 가져오기
		User dbUser = uDao.loginUser(user);

		// 일치하는 유저가 없다면 UNAUTHORIZED 반환.
		if (dbUser == null) {
			result.put("message", "일치하는 유저가 없습니다.");
			return result;
		}

		// 있으면 Token 발급
		// AccessToken, RefreshToken 두 개를 발급해준다.
		String accessToken = jwtUtil.createAccessToken(dbUser.getId());
		String refreshToken = jwtUtil.createRefreshToken(dbUser.getId());
		
		//DB에 RefreshToken 저장
		Map<String, Object> map = new HashMap<>();
		map.put("refreshToken", refreshToken);
		map.put("userId", user.getId());
		rDao.insert(map);
		

		// accessToken만 응답 본문에 넣어서 보내고, refreshToken은 쿠키에 넣어서 보냄. =>
		// refreshToken은 브라우저에 자동 저장
		// accessToken만 sessionStorage 또는 pinia store에 저장.

		Cookie cookie = new Cookie("refreshToken", refreshToken);
		cookie.setMaxAge(refreshTokenExpireTime);
		cookie.setHttpOnly(false); // 클라이언트 측 JavaScript에서 접근 가능하게 설정
		cookie.setPath("/");
		cookie.setSecure(true); // HTTPS를 사용하는 경우에만
		cookie.setAttribute("SameSite", "None"); // 이 라인이 중요
		response.addCookie(cookie);

		result.put("access-token", accessToken);
//		result.put("name", dbUser.getName());
		
		return result;
	}

	@Override
	public Map<String, Object> refreshAccessToken(HttpServletRequest request) {
		//결과 담아서 반환할 맵 객체
		Map<String, Object> result = new HashMap<>();
		
		//쿠키에서 refreshToken 정보 받아오기
		Cookie[] cookies = request.getCookies();
		String refreshToken = null;
		for(Cookie c : cookies) {
			if(c.getName().equals("refreshToken")) {
				refreshToken = c.getValue();
			}
		}
		//db에서 토큰으로 저장된 유저 아이디 값 가져오기
		String dbUserId = rDao.selectUserIdByRefreshToken(refreshToken);
		
		//요청에서 acessToken가져오기
		String accessToken = request.getHeader("access-token");

		//토큰의 유저아이디 가져오기
		String tokenUserId = jwtUtil.getId(accessToken);
		if(dbUserId==null) {
			//리프래시 토큰이 없음
			result.put("message", "잘못된 토큰의 접근입니다.");
		}else if(dbUserId.equals(tokenUserId)) {
			//같은 사용자인지 검증
			//같다면 새로운 토큰 발급
			String newAccessToken = jwtUtil.createAccessToken(dbUserId);
			result.put("access-token", newAccessToken);
		}
		
		return result;
		
	}
	
}
