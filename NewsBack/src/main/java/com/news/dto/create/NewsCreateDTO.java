package com.news.dto.create;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsCreateDTO implements Serializable{
	private String title;
	private String description;
	private int categoryId;
	private int classifyId;
	private boolean featured;
	private Date createdTime;
}
