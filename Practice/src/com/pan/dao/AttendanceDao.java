package com.pan.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.tomcat.util.http.ResponseUtil;

import com.pan.vo.AttendanceVo;
import com.pan.vo.BoardCommentVo;
import com.pan.vo.BoardVo;


public class AttendanceDao {
	private static AttendanceDao instance;

	private AttendanceDao() {
	};

	public static AttendanceDao getInstance() {
		if (instance == null) {
			instance = new AttendanceDao();
		}
		return instance;
	}

	private Connection getConnection() {
		try {
			// javax.naming.Context
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/oraclexe");
			// -> 커넥션 풀 참조 얻기
			Connection conn = ds.getConnection();
			// -> 커넥션 풀에서 이미 생성된 커넥션 객체 얻기
			return conn;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;

	}

	private void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (conn != null)
			try {
				conn.close();
			} catch (Exception e) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
			}
	}
	
	// 출석체크
	public boolean insertAttendance(AttendanceVo attVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {
			
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into tbl_attendance "
					+ "   (att_id, att_text, att_date) "
					+ "   values (?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attVo.getAtt_id());
			pstmt.setString(2, attVo.getAtt_text());
			pstmt.executeUpdate();
			
			String sql2 = "update tbl_user set "
					+ "user_point = user_point + ? "
					+ "where user_id = ?";
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, 1000);
			pstmt2.setString(2, attVo.getAtt_id());
			pstmt2.executeUpdate();
			
			conn.commit();
			return true;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeAll(conn, pstmt, null);
			closeAll(null, pstmt2, null);
		}
		return false;
	}
	
	// 출석체크 불러오기
	public List<AttendanceVo> getAttendanceList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select * from tbl_attendance "
					+ "order by att_date desc ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<AttendanceVo> list = new ArrayList<AttendanceVo>();
			while (rs.next()) {
				
				// 오늘 날짜 불러오기 상단
				SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd"); 
				Date time = new Date();
				String today = format1.format(time);
				// 오늘 날짜 불러오기 하단
				Timestamp att_date = rs.getTimestamp("att_date");
				String getdate = att_date.toString();
				String getsubdate = getdate.substring(0, "0000-00-00".length());
				
				if (today.equals(getsubdate)) {
					String att_id = rs.getString("att_id");
					String att_text = rs.getString("att_text");
					
					AttendanceVo attVo = new AttendanceVo();
					attVo.setAtt_id(att_id);
					attVo.setAtt_text(att_text);
					attVo.setAtt_date(att_date);
					
					list.add(attVo);
				}
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	}
	
	// 출석했는지 체크를 위한 날짜불러오기
	public String getAttendanceCheck(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from tbl_attendance "
					+ "where att_id = ? "
					+ "order by att_date desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String time = rs.getTimestamp("att_date").toString();
				return time;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		
		return null;
	}
}
