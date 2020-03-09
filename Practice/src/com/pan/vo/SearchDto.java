package com.pan.vo;

public class SearchDto {
	private String searchType = "";
	private String keyword = "";

	public SearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchDto(String searchType, String keyword) {
		super();
		if (searchType != null) {
			this.searchType = searchType;
		}
		if (keyword != null) {
			this.keyword = keyword;
		}

	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		if (searchType != null) {
			this.searchType = searchType;
		}
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "SearchDto [searchType=" + searchType + ", keyword=" + keyword + "]";
	}

}
