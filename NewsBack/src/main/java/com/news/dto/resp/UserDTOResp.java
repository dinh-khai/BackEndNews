		package com.news.dto.resp;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDTOResp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String fullName;
	private String email;
	private String avatar;
	private boolean isAdmin;
	private Date createdTime;
	private Set<RoleDTOResp> roles;
}
