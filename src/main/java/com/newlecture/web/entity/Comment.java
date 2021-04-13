package com.newlecture.web.entity;

import java.util.Date;

public class Comment {
	private int id;
	private String content;
	private Date regdate;
	private String writer_id;
	private int notice_id;
	
	public Comment() {}
	public Comment(int id, String content, Date regdate, String writer_id, int notice_id) {
		this.id = id;
		this.content = content;
		this.regdate = regdate;
		this.writer_id = writer_id;
		this.notice_id = notice_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	
	
}
