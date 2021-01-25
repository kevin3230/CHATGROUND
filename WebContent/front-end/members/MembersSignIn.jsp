<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>ChatGround</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/utility/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/members/css/MembersSignIn.css">
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-10">
				<div class="row justify-content-center">
					<!-- 要改成servlet路徑 -->
					<form action="<%=request.getContextPath() %>/" method="post" id="signIn">
						<table>
							<thead>
								<tr>
									<th colspan="2" style="text-align:center;">會員登入</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>帳號</th>
									<td><input type="text" name="account"></td>
								</tr>
								<tr>
									<th>密碼</th>
									<td><input type="password" name="password"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div class="row justify-content-center">
					<a href="<%= request.getContextPath()%>/front-end/members/MembersSignUp.jsp">註冊會員</a>
					<button type="submit" form="signIn">登入</button>
				</div>
			</div>
		</div>
	</div>
	<script src="<%= request.getContextPath()%>/utility/js/jquery-3.5.1.min.js"></script>
    <script src="<%= request.getContextPath()%>/utility/js/popper.min.js"></script>
    <script src="<%= request.getContextPath()%>/utility/js/bootstrap.min.js"></script>
</body>
</html>