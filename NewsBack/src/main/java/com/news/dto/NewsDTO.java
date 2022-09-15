package com.news.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.news.entity.MoreDescription;
import com.news.entity.NewsCategory;
import com.news.entity.NewsClassification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private String description;
	private String image;
	private Date createdTime;
	private NewsCategory category;
	private NewsClassification classify;
	private int numberOfComment;
	private boolean featured;
	private int views;
	private Set<MoreDescription> list;
}
