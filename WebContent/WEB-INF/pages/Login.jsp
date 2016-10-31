<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>
<script type="text/javascript" src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="customCSS/custom.css" />
</head>
<body>
	<div class="logo">
		<center>
			<img alt="Logo" src="images/BOFHLogo.jpg" height="200" width="200">
		</center>
	</div>
	<h2 class="text-center">Books Out For Harambe&copy;</h2>
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<form role="form" method="post" action="${pageContext.request.contextPath}/Login">
				<c:if test="${!messages.result.isEmpty() && messages.result != null}">
					<span class="text-danger">${messages.result}</span>
				</c:if>
				<div class="form-group text-center">
					<label>Username</label> <input type="text" name="username"
						class="form-control">
				</div>
				<div class="form-group text-center">
					<label>Password</label><input type="password" name="password"
						class="form-control">
				</div>
				<input type="submit" value="Login" class="btn btn-default center-block">
			</form>
		</div>
	</div>
	<div class="footer text-muted">
		<p>Books Out For Harambe, BOFH&copy; 2016</p>
		<p>1110 S 67th St Omaha NE 68182</p>
		<!-- 	<p>Contributors: Nick Palacio, Alejandra Iniguez, Joseph Stein</p><br/> -->
		<p>1-800-555-5555</p>
	</div>
</body>
</html>