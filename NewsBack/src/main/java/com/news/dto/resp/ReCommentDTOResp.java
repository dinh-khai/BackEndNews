package com.news.dto.resp;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("serial")
public class ReCommentDTOResp implements Serializable{
	private long id;
	private String description;
	private UserDTOResp user;
	private CommentDTOResp comment;
}
