package com.news.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.news.common.Constants;
import com.news.dto.req.NewsDTOReq;
import com.news.dto.resp.NewsDTOResp;
import com.news.dto.resp.PaginationDTOResp;
import com.news.entity.Comment;
import com.news.entity.News;
import com.news.exception.MyException;
import com.news.exception.customException.NotFoundException;
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
	
	/**
	 * find news by id
	 * 
	 * @param long id
	 * @return NewsDTOResp
	 */
	@Override
	public NewsDTOResp findById(long id) {
		News news=newsRepos.findById(id).orElseThrow(
							()->new NotFoundException(
									HttpStatus.NOT_FOUND,"User not found"
									)
						);
		NewsDTOResp dto=mapper.mapperNewsDTO(news);
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
	public void saveNews(NewsDTOReq dto, MultipartFile file,HttpServletRequest request) {	
		News news=mapperEntity.mapperNews(dto);
		String imageURL ="";
		if(file != null && !file.isEmpty()) {
		    imageURL=upload.upload(file, Constants.FOLDER_IMAGE_NEWS);
		}
		news.setImage(imageURL);
		news.setUserInsert(Constants.USER_NAME_LOGIN);
		news.setUserUpdate(Constants.USER_NAME_LOGIN);
		newsRepos.save(news);	
	}

	@Override
	public void updateNews(long id, NewsDTOReq dto,MultipartFile file,HttpServletRequest request) {
//		News news = newsRepos.findById(id).orElse(null);
//		if(news!=null) {
//			String imageURL=upload.upload(file, Constants.FOLDER_IMAGE_NEWS, request);
//			news=mapperEntity.mapperNews(dto);
//			news.setImage(imageURL);
//		}
//		newsRepos.save(news);
	}

	@Override
	public void deleteNews(long id) {
		newsRepos.delete(newsRepos.findById(id).orElse(null));
		return;	
	}

	@Override
	public Set<Comment> listComment(long id) {
		return newsRepos.findById(id).orElse(null).getListComments();
	}

	@Override
	public List<News> mostFeatured() {		
		return newsRepos.mostFeatured();
	}

	@Override
	public List<News> mostNews() {
		return newsRepos.listNewNews();
	}
	
	/**
	 * get latest news
	 * 
	 * @param num
	 * @return list news latest
	 */
	@Override
	public List<News> getLatestNews(int num) {
			
		return newsRepos.getListLatestNews(num);
	}

	@Override
	public List<News> listTop6NewsByCate(int id) {
		return newsRepos.listTop6NewsByCate(id);
	}

	@Override
	public List<News> listTop4NewsByClassify(int id) {
		return newsRepos.listTop4NewsByClassify(id);
	}

	@Override
	public List<News> mostViews() {
		return newsRepos.mostViews();
	}

	@Override
	public void updateView(long id) {
		newsRepos.updateView(id);
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
	public List<NewsDTOResp> getListNews(int page, int limit, String sortType, String sortBy) {
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
	    return list;
	}
	
	@Override
	public List<NewsDTOResp> getListNewsByFeatured(int page, int limit) {
        Page<News> paging = null;
        Sort sort = Sort.by("id").descending();
        Pageable pageble = PageRequest.of(page, limit, sort);
        paging = newsRepos.findAll(pageble);
        List<NewsDTOResp> list = mapper.mapperNews(paging.getContent());
        return list;
    }

}
