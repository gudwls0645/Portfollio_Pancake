package com.pan.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pan.dao.BoardDao;
import com.pan.service.IBoardService;
import com.pan.vo.BoardVo;

public class BoardWriteProService implements IBoardService {
	
	private BoardDao boardDao = BoardDao.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");
		
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoard_subject(subject);
		boardVo.setBoard_content(content);
		boardVo.setBoard_writer(user_id);
		
		boardDao.writeBoard(boardVo);
		
		return "redirect:boardForm.pan";
	}

}
