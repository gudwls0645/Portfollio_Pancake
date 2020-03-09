package com.pan.service.attendance;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pan.dao.AttendanceDao;
import com.pan.dao.BoardCommentDao;
import com.pan.service.IBoardService;
import com.pan.vo.AttendanceVo;
import com.pan.vo.BoardCommentVo;

public class AttendanceWriteService implements IBoardService {

	private AttendanceDao attendanceDao = AttendanceDao.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		String reply_text = request.getParameter("reply_text");
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		String getDate = attendanceDao.getAttendanceCheck(user_id);
		String subTime = getDate.substring(0, "0000-00-00".length());
		
		// 오늘 날짜 불러오기 상단
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd"); 
		Date time = new Date();
		String time1 = format1.format(time);
		// 오늘 날짜 불러오기 하단
		
		System.out.println(subTime);
		System.out.println(time1);
		String data = "fail";
		if (subTime.equals(time1)) {
			System.out.println("날짜가 같음");
			request.setAttribute("data", data);
			return "/WEB-INF/view/data.jsp";
		} else {
			System.out.println("날짜가 다름");
		}
		
		AttendanceVo attVo = new AttendanceVo();
		attVo.setAtt_text(reply_text);
		attVo.setAtt_id(user_id);
		boolean result = attendanceDao.insertAttendance(attVo);
		
		if (result == true) {
			data = "success";
		}
		
		request.setAttribute("data", data);
		
		return "/WEB-INF/view/data.jsp";
	}

}
