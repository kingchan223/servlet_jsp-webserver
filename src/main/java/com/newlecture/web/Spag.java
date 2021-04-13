package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = 0;
		String num_ = request.getParameter("n");
		if(num_!= null && !num_.equals(""))
			num = Integer.parseInt(num_);
		
		String result; //얘가 모델
		if(num%2 != 0 ){
			result = "홀수";
		}else{
			result = "짝수";
		}
		
		//request를 spag와 Spag 공통의 저장소로 사용할 수 있다.
		request.setAttribute("result", result);
		
		String[] names = {"liam", "noel"};//리스트 전달하기
		request.setAttribute("names", names);//request에 names를 심는다.
		
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title","EL에 대해서");
		request.setAttribute(result, notice);
		//redirect:새로 요청할 때
		//forward:일을 이어갈 때
		//spag.jsp와 포워딩하기
		RequestDispatcher dispather = request.getRequestDispatcher("spag.jsp");
		//spag와 request, response를 공유할 수 있다.
		dispather.forward(request, response);
	}
}
