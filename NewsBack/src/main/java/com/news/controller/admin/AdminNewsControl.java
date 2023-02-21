package com.news.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.news.dto.req.NewsDTOReq;
import com.news.entity.News;
import com.news.exception.ErrMessage;
import com.news.exception.MyException;
import com.news.service.NewsService;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class AdminNewsControl {
	@Autowired
	NewsService newsService;
	
	/**
	 * save news
	 * 
	 * @param request
	 * @param dto
	 * @param file
	 * @return
	 */
	@PostMapping(value="/save",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> save(HttpServletRequest request,@RequestPart NewsDTOReq dto,@RequestPart MultipartFile file) {
		ErrMessage errMessage =new ErrMessage();
		boolean check=newsService.saveNews(dto, file, errMessage, request);
		if(!check) throw new MyException(errMessage);
		
		return new ResponseEntity<String>("Insert success",HttpStatus.OK);
	}

	/**
	 * update news
	 * 
	 * @param request
	 * @param dto
	 * @param file
	 * @param id
	 */
	@PutMapping(value="/update",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public void update(HttpServletRequest request,@RequestPart NewsDTOReq dto,@RequestPart MultipartFile file,@RequestPart long id) {
		newsService.updateNews(id, dto, file, request);
		return;
	}
	
	/**
	 * Delete news by id
	 * @param id
	 */
	@DeleteMapping("/delete")
	public void delete(@RequestParam long id) {
		newsService.deleteNews(id);
		return;
	}
}
