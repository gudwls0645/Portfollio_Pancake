package com.pan.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pan.dao.UserDao;
import com.pan.service.IBoardService;
import com.pan.vo.UserVo;

public class GetUserPointService implements IBoardService{

	private UserDao userDao = UserDao.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		UserVo userVo = new UserVo();
		userVo.setUser_id(user_id);
		
		UserVo vo = userDao.getUserInfo(userVo);
		
		int user_point = vo.getUser_point();
		request.setAttribute("data", user_point);
		
		return "/WEB-INF/view/data.jsp";
	}

}
