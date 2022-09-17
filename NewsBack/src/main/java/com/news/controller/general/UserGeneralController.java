package com.news.controller.general;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.news.config.JwtProvider;
import com.news.dto.JwtResponse;
import com.news.dto.create.UserCreateDTO;
import com.news.service.UserService;

@RestController
@RequestMapping("/api/news/general")
public class UserGeneralController {
	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	JwtProvider jwt;
	
	
	@PostMapping("/login")
	public JwtResponse login(@RequestParam String userName,@RequestParam String password) {
		Authentication authentication =manager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails user=(UserDetails) authentication.getPrincipal();
		return new JwtResponse(jwt.createToken(user));
	}
	
	@PostMapping(value="/register",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String register(@RequestPart UserCreateDTO dto,@RequestPart MultipartFile file,HttpServletRequest request) {
		return userService.save(dto, file, request);
	}
	
	
}
