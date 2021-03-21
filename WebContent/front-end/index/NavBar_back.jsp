<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NavBar</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/css/NavBar.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/utility/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid navBar">
		<div class="row justify-content-between">
			<div class="col-1" >
				<a href="<%= request.getContextPath()%>/front-end/index/index.jsp">首頁</a>
			</div>
			<div class="col-auto">
				<c:if test="${not empty sessionScope.membersVO_memNo}">
					<a href="<%= request.getContextPath()%>/front-end/chatground/chatground.jsp">聊天室</a>
				</c:if>
				
				<a href="<%= request.getContextPath()%>/front-end/members/MembersSignUp.jsp">註冊</a>
				
				<c:if test="${empty sessionScope.membersVO_memNo}">
					<a href="<%= request.getContextPath()%>/front-end/members/MembersSignIn.jsp">登入</a>
				</c:if>
				<c:if test="${not empty sessionScope.membersVO_memNo}">
					歡迎 ${sessionScope.membersVO_memAcc}
					<a href="<%= request.getContextPath()%>/Members/Members.do?action=signOut">登出</a>
				</c:if>
			</div>
		</div>
	</div>

<script src="<%= request.getContextPath()%>/utility/js/jquery-3.5.1.min.js"></script>
<script src="<%= request.getContextPath()%>/utility/js/popper.min.js"></script>
<script src="<%= request.getContextPath()%>/utility/js/bootstrap.min.js"></script>
</body>
</html>