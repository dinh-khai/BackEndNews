package com.news.service.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.news.common.Constants;
import com.news.config.JwtFilter;
import com.news.config.JwtProvider;
import com.news.dto.req.UserDTOReq;
import com.news.dto.resp.UserDTOResp;
import com.news.entity.User;
import com.news.mapper.MapperDTO;
import com.news.mapper.MapperEntity;
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
	MapperEntity mapperEntity;
	@Autowired
	JwtProvider jwt;
	@Autowired
	JwtFilter filter;

	/**
	 * 
	 */
	@Override
	public String save(UserDTOReq dto,MultipartFile file,HttpServletRequest request) {
		try {
//			check username exist
			Optional<User>op= userRepos.findById(dto.getUserName());
			op.get();
			return "err";
		} catch (Exception e) {
				String imageURL=upload.upload(file, Constants.FOLDER_IMAGE_AVATAR, request);
				User user =mapperEntity.mapperUser(dto);
				user.setAvatar(imageURL);
				userRepos.save(user);
			return "ok";
		}
	}

//	get user from token
	@Override
	public UserDTOResp findById(HttpServletRequest request) {
		String token=filter.getJwtFromRequest(request);
		String userName=jwt.getUserNameFromJWT(token);
		UserDTOResp dto=mapperDTO.mapperUserDTO(userRepos.findById(userName).orElse(null));
		return dto;
	}	
	
}
