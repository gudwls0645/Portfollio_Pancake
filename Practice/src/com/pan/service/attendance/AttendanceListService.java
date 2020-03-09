package com.pan.service.attendance;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.pan.dao.AttendanceDao;
import com.pan.dao.BoardCommentDao;
import com.pan.service.IBoardService;
import com.pan.vo.AttendanceVo;
import com.pan.vo.BoardCommentVo;


public class AttendanceListService implements IBoardService {

	private AttendanceDao attDao = AttendanceDao.getInstance();
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		List<AttendanceVo> list = attDao.getAttendanceList();
		
		JSONArray jsonArray = new JSONArray(); // []
		
		for (AttendanceVo vo : list) {
			
			JSONObject jsonObject = new JSONObject(); // {}
			
			jsonObject.put("att_id", vo.getAtt_id());
			jsonObject.put("att_text", vo.getAtt_text());
			jsonObject.put("att_date", vo.getAtt_date().toString());
			
			jsonArray.add(jsonObject);
			
		}
		
		request.setAttribute("data", jsonArray.toJSONString());
		System.out.println(jsonArray);
		System.out.println("출석체크 불러오기");
		
		return "/WEB-INF/view/data.jsp";
	}
}
