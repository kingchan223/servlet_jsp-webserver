package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/add2")
public class Add2 extends HttpServlet{
	@Override // request는 입력도구, response는 출력도구
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//요청을 배열로 받기 클라이언트에서 같은 name을 써도 된다.
		String[] nums = request.getParameterValues("num");

		int result = 0;
		for(int i=0; i<nums.length; i++) {
			int num = 0;
			if(!nums[i].equals("")&& nums[i]!=null) num = Integer.parseInt(nums[i]);//num선언은 반복되지 않는다.연산만 반복된다.
			result += num;
		}

		response.getWriter().printf("result:%d",result);
	}
}