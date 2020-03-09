package com.pan.service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.pan.dao.BoardCommentDao;
import com.pan.service.IBoardService;
import com.pan.vo.BoardCommentVo;


public class BoardCommentListService implements IBoardService {

	private BoardCommentDao commentDao = BoardCommentDao.getInstance();
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		List<BoardCommentVo> list = commentDao.getCommentList(board_num);
		
		JSONArray jsonArray = new JSONArray(); // []
		for (BoardCommentVo vo : list) {
			JSONObject jsonObject = new JSONObject(); // {}
			// [{
			//      "reply_num" : 1,
			//      "board_num" : 84
			// }]
			jsonObject.put("reply_num", vo.getReply_num());
			jsonObject.put("board_num", board_num);
			jsonObject.put("replyer", vo.getReplyer());
			jsonObject.put("reply_date", vo.getReply_date().toString());
			jsonObject.put("reply_text", vo.getReply_text());
			jsonArray.add(jsonObject);
			
		}
		
		request.setAttribute("data", jsonArray.toJSONString());
		
		return "/WEB-INF/view/data.jsp";
	}

}
