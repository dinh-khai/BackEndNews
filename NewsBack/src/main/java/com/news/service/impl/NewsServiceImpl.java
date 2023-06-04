package com.news.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.news.common.Constants;
import com.news.dto.req.NewsDTOReq;
import com.news.dto.resp.NewsDTOResp;
import com.news.dto.resp.PaginationDTOResp;
import com.news.entity.News;
import com.news.exception.MyException;
import com.news.mapper.MapperDTO;
import com.news.mapper.MapperEntity;
import com.news.repos.CategoryRepos;
import com.news.repos.ClassifyRepos;
import com.news.repos.NewsRepos;
import com.news.repos.UserRepos;
import com.news.service.NewsService;
import com.news.service.UpLoadService;

@Service
public class NewsServiceImpl implements NewsService{
	@Autowired
	NewsRepos newsRepos;
	
	@Autowired
	UserRepos userRepos;
	
	@Autowired
	UpLoadService upload;
	
	@Autowired
	CategoryRepos cateRepos;
	
	@Autowired
	ClassifyRepos classifyRepos;
	
	@Autowired
	MapperDTO mapper;
	
	@Autowired
	MapperEntity mapperEntity;
	
	@Autowired
	ModelMapper map;
	
	/**
	 * find news by id
	 * 
	 * @param long id
	 * @return NewsDTOResp
	 */
	@Override
	public NewsDTOResp findById(long id) {
		News news=newsRepos.findById(id).orElseThrow(
							()->new MyException(
									HttpStatus.NOT_FOUND,"User not found"
									)
						);
		NewsDTOResp dto=map.map(news, NewsDTOResp.class);
		return dto;
	}
	
	/**
	 * find all news
	 * 
	 * @return List<NewsDTOResp>
	 */
	@Override
	public List<NewsDTOResp> findAll() {
		List<News> listNews = newsRepos.findAll();
		return mapper.mapperNews(listNews);
	}

	/**
	 * save news
	 * 
	 * @param dto
	 * @param file
	 * @param request
	 * @exception SQLException
	 * @return
	 */
	@Transactional(rollbackFor = {SQLException.class})
	@Override
	public void saveNews(NewsDTOReq dto, MultipartFile file) {	
		News news = mapperEntity.mapperNews(dto, null);
		String imageURL ="";
		if(file != null && !file.isEmpty()) {
		    imageURL=upload.upload(file, Constants.FOLDER_IMAGE_NEWS);
		}
		news.setImage(imageURL);
		news.setViews(0);
		news.setUserInsert(dto.getUsername());
		news.setUserUpdate(dto.getUsername());
		news.setTimeInsert(new Date());
		news.setTimeUpdate(new Date());
		newsRepos.save(news);	
	}

	@Transactional(rollbackFor = {SQLException.class})
	@Override
	public void updateNews(long id, NewsDTOReq dto,MultipartFile file) {
		News news = newsRepos.findById(id).orElseThrow(()->new MyException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Không tìm thấy tin tức của id : %s", id)));
		String imageURL = "";
		if(news!=null) {
			if(file != null && !file.isEmpty()) {
	            imageURL=upload.upload(file, Constants.FOLDER_IMAGE_NEWS);
	        }
			news = mapperEntity.mapperNews(dto, news);
			news.setId(id);
			news.setImage(imageURL);
			news.setUserUpdate(dto.getUsername());
			news.setTimeUpdate(new Date());
		}
		newsRepos.save(news);
	}

	@Transactional(rollbackFor = {SQLException.class})
	@Override
	public void deleteNews(long id) {
		newsRepos.delete(newsRepos.findById(id).orElseThrow(
		        ()->new MyException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Không tìm thấy news của id : %s", id))));
		return;	
	}

	@Transactional(rollbackFor = {SQLException.class})
	@Override
	public void updateView(long id) {
		News news = newsRepos.findById(id).orElseThrow(()->new MyException(HttpStatus.NOT_FOUND, String.format("Không tìm thấy tin tức của id : %s", id)));
		news.setViews(news.getViews() + 1);
		newsRepos.save(news);
		return;	
	}

	@Override
	public PaginationDTOResp pagination(int page, int id, int limit,String sortType, String sortBy ,String field) {
	    Page<News> paging = null;
	    Sort sort = null;
	    if (sortBy == null) {
            sortBy = "id";
        }
	    if (sortType == null || sortType.equals("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else if (sortType.equals("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            throw new MyException(HttpStatus.INTERNAL_SERVER_ERROR, "Kiểu sort không chính xác");
        }
	    Pageable pageble = PageRequest.of(page, limit, sort);
		switch (field) {
        case "category":
            paging = newsRepos.findAllByCategoryId(id, pageble);
            break;
        case "classification":
            paging = newsRepos.findAllByClassifyId(id, pageble);
            break;
        default:
            break;
        }
		List<NewsDTOResp> list = mapper.mapperNews(paging.getContent());
		PaginationDTOResp dto = new PaginationDTOResp(paging.getTotalPages(),paging.getNumber(),list,paging.isFirst(),paging.isLast());
		return dto;
	}
	
	@Override
	public PaginationDTOResp getListNews(int page, int limit, String sortType, String sortBy) {
	    Page<News> paging = null;
        Sort sort = null;
        if (sortBy == null) {
            sortBy = "id";
        }
	    if (sortType == null || sortType.equals("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else if (sortType.equals("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            throw new MyException(HttpStatus.INTERNAL_SERVER_ERROR, "Kiểu sort không chính xác");
        }
	    Pageable pageble = PageRequest.of(page, limit, sort);
        paging = newsRepos.findAll(pageble);
	    List<NewsDTOResp> list = mapper.mapperNews(paging.getContent());
	    PaginationDTOResp dto = new PaginationDTOResp(paging.getTotalPages(),paging.getNumber(),list,paging.isFirst(),paging.isLast());
	    return dto;
	}
	
	@Override
	public List<NewsDTOResp> getListNewsByFeatured(int page, int limit) {
        Sort sort = Sort.by("id").descending();
        Pageable pageble = PageRequest.of(page, limit, sort);
        Page<News> paging = newsRepos.getNewsByFeatured(pageble);
        List<NewsDTOResp> list = mapper.mapperNews(paging.getContent());
        return list;
    }
	
	@Override
    public PaginationDTOResp search(String q, int page, int limit) {
        Page<News> paging = null;
        Sort sort = Sort.by("id").descending();
        Pageable pageble = PageRequest.of(page, limit, sort);
        paging = newsRepos.search(q, pageble);
        List<NewsDTOResp> list = mapper.mapperNews(paging.getContent());
        PaginationDTOResp dto = new PaginationDTOResp(paging.getTotalPages(),paging.getNumber(),list,paging.isFirst(),paging.isLast());
        return dto;
    }

}
