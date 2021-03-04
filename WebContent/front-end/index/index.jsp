<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>ChatGround</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/utility/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="/front-end/index/NavBar.jsp" flush="true" />
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-10">
				<div class="col-12">
					群聊網站
				</div>
				<div class="col-12">
					<a href="#">聊天室</a>
					<a href="<%= request.getContextPath()%>/front-end/members/MembersSignUp.jsp">註冊</a>
					<a href="#">會員登入</a>
				</div>
			</div>
		</div>
	</div>
	<script src="<%= request.getContextPath()%>/utility/js/jquery-3.5.1.min.js"></script>
    <script src="<%= request.getContextPath()%>/utility/js/popper.min.js"></script>
    <script src="<%= request.getContextPath()%>/utility/js/bootstrap.min.js"></script>
</body>
</html>