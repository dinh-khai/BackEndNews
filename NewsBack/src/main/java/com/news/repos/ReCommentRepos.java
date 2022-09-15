package com.news.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.news.entity.ReComment;

@Repository
public interface ReCommentRepos extends JpaRepository<ReComment, Long>{
	List<ReComment> findByCommentId(long id);
}
