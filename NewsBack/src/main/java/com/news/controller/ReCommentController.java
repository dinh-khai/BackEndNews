package com.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.req.ReCommentDTOReq;
import com.news.service.ReCommentService;

@RestController
@RequestMapping("/api/recomments/")
@CrossOrigin("*")
public class ReCommentController {
    @Autowired
    ReCommentService reCmtService;
    
    @PostMapping
    public ResponseEntity<String> saveReComment(@RequestBody ReCommentDTOReq dto) {
        reCmtService.saveReComemnt(dto);
        return new ResponseEntity<String>("", HttpStatus.CREATED);
    }
}
