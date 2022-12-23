package com.news.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.news.dto.UserDTO;
import com.news.dto.create.UserCreateDTO;

public interface UserService {

	UserDTO findById(HttpServletRequest request);

	String save(UserCreateDTO dto,MultipartFile file,HttpServletRequest request);
}
