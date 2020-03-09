package com.pan.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pan.dao.BoardDao;
import com.pan.service.IBoardService;
import com.pan.vo.BoardVo;

public class BoardUpdateProService implements IBoardService {

	private BoardDao boardDao = BoardDao.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		BoardVo boardVo = new BoardVo();
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		boardVo.setBoard_writer(user_id);
		boardVo.setBoard_subject(request.getParameter("subject"));
		boardVo.setBoard_content(request.getParameter("content"));
		boardVo.setBoard_num(board_num);
		
		boardDao.update(boardVo);
		
		return "redirect:boardForm.pan";
	}

}
