package com.pan.service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pan.dao.BoardDao;
import com.pan.service.IBoardService;
import com.pan.vo.BoardVo;
import com.pan.vo.PagingDto;
import com.pan.vo.SearchDto;

public class BoardFormService implements IBoardService {

	private BoardDao boardDao = BoardDao.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int perPage = 10;
		String strPerPage = request.getParameter("perPage");
		if (strPerPage != null) {
			perPage = Integer.parseInt(strPerPage);
		}
		
//		System.out.println("perPage:" + perPage);
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		SearchDto searchDto = new SearchDto(searchType, keyword);
		
		int nowPage = 1;
		String strPage = request.getParameter("nowPage");
		if (strPage != null && !strPage.equals("")) {
			nowPage = Integer.parseInt(strPage);
		}
		
		
		PagingDto pagingDto = new PagingDto(nowPage, searchDto, perPage);
		List<BoardVo> list = boardDao.getArticles(pagingDto, searchDto);
		
		request.setAttribute("list", list);
		request.setAttribute("pagingDto", pagingDto);
		request.setAttribute("searchDto", searchDto);
		
		
		
		return "/WEB-INF/view/board/board_list.jsp";
	}

}
