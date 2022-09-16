package com.news.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.news.dto.create.CommentCreateDTO;
import com.news.dto.create.NewsCreateDTO;
import com.news.dto.create.ReCommentCreateDTO;
import com.news.dto.create.UserCreateDTO;
import com.news.entity.Comment;
import com.news.entity.News;
import com.news.entity.NewsCategory;
import com.news.entity.NewsClassification;
import com.news.entity.ReComment;
import com.news.entity.User;
import com.news.repos.CategoryRepos;
import com.news.repos.ClassifyRepos;
import com.news.repos.CommentRepos;
import com.news.repos.NewsRepos;
import com.news.repos.UserRepos;

@Component
public class MapperEntity {
	@Autowired
	UserRepos userRepos;
	@Autowired
	NewsRepos newsRepos;
	@Autowired
	CommentRepos cmtRepos;
	@Autowired
	ClassifyRepos classifyRepos;
	@Autowired
	CategoryRepos cateRepos;
	
	
//	mapper news create dto to news entity
	public News mapperNews(NewsCreateDTO dto) {
		News news =new News();
		NewsCategory category=cateRepos.findById(dto.getCategoryId()).orElse(null);
		NewsClassification classify=classifyRepos.findById(dto.getClassifyId()).orElse(null);
		news.setTitle(dto.getTitle());
		news.setDescription(dto.getDescription());
		news.setCategory(category);
		news.setClassify(classify);
		news.setFeatured(dto.isFeatured());
		news.setViews(0);
		news.setTime(dto.getCreatedTime());
		return news;
	}
	
//	mapper comment create dto to comment entity
	public Comment mapperComment(CommentCreateDTO dto) {
		Comment cmt = new Comment();
		User user=userRepos.findById(dto.getUserName()).orElse(null);
		News news =newsRepos.findById(dto.getNewsId()).orElse(null);
		cmt.setContent(dto.getDescription());
		cmt.setCreatedTime(dto.getCreatedTime());
		cmt.setUserCreator(user);
		cmt.setNews(news);
		return cmt;
	}
	
//	mapper re comment create to recomment entity
	public ReComment mapperReComment(ReCommentCreateDTO dto) {
		ReComment reComment=new ReComment();
		User user=userRepos.findById(dto.getUserName()).orElse(null);
		Comment comment=cmtRepos.findById(dto.getCmtId()).orElse(null);
		reComment.setContent(dto.getDescription());
		reComment.setCreatedTime(dto.getCreatedTime());
		reComment.setUserCreator(user);
		reComment.setComment(comment);
		return reComment;
	}
	
//	mapper user create dto to user entity
	public User mapperUser(UserCreateDTO dto) {
		User user =new User();
		user.setUserName(dto.getUserName());
		user.setPassword(dto.getPassword());
		user.setFullName(dto.getFullName());
		user.setEmail(dto.getEmail());
		user.setCreatedTime(dto.getCreatedTime());
		user.setAdmin(false);
		return user;
	}
	
}
