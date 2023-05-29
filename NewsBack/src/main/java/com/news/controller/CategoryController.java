package com.news.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.resp.CategoryDTOResp;
import com.news.service.CategoryService;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/categories/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ModelMapper mapper;
    
    @GetMapping("")
    public ResponseEntity<List<CategoryDTOResp>> getAll() {
        return new ResponseEntity<List<CategoryDTOResp>>(categoryService.findAll(),HttpStatus.OK);
    }
}
