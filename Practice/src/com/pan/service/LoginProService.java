package com.pan.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pan.dao.UserDao;
import com.pan.vo.UserVo;

public class LoginProService implements IBoardService {
	
	private UserDao userDao = UserDao.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String user_id = request.getParameter("id");
		
		UserVo userVo = new UserVo();
		userVo.setUser_id(user_id);
		userVo.setUser_pw(request.getParameter("pw"));
		
		String check = userDao.userLogin(userVo);
		
		String msg = "";
		if (check.equals("성공")) {
//			System.out.println("로그인 성공");
			userVo = userDao.getUserInfo(userVo);
			
			String getName = userVo.getUser_name();
			int getPoint = userVo.getUser_point();
			String getEmail = userVo.getUser_email();
			
			session.setAttribute("user_id", user_id);
			session.setAttribute("user_name", getName);
			session.setAttribute("user_point", getPoint);
			session.setAttribute("user_email", getEmail);
			msg = "o";
		} else if (check.equals("패스워드")){
//			System.out.println("패스워드");
			msg = "pw";
		} else if (check.equals("아이디")) {
//			System.out.println("아이디");
			msg = "id";
		}
		request.setAttribute("msg", msg);
		
		return "loginForm.pan";
	}

}
