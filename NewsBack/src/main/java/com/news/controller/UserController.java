package com.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.news.dto.req.UserDTOReq;
import com.news.dto.resp.UserDTOResp;
import com.news.service.UserService;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;
    
    @PostMapping("login")
    public ResponseEntity<UserDTOResp> login(@RequestParam String username, @RequestParam String password) {
        UserDTOResp dto = userService.login(username, password);
        return new ResponseEntity<UserDTOResp>(dto, HttpStatus.ACCEPTED);
    }
    
    @PostMapping(value = "",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> register(@RequestParam String dto, @RequestPart(required = false) MultipartFile file) {
        userService.save(dto, file);
        return new ResponseEntity<String>("Đăng ký tài khoản thành công", HttpStatus.OK);
    }
}
