package com.pan.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pan.dao.BoardDao;
import com.pan.service.IBoardService;

public class BoardDeleteProService implements IBoardService {

	private BoardDao boardDao = BoardDao.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		boardDao.delete(board_num);
		
		return "redirect:boardForm.pan";
	}

}
