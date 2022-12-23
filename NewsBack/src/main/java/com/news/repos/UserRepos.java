package com.news.repos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.news.entity.User;

@Repository
public interface UserRepos extends JpaRepository<User, String>{
	
}
