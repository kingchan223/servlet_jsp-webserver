package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//
@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	@Override 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//입력으로 들어온 v, operator 값 받기.
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
	
		int v = 0 ;

		if (!v_.equals("")||v_!=null) v = Integer.parseInt(v_);
		
		///* 계산 *///
		if(op.equals("=")) {
//			int x = (Integer)application.getAttribute("value");
//			int x = (Integer)session.getAttribute("value");
			int x = 0;
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			}
			int y = v;
//			String operator = (String) application.getAttribute("op");
//			String operator = (String) session.getAttribute("op");
			
			String operator = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}
			}
			
			int result = 0;

			if(operator.equals("+"))
				result = x+y;
			else
				result = x-y;
			response.getWriter().printf("result:%d",result);
		}
		///* 저장 *///
		else {

//			application.setAttribute("value", v);
//			application.setAttribute("op", op);
//			
//			session.setAttribute("value", v);
//     		session.setAttribute("op", op);
//			
			//쿠키는 url로 쓸 수 있는 문자로만 보내야한다.
			//2개의 쿠키 지정.
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/calc2");
			valueCookie.setMaxAge(60*60);//쿠키 발급된 후로 60초*60*24 = 24시간 동안 쿠키 유효
			opCookie.setPath("/calc2");
			
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2.html");//백지를 보여주지 않고 clac2로 그대로 보기
		}
     }
}