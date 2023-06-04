package com.news.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.news.dto.req.NewsDTOReq;
import com.news.dto.resp.NewsDTOResp;
import com.news.dto.resp.PaginationDTOResp;

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
	List<NewsDTOResp> findAll();

	/**
	 * save news
	 * 
	 * @param dto
	 * @param file
	 * @param request
	 * @return news save
	 */
	void saveNews(NewsDTOReq dto, MultipartFile file);

	/**
	 * update news
	 * 
	 * @param id
	 * @param dto
	 * @param file
	 * @param request
	 */
	void updateNews(long id, NewsDTOReq dto, MultipartFile file);

	/**
	 * delete news
	 * 
	 * @param id
	 */
	void deleteNews(long id);
	
//	update view
	void updateView(long id);

	/**
	 * 
	 * @param page
	 * @param id
	 * @param limit
	 * @param sort
	 * @param sortBy
	 * @param field
	 * @return
	 */
	PaginationDTOResp pagination(int page, int id, int limit ,String sort ,String sortBy ,String field);
	
	PaginationDTOResp getListNews(int page, int limit, String sortType, String sortBy);
	
	/**
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	List<NewsDTOResp> getListNewsByFeatured(int page, int limit);
	
	/**
	 * search
	 * @param q
	 * @param page
	 * @param limit
	 * @return
	 */
	PaginationDTOResp search(String q, int page, int limit);
}
