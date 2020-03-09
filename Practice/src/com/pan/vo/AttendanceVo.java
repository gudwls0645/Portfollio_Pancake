package com.pan.vo;

import java.sql.Timestamp;

public class AttendanceVo {
	private String att_id;
	private String att_text;
	private Timestamp att_date;

	public AttendanceVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AttendanceVo(String att_id, String att_text, Timestamp att_date) {
		super();
		this.att_id = att_id;
		this.att_text = att_text;
		this.att_date = att_date;
	}

	public String getAtt_id() {
		return att_id;
	}

	public void setAtt_id(String att_id) {
		this.att_id = att_id;
	}

	public String getAtt_text() {
		return att_text;
	}

	public void setAtt_text(String att_text) {
		this.att_text = att_text;
	}

	public Timestamp getAtt_date() {
		return att_date;
	}

	public void setAtt_date(Timestamp att_date) {
		this.att_date = att_date;
	}

	@Override
	public String toString() {
		return "AttendanceVo [att_id=" + att_id + ", att_text=" + att_text + ", att_date=" + att_date + "]";
	}

}
