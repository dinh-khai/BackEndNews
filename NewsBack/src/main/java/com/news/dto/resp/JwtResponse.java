package com.news.dto.resp;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("serial")
public class JwtResponse implements Serializable{
	private String accessToken;
	
}
