package com.pan.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pan.service.IBoardService;

public class BoardWriteFormService implements IBoardService {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String mem_id = (String)session.getAttribute("user_id");
		if (mem_id != null && !mem_id.equals("")) {
			return "/WEB-INF/view/board/board_write.jsp";
		}
		request.setAttribute("msg", "notLogin");
		return "boardForm.pan";
		
		
	}

}
