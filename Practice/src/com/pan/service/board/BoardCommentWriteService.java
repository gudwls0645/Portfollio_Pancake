package com.pan.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.pan.dao.BoardCommentDao;
import com.pan.service.IBoardService;
import com.pan.vo.BoardCommentVo;


public class BoardCommentWriteService implements IBoardService {

	private BoardCommentDao commentDao = BoardCommentDao.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String reply_text = request.getParameter("reply_text");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		BoardCommentVo commentVo = new BoardCommentVo();
		commentVo.setBoard_num(board_num);
		commentVo.setReply_text(reply_text);
		commentVo.setReplyer(user_id);
		boolean result = commentDao.insertComment(commentVo);
		
		
		
		String data = "fail";
		
		
		if (result == true) {
			data = "success";
		}
		
		
		request.setAttribute("data", data);
		return "/WEB-INF/view/data.jsp";
	}

}
