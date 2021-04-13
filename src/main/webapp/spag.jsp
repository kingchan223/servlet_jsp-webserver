<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
pageContext.setAttribute("result", "hello");//page에 result라는 키값으로 데이터 저장
%>
<body>
	<%=request.getAttribute("result")%>입니다.<br>
	${result}입니다.<br><!-- page가 우서순위가 높으므로 page의 hello를 출력한다. -->
	<!-- request에서만 찾도록 지정 -->
	requestScope.result:${requestScope.result}<br>
	<!-- page에서만 찾도록 지정 -->
	pageScope.result:${pageScope.result}<br>
	${names[1]}<br>
	${notice.title}<br>
	${aa}<br>
	
	<!-- param, header객체 사용해보기  -->
	param.n: ${param.n}<br>
	header.accept: ${header.accept}<br>
	
	<!-- EL연산자 -->
	EL연산자<br>
	${param.n > 3}<br>
	${param.n ge 3}<br>
	${empty param.n}<br> <!-- null이거나 빈문자열이면 true -->
	${empty param.n?"값이 비어있습니다":param.n}<br>
	${param.n/2}
</body>
</html>