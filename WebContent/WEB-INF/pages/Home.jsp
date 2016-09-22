<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="models.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BooksOutForHarambe</title>
<script type="text/javascript" src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css" />
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
	<h2>List of Available books</h2>
	<%
		Book exampleBook = new Book("Title", "Author", "Publisher", "Date", 123456789);
		Book exampleBook1 = new Book("Title1", "Author1", "Publisher1", "Date1", 12345678);
		User buyer = new User();
		User seller = new User();
		Trade exampleTrade = new Trade(buyer, seller, exampleBook, exampleBook1);

		out.println("Name: " + exampleTrade.getRecipientBook().getName() + "<br />");
		out.println("Author: " + exampleTrade.getRecipientBook().getAuthor() + "<br />");
		//		out.println("Publisher: " + exampleTrade.getBook().getPublisher() + "<br />");
		//		out.println("Date Published: " + exampleTrade.getBook().getDate() + "<br />");
		//		out.println("ISBN: " + exampleTrade.getBook().getISBN() + "<br />");
		out.println("<input type='button' value='Request Trade'>");
	%>
	<div class="footer">
	<p>Books Out For Harambe, BOFH&copy; 2016</p>
	<p>Contributors: Nick Palacio, Alejandra Iniguez, Joseph Stein</p>
	</div>
</body>
</html>