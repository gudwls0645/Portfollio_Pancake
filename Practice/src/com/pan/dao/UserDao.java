package com.pan.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.pan.vo.UserVo;



public class UserDao {
	private static UserDao instance;

	private UserDao() {
	};

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
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
	
	// 회원가입
	public int userJoin(UserVo userVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into tbl_user (user_id, user_pw, user_name, user_email) " + 
					"values (?, ?, 'x', ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userVo.getUser_id());
			pstmt.setString(2, userVo.getUser_pw());
//			pstmt.setString(3, userVo.getUser_name());
			pstmt.setString(3, userVo.getUser_email());
			int count = pstmt.executeUpdate();
			
			String now = "2000-03-20 10:20:30.0"; // 형식을 지켜야 함

			Timestamp time = Timestamp.valueOf(now);

			String sql2 = "insert into tbl_attendance (att_id, att_text, att_date) "
					+ "values (?, '가입', ?)";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, userVo.getUser_id());
			pstmt2.setTimestamp(2, time);
			pstmt2.executeUpdate();
			
			conn.commit();
			return count;
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
		return 0;
	}
	
	// 로그인
	public String userLogin (UserVo userVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from tbl_user "
					+ "   where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userVo.getUser_id());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String user_pw = rs.getString("user_pw");
				if (user_pw.equals(userVo.getUser_pw())) {
					return "성공";
				} else {
					return "패스워드";
				}
			} else {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return "아이디";
	}
	
	// 로그인 한 사용자의 정보 얻기 
	public UserVo getUserInfo(UserVo userVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from tbl_user " + 
					"     where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userVo.getUser_id());
			
			rs = pstmt.executeQuery();
			UserVo getVo = new UserVo();
			if (rs.next()) {
				getVo.setUser_name(rs.getString("user_name"));
				getVo.setUser_point(rs.getInt("user_point"));
				getVo.setUser_email(rs.getString("user_email"));
			}
			
			return getVo;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		
		return null;
	}
}
