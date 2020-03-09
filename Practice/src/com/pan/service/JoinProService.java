package com.pan.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pan.dao.UserDao;
import com.pan.vo.UserVo;

public class JoinProService implements IBoardService {

	private UserDao userDao = UserDao.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		UserVo userVo = new UserVo();
		
		userVo.setUser_id(request.getParameter("id"));
		userVo.setUser_pw(request.getParameter("pw"));
		userVo.setUser_name(request.getParameter("name"));
		userVo.setUser_email(request.getParameter("email"));
		
		int count = userDao.userJoin(userVo);
		
		if(count > 0) {
			System.out.println("성공");
			request.setAttribute("msg", "o");
		} else {
			System.out.println("실패");
			request.setAttribute("msg", "x");
		}
		
		return "joinForm.pan";
	}

}
