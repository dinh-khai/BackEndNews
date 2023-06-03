package com.news.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.common.Constants;
import com.news.config.JwtFilter;
import com.news.config.JwtProvider;
import com.news.dto.req.UserDTOReq;
import com.news.dto.resp.UserDTOResp;
import com.news.entity.Role;
import com.news.entity.User;
import com.news.exception.MyException;
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
	ModelMapper mapper;
	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	JwtFilter filter;
	@Autowired
	AuthenticationManager auth;
	
	@Autowired
	HttpServletRequest req;
	
	@Autowired
	ObjectMapper objMapper;
	@Autowired
	PasswordEncoder encode;

	/**
	 * 
	 */
	@Override
	@Transactional
	public void save(String dto,MultipartFile file) {
		User user = new User();
        try {
            UserDTOReq userDto = objMapper.readValue(dto, UserDTOReq.class);
            boolean validate = validateUser(userDto);
            if (!validate) {
                throw new MyException(HttpStatus.INTERNAL_SERVER_ERROR, "Username đã được đăng ký");
            }
            
            user = mapper.map(userDto, User.class);
            String img = upload.upload(file, Constants.FOLDER_IMAGE_AVATAR);
            user.setAvatar(img);
        } catch (JsonMappingException e) {
            throw new MyException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (JsonProcessingException e) {
            throw new MyException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
		Role role = new Role();
		role.setId(2);
		Set<Role> listRoles = new HashSet<>();
		listRoles.add(role);
		user.setCreatedTime(new Date());
		user.setRoles(listRoles);
		user.setPassword(encode.encode(user.getPassword()));
		userRepos.save(user);
	}

	@Override
	public UserDTOResp findById(HttpServletRequest request) {
		String token = filter.getJwtFromRequest(request);
		String userName = jwtProvider.getUserNameFromJWT(token);
		UserDTOResp dto = mapperDTO.mapperUserDTO(userRepos.findById(userName).orElse(null));
		return dto;
	}

    @Override
    public UserDTOResp login(String username, String password) {
        Authentication authentication = auth.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        String jwt = jwtProvider.createToken(userDetail);
        User user = userRepos.findById(username).orElse(null);
        UserDTOResp dto = mapper.map(user, UserDTOResp.class);
        dto.setToken(jwt);
        return dto;
    }	
	
	private boolean validateUser(UserDTOReq dto) {
	    User user = userRepos.findById(dto.getUsername()).orElse(null);
	    if (user != null) {
            return false;
        }
	    return true;
	}
}
