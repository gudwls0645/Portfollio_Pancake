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

import com.pan.vo.BoardVo;
import com.pan.vo.PagingDto;
import com.pan.vo.SearchDto;
import com.pan.vo.UserVo;




public class BoardDao {
	private static BoardDao instance;

	private BoardDao() {
	};

	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDao();
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
	
	// 게시글 불러오기
	public List<BoardVo> getBoardList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select * from tbl_board "
					+ "   order by board_num desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<BoardVo> list = new ArrayList<BoardVo>();
			while (rs.next()) {

				int board_num = rs.getInt("board_num");
				String board_subject = rs.getString("board_subject");
				String board_content = rs.getString("board_content");
				String board_writer = rs.getString("board_writer");
				int board_read_count = rs.getInt("board_read_count");
				int board_ref = rs.getInt("board_ref");
				int board_re_step = rs.getInt("board_re_step");
				int board_re_level = rs.getInt("board_re_level");
				Timestamp board_ref_date = rs.getTimestamp("board_reg_date");
				
				BoardVo boardVo = new BoardVo();
				boardVo.setBoard_num(board_num);
				boardVo.setBoard_subject(board_subject);
				boardVo.setBoard_content(board_content);
				boardVo.setBoard_writer(board_writer);
				boardVo.setBoard_read_count(board_read_count);
				boardVo.setBoard_ref(board_ref);
				boardVo.setBoard_re_step(board_re_step);
				boardVo.setBoard_re_level(board_re_level);
				boardVo.setBoard_reg_date(board_ref_date);
				
				list.add(boardVo);
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
	
	
	// 게시글 쓰기
	public void writeBoard(BoardVo boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "insert into tbl_board (board_num, board_subject, board_content, board_writer, board_read_count, " + 
					"                        board_ref, board_re_step, board_re_level, board_reg_date) " + 
					"values (seq_board_num.nextval, ?, ?, ?, 0, " + 
					"                0, 0, 0, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_subject());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setString(3, boardVo.getBoard_writer());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally { 
			closeAll(conn, pstmt, null);
		}
	}
	
	// 상세보기
	public BoardVo getContent(int board_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select * from tbl_board "
					+ "   where board_num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {

					String board_subject = rs.getString("board_subject");
					String board_content = rs.getString("board_content");
					String board_writer = rs.getString("board_writer");
					int board_read_count = rs.getInt("board_read_count");
					int board_ref = rs.getInt("board_ref");
					int board_re_step = rs.getInt("board_re_step");
					int board_re_level = rs.getInt("board_re_level");
					Timestamp board_reg_date = rs.getTimestamp("board_reg_date");

					BoardVo boardVo = new BoardVo();
					boardVo.setBoard_num(board_num);
					boardVo.setBoard_subject(board_subject);
					boardVo.setBoard_content(board_content);
					boardVo.setBoard_writer(board_writer);
					boardVo.setBoard_read_count(board_read_count);
					boardVo.setBoard_ref(board_ref);
					boardVo.setBoard_re_step(board_re_step);
					boardVo.setBoard_re_level(board_re_level);
					boardVo.setBoard_reg_date(board_reg_date);
					
					return boardVo;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		
		return null;
	}
	
	// 조회수
	public void readCount(int board_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "update tbl_board set " + 
					"board_read_count = board_read_count + 1 " + 
					"where board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}
	
	// 삭제
	public boolean delete(int board_num)  {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "delete from tbl_board "
					+ "   where board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
		return false;
	}
	
	// 수정하기
	public boolean update(BoardVo boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "update tbl_board set " + 
					"    board_subject = ?, " + 
					"    board_content = ? " +
				    "where board_num = ? and board_writer = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_subject());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3, boardVo.getBoard_num());
			pstmt.setString(4, boardVo.getBoard_writer());
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
		return false;
	}
	
	// 글갯수
	public int getCount(SearchDto searchDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select count(*) from tbl_board";
			if (!searchDto.getSearchType().equals("") && 
					!searchDto.getKeyword().equals("")) {
				sql += "   where " + searchDto.getSearchType() +
						" like '%" + searchDto.getKeyword() + "%'";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt(1);
				return cnt;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return 0;
	}
	
	// 글목록
	public List<BoardVo> getArticles(PagingDto pagingDto, SearchDto searchDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select * from " + 
					"(select rownum rnum, a.* from " + 
					"(select * from tbl_board "; 
			if(!searchDto.getSearchType().equals("") && 
					!searchDto.getKeyword().equals("")) {
				sql += " where " + searchDto.getSearchType() +
						" like '%" + searchDto.getKeyword() + "%'";
			}
			sql +=	"order by board_num desc, board_ref desc, board_re_step asc) a) " + 
					"where rnum >= ? and rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pagingDto.getStartRow());
			pstmt.setInt(2, pagingDto.getEndRow());
			
			rs = pstmt.executeQuery();
			// java.util.List - interface
			List<BoardVo> list = new ArrayList<>();
			while (rs.next()) {
				int num = rs.getInt("board_num");
				String subject = rs.getString("board_subject");
				String writer = rs.getString("board_writer");
				Timestamp reg_date = rs.getTimestamp("board_reg_date");
				int readcount = rs.getInt("board_read_count");
				int ref = rs.getInt("board_ref");
				int re_step = rs.getInt("board_re_step");
				int re_level = rs.getInt("board_re_level");
				int board_reply_count = rs.getInt("board_reply_count");
				
				BoardVo vo = new BoardVo();
				vo.setBoard_num(num);
				vo.setBoard_subject(subject);
				vo.setBoard_writer(writer);
				vo.setBoard_reg_date(reg_date);
				vo.setBoard_read_count(readcount);
				vo.setBoard_ref(ref);
				vo.setBoard_re_step(re_step);
				vo.setBoard_re_level(re_level);
				vo.setBoard_reply_count(board_reply_count);
				
				list.add(vo);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	}
}
