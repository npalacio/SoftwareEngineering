<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ContactUs</title>
<script type="text/javascript" src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="customCSS/custom.css" />
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/Home">BooksOutForHarambe</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
				<li><a href="${pageContext.request.contextPath}/MyBooks">MyBooks</a></li>
				<li><a href="${pageContext.request.contextPath}/AddBook">AddBook</a></li>
				<li><a href="${pageContext.request.contextPath}/Notifications">Notifications</a></li>
				<li><a href="${pageContext.request.contextPath}/ContactUs">ContactUs</a></li>
			</ul>
		</div>
	</nav>

	<h2 class="text-center">Please feel free leave us a message:</h2>
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<form role="form" method="post"
				action="${pageContext.request.contextPath}/ContactUs">
				<c:if
					test="${!messages.success.isEmpty() && messages.success != null}">
					<span class="text-success">${messages.success}</span>
				</c:if>
				<div class="form-group">
					<label>Message:</label>
					<textarea rows="4" cols="50" name="message" class="form-control"></textarea>
				</div>
				<c:if
					test="${!messages.result.isEmpty() && messages.result != null}">
					<span class="text-danger">*${messages.result}</span>
					<br />
				</c:if>
				<input type="submit" value="Send" class="btn btn-default">
			</form>
		</div>
	</div>
	<div class="contactUsDiv">
		<h3>Or contact us here:</h3>
		<strong>Telephone:</strong> 1-800-555-5555 <br /> <strong>Email:</strong>
		admin@BooksOutForHarambe.com <br /> <strong>Mail:</strong> 1110 S
		67th St Omaha NE 68182 <br>
	</div>
	<br />
	<div class="footer text-muted">
		<p>Books Out For Harambe, BOFH&copy; 2016</p>
		<p>1110 S 67th St Omaha NE 68182</p>
		<!-- 	<p>Contributors: Nick Palacio, Alejandra Iniguez, Joseph Stein</p><br/> -->
		<p>1-800-555-5555</p>
	</div>
</body>
</html>