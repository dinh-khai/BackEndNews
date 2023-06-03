package com.news.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.req.CommentDTOReq;
import com.news.dto.resp.ReCommentDTOResp;
import com.news.service.CommentService;
import com.news.service.ReCommentService;

@RestController
@RequestMapping("/api/comments/")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    CommentService cmtService;
    @Autowired
    ReCommentService reCmtService;
    
    @PostMapping
    public ResponseEntity<String> saveComment(@RequestBody CommentDTOReq dto) {
        cmtService.saveComment(dto);
        return new ResponseEntity<String>("", HttpStatus.CREATED);
    }
    
    @GetMapping("{id}/recomments")
    public ResponseEntity<List<ReCommentDTOResp>> getReCommentsByIdCmt(@PathVariable long id) {
        return new ResponseEntity<List<ReCommentDTOResp>>(reCmtService.getReCommentByComment(id), HttpStatus.OK);
    }
}
