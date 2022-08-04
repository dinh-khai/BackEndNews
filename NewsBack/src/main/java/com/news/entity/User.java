package com.news.entity;

import java.io.Serializable;
//import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="username")
	private String userName;
	
	@Column(name="avatar")
	private String avatar;
	
	@Column(name="fullname")
	private String fullName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="createdtime")
	private Date createdTime;
	
	@Column(name="isadmin")
	private boolean isAdmin;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userCreator",fetch = FetchType.LAZY)
	private Set<Comment> listComments;
}
