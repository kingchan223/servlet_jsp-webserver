package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/add")
public class Add extends HttpServlet{
	@Override // request는 입력도구, response는 출력도구
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");


		
		String left = request.getParameter("left");
		String right = request.getParameter("right");
		
		int a = 0 ;
		int b = 0 ;
		if (!left.equals("")) a =  Integer.parseInt(left);
		if (!right.equals("")) b =  Integer.parseInt(right);
		int sum = a+b;

		response.getWriter().printf("result:%d",sum);
	}
}