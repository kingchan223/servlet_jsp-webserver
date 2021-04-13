package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/notice-reg")
public class NoticeReg extends HttpServlet{
	@Override // request는 입력도구, response는 출력도구
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");//사용자에게 (utf-8로 보낸다)보내는 인코딩 방식
		response.setContentType("text/html; charset=UTF-8");//브라우저야 이거 참조해서 봐
		//request.setCharacterEncoding("UTF-8");// 필터에다가 인코딩 설정을 했다@@@
		
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		out.println(title);
		out.println(content);
	}
}
