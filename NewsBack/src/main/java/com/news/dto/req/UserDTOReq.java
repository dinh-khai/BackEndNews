package com.news.dto.req;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("serial")
public class UserDTOReq implements Serializable{
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private Date createdTime;
}
