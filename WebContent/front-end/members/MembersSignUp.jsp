<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>ChatGround</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/utility/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/members/css/MembersSignUp.css">
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-10">
				<div class="col-12">
					會員註冊
				</div>
				<div class="col-12">
					<!-- 要改成servlet路徑 -->
					<form action="<%=request.getContextPath() %>/" method="post" id="signUp">
						<table>
							<thead>
								<tr>
									<th><font color="red">*</font>為必填欄位</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th><label class="xrequired">帳號</label></th>
									<td><input type="text" name="account" required="required"></td>
								</tr>
								<tr>
									<th><label class="xrequired">密碼</label></th>
									<td><input type="password" name="password" required="required"></td>
								</tr>
								<tr>
									<th><label class="xrequired">確認密碼</label></th>
									<td><input type="password" name="password" required="required"></td>
								</tr>
								<tr>
									<th><label class="xrequired">暱稱</label></th>
									<td><input type="text" name="nickname" required="required"></td>
								</tr>
								<tr>
									<th><label class="xrequired">信箱</label></th>
									<td><input type="email" name="email" required="required"></td>
								</tr>
								<tr>
									<th><label class="xrequired">生日</label></th>
									<td><input type="date" name="birthday" required="required"></td>
								</tr>
								<tr>
									<th><label class="xrequired">性別</label></th>
									<td>
										<input type="radio" name="gender" value="男" id="gendermale" required="required"><label for="gendermale">男</label>
										<input type="radio" name="gender" value="女" id="genderfemale" required="required"><label for="genderfemale">女</label>
										<input type="radio" name="gender" value="X" id="genderX" required="required"><label for="genderX">X</label>
									</td>
								</tr>
								<tr>
									<th>大頭照</th>
									<td><input type="file" name="mempic"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div class="row justify-content-center">
					<button type="submit" form="signUp">註冊</button>
				</div>
			</div>
		</div>
	</div>
	<script src="<%= request.getContextPath()%>/utility/js/jquery-3.5.1.min.js"></script>
    <script src="<%= request.getContextPath()%>/utility/js/popper.min.js"></script>
    <script src="<%= request.getContextPath()%>/utility/js/bootstrap.min.js"></script>
</body>
</html>