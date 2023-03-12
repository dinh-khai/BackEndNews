package com.news.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.news.dto.req.NewsDTOReq;
import com.news.dto.resp.NewsDTOResp;
import com.news.dto.resp.PaginationDTOResp;
import com.news.entity.Comment;
import com.news.entity.News;
import com.news.exception.Error;

public interface NewsService {

	/**
	 * find news by id
	 * 
	 * @param id
	 * @return NewsDto
	 */
	NewsDTOResp findById(long id);

	/**
	 * find all news
	 * 
	 * @return all news found
	 */
	List<News> findAll();

	/**
	 * find comments by news id
	 * 
	 * @param id
	 * 
	 * @return list comments found
	 */
	Set<Comment> listComment(long id);

	/**
	 * save news
	 * 
	 * @param dto
	 * @param file
	 * @param request
	 * @return news save
	 */
	void saveNews(NewsDTOReq dto, MultipartFile file, HttpServletRequest request);

	/**
	 * update news
	 * 
	 * @param id
	 * @param dto
	 * @param file
	 * @param request
	 */
	void updateNews(long id, NewsDTOReq dto, MultipartFile file, HttpServletRequest request);

	/**
	 * delete news
	 * 
	 * @param id
	 */
	void deleteNews(long id);

	/**
	 * get list most new featured
	 * 
	 * @return list news found
	 */
	List<News> mostFeatured();

	/**
	 * 
	 * @return
	 */
	List<News> mostNews();
	
	/**
	 * get latest news
	 * 
	 * @param num
	 * @return
	 */
	List<News> getLatestNews(int num);

//	top 6 news by category
	List<News> listTop6NewsByCate(int id);

// 	top 4 news by classify
	List<News> listTop4NewsByClassify(int id);

//	most views
	List<News> mostViews();

//	update view
	void updateView(long id);

//	get news by category and pagination
	PaginationDTOResp getNewsByCategory(int page, int categoryId);
}
