package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hello")
public class Nana extends HttpServlet{
	@Override // request는 입력도구, response는 출력도구
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");//사용자에게 (utf-8로 보낸다)보내는 인코딩 방식
		response.setContentType("text/htmlk charset=UTF-8");//브라우저야 이거 참조해서 봐
		
		PrintWriter out = response.getWriter();
		
		//getParameter는 쿼리스트링을 읽어온다. 
		//클라이언트에서 cnt쿼리스트링을 보내면 서버에서 그걸 받기 위해 request.getParameter("cnt")
		String cnt_ = request.getParameter("cnt");
		
		int cnt = 100;//사용자가 쿼리를 제대로 날리지 못했다면 100번 출력.
		if( cnt_!=null && !cnt_.equals(""))//사용자가 쿼리를 제대로 날렸다면 날린만큼 출력.
			cnt = Integer.parseInt(cnt_);

		for(int i=0; i<cnt; i++)
			out.println("안녕o~!!<br>");
	}
}
