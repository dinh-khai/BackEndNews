package com.news.repos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.news.entity.User;

@Repository
public interface UserRepos extends JpaRepository<User, String>{
	@Query(value="Select * from User u where u.username=? and u.password=?;",nativeQuery = true)
	User user(String userName,String password);
	
	@Transactional
	@Modifying
	@Query(value="update User u set u.password=? where u.username=?",nativeQuery = true)
	void updatePass(String password,String userName);
}
