package com.news.controller.general;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.news.config.JwtProvider;
import com.news.dto.JwtResponse;
import com.news.entity.Role;
import com.news.repos.UserRepos;

@RestController
@RequestMapping("/api/news/general")
public class UserGeneralController {
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
	
	@Autowired
	UserRepos repos;
	@GetMapping("/info")
	public Set<Role> geRole(@RequestParam String user){
		return repos.findById(user).orElse(null).getRoles();
	}
	
}
