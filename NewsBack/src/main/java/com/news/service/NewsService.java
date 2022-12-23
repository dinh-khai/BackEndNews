package com.news.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.news.dto.NewsDTO;
import com.news.dto.PaginationDTO;
import com.news.dto.create.NewsCreateDTO;
import com.news.entity.Comment;
import com.news.entity.News;

public interface NewsService {

	/**
	 * find news by id
	 * 
	 * @param id
	 * @return NewsDto
	 */
	NewsDTO findById(long id);

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
	News saveNews(NewsCreateDTO dto, MultipartFile file, HttpServletRequest request);

	/**
	 * update news
	 * 
	 * @param id
	 * @param dto
	 * @param file
	 * @param request
	 */
	void updateNews(long id, NewsCreateDTO dto, MultipartFile file, HttpServletRequest request);

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

//	top 6 news by category
	List<News> listTop6NewsByCate(int id);

// 	top 4 news by classify
	List<News> listTop4NewsByClassify(int id);

//	most views
	List<News> mostViews();

//	update view
	void updateView(long id);

//	get news by category and pagination
	PaginationDTO getNewsByCategory(int page, int categoryId);
}
