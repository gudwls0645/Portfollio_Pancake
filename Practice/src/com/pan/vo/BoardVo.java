package com.pan.vo;

import java.sql.Timestamp;

public class BoardVo {
	private int board_num;
	private String board_subject;
	private String board_content;
	private String board_writer;
	private int board_read_count;
	private int board_ref;
	private int board_re_step;
	private int board_re_level;
	private Timestamp board_reg_date;
	private int board_reply_count;

	public BoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardVo(int board_num, String board_subject, String board_content, String board_writer, int board_read_count,
			int board_ref, int board_re_step, int board_re_level, Timestamp board_reg_date, int board_reply_count) {
		super();
		this.board_num = board_num;
		this.board_subject = board_subject;
		this.board_content = board_content;
		this.board_writer = board_writer;
		this.board_read_count = board_read_count;
		this.board_ref = board_ref;
		this.board_re_step = board_re_step;
		this.board_re_level = board_re_level;
		this.board_reg_date = board_reg_date;
		this.board_reply_count = board_reply_count;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getBoard_subject() {
		return board_subject;
	}

	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public int getBoard_read_count() {
		return board_read_count;
	}

	public void setBoard_read_count(int board_read_count) {
		this.board_read_count = board_read_count;
	}

	public int getBoard_ref() {
		return board_ref;
	}

	public void setBoard_ref(int board_ref) {
		this.board_ref = board_ref;
	}

	public int getBoard_re_step() {
		return board_re_step;
	}

	public void setBoard_re_step(int board_re_step) {
		this.board_re_step = board_re_step;
	}

	public int getBoard_re_level() {
		return board_re_level;
	}

	public void setBoard_re_level(int board_re_level) {
		this.board_re_level = board_re_level;
	}

	public Timestamp getBoard_reg_date() {
		return board_reg_date;
	}

	public void setBoard_reg_date(Timestamp board_reg_date) {
		this.board_reg_date = board_reg_date;
	}

	public int getBoard_reply_count() {
		return board_reply_count;
	}

	public void setBoard_reply_count(int board_reply_count) {
		this.board_reply_count = board_reply_count;
	}

	@Override
	public String toString() {
		return "BoardVo [board_num=" + board_num + ", board_subject=" + board_subject + ", board_content="
				+ board_content + ", board_writer=" + board_writer + ", board_read_count=" + board_read_count
				+ ", board_ref=" + board_ref + ", board_re_step=" + board_re_step + ", board_re_level=" + board_re_level
				+ ", board_reg_date=" + board_reg_date + ", board_reply_count=" + board_reply_count + "]";
	}

}
