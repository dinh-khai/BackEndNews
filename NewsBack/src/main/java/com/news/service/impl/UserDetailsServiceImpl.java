package com.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.news.entity.Role;
import com.news.entity.User;
import com.news.repos.UserRepos;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserRepos userRepos;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepos.findById(username).orElse(null);
		if(user==null) {
			throw new UsernameNotFoundException("User not exist");
		}
		
		List<GrantedAuthority> list=new ArrayList<>();
		for(Role role:user.getRoles()) {
			list.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), list);
	}

}
