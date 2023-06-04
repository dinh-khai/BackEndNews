package com.news.dto.resp;

import java.io.Serializable;
import java.util.Date;
import com.news.entity.NewsCategory;
import com.news.entity.NewsClassification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsDTOResp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private String shortDescription;
	private String description;
	private String image;
	private Date timeInsert;
	private Date tineUpdate;
	private NewsCategory category;
	private NewsClassification classify;
	private int numberOfComment;
	private boolean featured;
	private int views;
	private String userInsert;
	private String userUpdate;
}
