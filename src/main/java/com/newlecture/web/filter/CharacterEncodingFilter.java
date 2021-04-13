package com.newlecture.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")//필터를 매핑할 url을 써준다. 모든 url에 필터가 작동하도록 했다.
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request
						, ServletResponse response
						, FilterChain chain)//FilterChain 얘가 요청이 들어오고 그 요청을 터 다음 서블릿으로 보낼까 말까를 결정한다.
			throws IOException, ServletException {

		/*필터 넘어가기 전*/
		request.setCharacterEncoding("UTF-8");//필터에다가 캐릭터 인코딩 설정
		//흐름을 넘겨서 필터 다음이 실행되게 한다. 그리고 실행을 서블릿에서 받으면 response가 받고
		chain.doFilter(request, response);//문지기역할.
		/*필터 넘어간 후*/ 
	}
}
