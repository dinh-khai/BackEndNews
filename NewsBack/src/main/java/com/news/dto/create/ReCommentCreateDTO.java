package com.news.dto.create;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("serial")
public class ReCommentCreateDTO implements Serializable{
	private String description;
	private Date createdTime;
	private String userName;
	private long cmtId;
}
