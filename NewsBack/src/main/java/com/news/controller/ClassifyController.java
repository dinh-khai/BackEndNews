package com.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.resp.PaginationDTOResp;
import com.news.service.NewsService;

@RestController
@RequestMapping("/api/classifications/")
@CrossOrigin("*")
public class ClassifyController {
    @Autowired
    NewsService newsService;
    
    @GetMapping("{id}/news")
    public ResponseEntity<PaginationDTOResp> getNewsByCategory(@PathVariable int id
                                                              ,@RequestParam int page
                                                              ,@RequestParam int limit
                                                              ,@RequestParam(required = false) String sortType
                                                              ,@RequestParam(required = false) String sortBy) {
        PaginationDTOResp dto = newsService.pagination(page, id, limit, sortType, sortBy, "classification");
        return new ResponseEntity<PaginationDTOResp>(dto,HttpStatus.OK);
    }
}
