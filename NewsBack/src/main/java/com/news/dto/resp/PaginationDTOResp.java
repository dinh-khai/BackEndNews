package com.news.dto.resp;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginationDTOResp implements Serializable{
	private int totalPages;
	private int currentPage;
	private List<?> list;
	private boolean isFirst;
	private boolean isLast;
	
}
