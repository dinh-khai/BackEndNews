package com.news.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.news.dto.req.UserDTOReq;
import com.news.dto.resp.UserDTOResp;

public interface UserService {

	UserDTOResp findById(HttpServletRequest request);

	void save(String dto,MultipartFile file,HttpServletRequest request);
	
	UserDTOResp login(String username, String password);
}
