package com.pan.vo;

import com.pan.dao.BoardDao;

public class PagingDto {
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	private int totalPage;
	private int perPage = 10;
	private int nowPage = 1;
	private final int PAGE_BLOCK = 10;

	public PagingDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PagingDto(int nowPage, SearchDto searchDto, int perPage) {
		this.nowPage = nowPage;
		this.perPage = perPage;
		
		BoardDao boardDao = BoardDao.getInstance();
		int count = boardDao.getCount(searchDto);
		totalPage = (int) Math.ceil((double) count / perPage);

		startRow = nowPage * perPage - (perPage - 1);
		endRow = nowPage * perPage;

		startPage = ((nowPage - 1) / PAGE_BLOCK) * PAGE_BLOCK + 1; // 진광
		endPage = startPage + (PAGE_BLOCK - 1);
		if (endPage > totalPage) {
			endPage = totalPage;
		}
	}

	public PagingDto(int startRow, int endRow) {
		super();
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	@Override
	public String toString() {
		return "PagingDto [startRow=" + startRow + ", endRow=" + endRow + ", startPage=" + startPage + ", endPage="
				+ endPage + ", totalPage=" + totalPage + ", perPage=" + perPage + ", nowPage=" + nowPage
				+ ", PAGE_BLOCK=" + PAGE_BLOCK + "]";
	}

}
