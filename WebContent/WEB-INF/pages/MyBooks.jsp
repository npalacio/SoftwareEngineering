<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="models.*" import="database.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	background-color: #e6f9ff;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MyBooks</title>
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
	<h1>This is the My Books page</h1>

	<h2>List of My Books For Sale or Trade</h2>

	<c:forEach items="${dbr.getMyBooks(user)}" var="book">
		<table style="width: 50%">
		<tr>
			<td>Name</td>
			<td>${book.getTitle()}</td>
		</tr>
		<tr>
			<td>Author</td>
			<td>${book.getAuthor()}</td>
		</tr>
		<tr>
			<td>Publisher</td>
			<td>${book.getPublisher()}</td>
		</tr>
		<tr>
			<td>Date</td>
			<td>${book.getYear()}</td>
		</tr>
		<tr>
			<td>ISBN</td>
			<td>${book.getISBN()}</td>
		</tr>
	</table>
	<input type='button' value='Trade'>
	<input type='button' value='Purchase'>
	<br>
	<br />
	</c:forEach>
	
	<h2>Add Book</h2>
	<form method="post">
		<label>Title:</label> <input type="text" name="title"><br />
		<label>Author:</label> <input type="text" name="author"><br />
		<label>Publisher:</label> <input type="text" name="publisher"><br />
		<label>Year:</label> <input type="text" name="year"><br /> <label>ISBN:</label>
		<input type="text" name="isbn"><br /> <input type="submit"
			value="Add Book">
	</form>
	<c:if test="${success}">
		<div class="alert alert-danger">
			<ul>
				<li>${messages.result}</li>
				<li>${messages.user}</li>
				<li>${messages.title}</li>
				<li>${messages.author}</li>
				<li>${messages.publisher}</li>
				<li>${messages.year}</li>
				<li>${messages.isbn}</li>
			</ul>
		</div>
	</c:if>
	<div class="footer">
		<p>Books Out For Harambe, BOFH&copy; 2016</p>
		<p>1110 S 67th St Omaha NE 68182</p>
		<!-- 	<p>Contributors: Nick Palacio, Alejandra Iniguez, Joseph Stein</p><br/> -->
		<p>1-800-555-5555</p>
	</div>
</body>
</html>