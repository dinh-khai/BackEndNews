package com.news;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.news.entity.Role;
import com.news.entity.User;
import com.news.repos.UserRepos;
import com.news.service.UserService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class NewsBackApplication {
	public static void main(String[] args) {
		SpringApplication.run(NewsBackApplication.class, args);
	}
	
	@Autowired
	UserRepos userRepos;
	@Autowired
	PasswordEncoder encode;
	
	@PostConstruct
	public void saveAdmin() {
	    String username = "admin";
	    User user = userRepos.findById(username).orElse(null);
	    if (user != null) {
            return;
        }
	    Role roleUser = new Role();
	    roleUser.setId(2);
	    roleUser.setName("USER");
	    Role roleAdmin = new Role();
	    roleAdmin.setId(1);
	    roleAdmin.setName("ADMIN");
	    Set<Role> listRoles = new LinkedHashSet<>();
	    listRoles.add(roleAdmin);
	    listRoles.add(roleUser);
	    user = new User();
	    user.setUsername(username);
	    user.setPassword(encode.encode("123"));
	    user.setFullName("Administrator");
	    user.setCreatedTime(new Date());
	    user.setRoles(listRoles);
	    user.setAdmin(true);
	    userRepos.save(user);
	    
	}
}
