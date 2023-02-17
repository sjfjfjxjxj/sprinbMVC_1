<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page session="false" %> --%>
<!-- home.jsp는 디폴트값이 세션 펄스래! 그래서 이거 안지우면 로그인이 안됨 -->
<html>
	<head>
		<title>Home</title>
		
	<body>
		<!-- jsp액션태그...상대경로로... -->
		<jsp:include page="./common/menuBar.jsp"></jsp:include>
	</body>
</html>
