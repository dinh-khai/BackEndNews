package com.news.mapper;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.news.dto.req.CommentDTOReq;
import com.news.dto.req.NewsDTOReq;
import com.news.dto.req.ReCommentDTOReq;
import com.news.dto.req.UserDTOReq;
import com.news.entity.Comment;
import com.news.entity.News;
import com.news.entity.NewsCategory;
import com.news.entity.NewsClassification;
import com.news.entity.ReComment;
import com.news.entity.User;
import com.news.exception.MyException;
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
	
	
	/**
	 * mapper news create dto to news entity
	 * @param dto
	 * @return
	 */
	public News mapperNews(NewsDTOReq dto) {
		News news =new News();
		NewsCategory category=cateRepos.findById(dto.getCategoryId()).orElse(null);
		NewsClassification classify=classifyRepos.findById(dto.getClassifyId()).orElse(null);
		news.setTitle(dto.getTitle());
		news.setShortDescription(dto.getShortDescription());
		news.setDescription(dto.getDescription());
		news.setCategory(category);
		news.setClassify(classify);
		news.setFeatured(dto.isFeatured());
		news.setViews(0);
		news.setTimeInsert(new Date());
		return news;
	}
	
	/**
	 * mapper comment create dto to comment entity
	 * @param dto
	 * @return
	 */
	public Comment mapperComment(CommentDTOReq dto) {
		Comment cmt = new Comment();
		User user=userRepos.findById(dto.getUsername()).orElse(null);
		News news =newsRepos.findById(dto.getNewsId()).orElse(null);
		cmt.setContent(dto.getDescription());
		cmt.setCreatedTime(dto.getCreatedTime());
		cmt.setUserCreator(user);
		cmt.setNews(news);
		return cmt;
	}
	
	public ReComment mapperReComment(ReCommentDTOReq dto) {
        ReComment cmt = new ReComment();
        User user = userRepos.findById(dto.getUsername()).orElseThrow(()->new MyException(HttpStatus.NOT_FOUND, "Không tìm thấy username"));
        Comment comment = cmtRepos.findById(dto.getCmtId()).orElseThrow(()->new MyException(HttpStatus.NOT_FOUND, "Không tìm thấy tin tức"));
        cmt.setContent(dto.getDescription());
        cmt.setCreatedTime(new Date());
        cmt.setUserCreator(user);
        cmt.setComment(comment);
        return cmt;
    }
	
}
