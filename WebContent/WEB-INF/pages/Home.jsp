<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="models.*" import="database.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BooksOutForHarambe</title>
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
				<li><a href="${pageContext.request.contextPath}/MyBooks">MyBooks</a></li>
				<li><a href="${pageContext.request.contextPath}/Notifications">Notifications</a></li>
				<li><a href="${pageContext.request.contextPath}/ContactUs">ContactUs</a></li>
			</ul>
		</div>
	</nav>
	<h3>
		Welcome, User!
	</h3>
	<h5>Here are the books available for purchasing/trading today:</h5>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<table class="table table-bordered table-condensed table-striped">
					<tr>
						<td><strong>Title</strong></td>
						<td><strong>Author</strong></td>
						<td><strong>Publisher</strong></td>
						<td><strong>Year</strong></td>
						<td><strong>ISBN</strong></td>
					</tr>
					<c:forEach items="${dbr.getAvailableBooks()}" var="book">
						<tr class="text">
							<td class="italic">${book.getTitle()}</td>
							<td>${book.getAuthor()}</td>
							<td>${book.getPublisher()}</td>
							<td>${book.getYear()}</td>
							<td>${book.getISBN()}</td>
							<td class="text-primary"><button>Trade</button>&nbsp;
								<button>Purchase</button></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<br />
	<div class="footer">
		<p>Books Out For Harambe, BOFH&copy; 2016</p>
		<p>1110 S 67th St Omaha NE 68182</p>
		<!-- 	<p>Contributors: Nick Palacio, Alejandra Iniguez, Joseph Stein</p><br/> -->
		<p>1-800-555-5555</p>
	</div>
</body>
</html>