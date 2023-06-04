package com.news.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.service.UpLoadService;

@RestController
@RequestMapping("/api/image/")
public class ImageController {
    @Autowired
    UpLoadService uploadService;

    @GetMapping("{func}/{date}/{fileName}")
    public void getImage(@PathVariable String func, @PathVariable String date, @PathVariable String fileName, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(uploadService.getInputStream(func, date, fileName), response.getOutputStream());
    }
}
