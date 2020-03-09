package com.pan.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pan.dao.BoardDao;
import com.pan.service.IBoardService;
import com.pan.vo.BoardVo;

public class BoardContentService implements IBoardService {

	private BoardDao boardDao = BoardDao.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		boardDao.readCount(board_num);
		
		BoardVo boardVo = boardDao.getContent(board_num);
		
		request.setAttribute("BoardVo", boardVo);
		
		return "/WEB-INF/view/board/board_content.jsp";
	}

}
