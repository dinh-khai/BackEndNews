package com.news.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.news.config.JwtFilter;
import com.news.config.JwtProvider;
import com.news.dto.UserDTO;
import com.news.entity.User;
import com.news.mapper.MapperDTO;
import com.news.repos.UserRepos;
import com.news.service.UpLoadService;
import com.news.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepos userRepos;
	
	@Autowired
	UpLoadService upload;
	
	@Autowired
	MapperDTO mapperDTO;
	
	@Autowired
	JwtProvider jwt;
	@Autowired
	JwtFilter filter;

	@Override
	public String save(String userName,String fullName,String password,String email
						,MultipartFile file,String serverName,int port) {
		String defaultImage="http://" + serverName + ":" + port + "/image/macdinh.png";
		try {
			Optional<User>op= userRepos.findById(userName);
			op.get();
			return "err";
		} catch (Exception e) {
				String imageURL=upload.upload(file, "user",defaultImage, serverName,port);
				User user =new User();
				user.setUserName(userName);
				user.setFullName(fullName);
				user.setPassword(password);			
				user.setAdmin(false);
				user.setAvatar(imageURL);
				user.setEmail(email);
				user.setCreatedTime(Calendar.getInstance().getTime());
				userRepos.save(user);
			return "ok";
		}
	}

	@Override
	public UserDTO findById(HttpServletRequest request) {
		String token=filter.getJwtFromRequest(request);
		String userName=jwt.getUserNameFromJWT(token);
		UserDTO dto=mapperDTO.mapperUserDTO(userRepos.findById(userName).orElse(null));
		return dto;
	}

	@Override
	public User getUser(String userName, String password) {
		return userRepos.user(userName, password);
	}

	@Override
	public List<User> findAll() {
		return userRepos.findAll();
	}

	@Override
	public void updateUser(String password,String userName) {
	 userRepos.updatePass(password, userName);
	 return;
	}
	
	
}
