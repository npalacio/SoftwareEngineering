<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
				<a class="navbar-brand" href="#">BooksOutForHarambe</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
				<li><a href="${pageContext.request.contextPath}/MyBooks">My
						Books</a></li>
				<li><a href="${pageContext.request.contextPath}/Notifications">Notifications</a></li>
				<li><a href="${pageContext.request.contextPath}/ContactUs">Contact
						Us</a></li>
			</ul>
		</div>
	</nav>
	<h1>This is the contact us page</h1>
	<div class="contactUsDiv">
		<h3>Please feel free to contact us:</h3>
		<strong>Telephone:</strong> 1-800-555-5555 <br /> <strong>Email:</strong>
		admin@BooksOutForHarambe.com <br /> <strong>Mail:</strong> 1110 S
		67th St Omaha NE 68182 <br>
		<h3>Or leave us a message:</h3>
		<form>
			<table>
				<tr>
					<td>Subject:</td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td>Message:</td>
					<td><textarea rows="4" cols="50"></textarea></td>
				</tr>
			</table>
			<br />
			<button type="button">Submit</button>
		</form>
	</div>
	<br/>
	<div class="footer">
		<p>Books Out For Harambe, BOFH&copy; 2016</p>
		<p>1110 S 67th St Omaha NE 68182</p>
		<!-- 	<p>Contributors: Nick Palacio, Alejandra Iniguez, Joseph Stein</p><br/> -->
		<p>1-800-555-5555</p>
	</div>
</body>
</html>