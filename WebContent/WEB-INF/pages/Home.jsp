<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="models.*"%>
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
	<h1>
		This is the Home page<br />
	</h1>
	<!--	<h3>User Login</h3>
 	<form method='get'>
		User Name: <input type='text' name='UserName'> Password: <input
			type='password' name='Password'> <input type='submit'
			value='Submit'>

	</form>
 -->
	<h2>List of Available Books</h2>

	<jsp:useBean id="exampleBook" class="models.Book" scope="page">
		<jsp:setProperty name="exampleBook" property="title" value="TITLE" />
		<jsp:setProperty name="exampleBook" property="author" value="Author" />
		<jsp:setProperty name="exampleBook" property="publisher"
			value="Publisher" />
		<jsp:setProperty name="exampleBook" property="date" value="Date" />
		<jsp:setProperty name="exampleBook" property="ISBN" value="123456789" />
	</jsp:useBean>

	<jsp:useBean id="exampleBook2" class="models.Book" scope="page">
		<jsp:setProperty name="exampleBook2" property="title" value="TITLE2" />
		<jsp:setProperty name="exampleBook2" property="author" value="Author2" />
		<jsp:setProperty name="exampleBook2" property="publisher"
			value="Publisher2" />
		<jsp:setProperty name="exampleBook2" property="date" value="Date2" />
		<jsp:setProperty name="exampleBook2" property="ISBN"
			value="1234567892" />
	</jsp:useBean>

	<table style="width: 50%">
		<tr>
			<td>Name</td>
			<td>${exampleBook.title}</td>
		</tr>
		<tr>
			<td>Author</td>
			<td>${exampleBook.author}</td>
		</tr>
		<tr>
			<td>Publisher</td>
			<td>${exampleBook.publisher}</td>
		</tr>
		<tr>
			<td>Date</td>
			<td>${exampleBook.date}</td>
		</tr>
		<tr>
			<td>ISBN</td>
			<td>${exampleBook.ISBN}</td>
		</tr>
	</table>
	<input type='button' value='Trade'>
	<input type='button' value='Purchase'>
	<br>
	<br />

	<table style="width: 50%">
		<tr>
			<td>Name</td>
			<td>${exampleBook2.title}</td>
		</tr>
		<tr>
			<td>Author</td>
			<td>${exampleBook2.author}</td>
		</tr>
		<tr>
			<td>Publisher</td>
			<td>${exampleBook2.publisher}</td>
		</tr>
		<tr>
			<td>Date</td>
			<td>${exampleBook2.date}</td>
		</tr>
		<tr>
			<td>ISBN</td>
			<td>${exampleBook2.ISBN}</td>
		</tr>
	</table>
	<input type='button' value='Trade'>
	<input type='button' value='Purchase'>
	<br>
	<br />

	<div class="footer">
		<p>Books Out For Harambe, BOFH&copy; 2016</p>
		<p>1110 S 67th St Omaha NE 68182</p>
		<!-- 	<p>Contributors: Nick Palacio, Alejandra Iniguez, Joseph Stein</p><br/> -->
		<p>1-800-555-5555</p>
	</div>
</body>
</html>