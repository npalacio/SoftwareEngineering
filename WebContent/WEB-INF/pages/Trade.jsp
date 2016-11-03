<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="models.*" import="database.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Trade</title>
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
				<li><a href="${pageContext.request.contextPath}/MyBooks">My
						Books</a></li>
				<li><a href="${pageContext.request.contextPath}/AddBook">Add
						Book</a></li>
				<li><a href="${pageContext.request.contextPath}/Notifications">Notifications</a></li>
				<li><a href="${pageContext.request.contextPath}/ContactUs">Contact
						Us</a></li>
			</ul>
		</div>
	</nav>
	<h2>Start A Trade</h2>
	<!--<c:out value="${dbr.getAvailableBooks(column)[param.id].getTitle()}" />
	<c:import var="book" url="/WEB-INF/pages/Home.jsp"/>
	<c:out value="${requestScope.book.getTitle()}"/>-->
	<br/>
	<h3>Book you will receive: </h3>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<table class="table table-bordered table-condensed table-striped">
					<tr>
						<td><strong><a href="${pageContext.request.contextPath}/Home?col=Title">Title</a></strong></td>
						<td><strong><a href="${pageContext.request.contextPath}/Home?col=Author">Author</a></strong></td>
						<td><strong><a href="${pageContext.request.contextPath}/Home?col=Publisher">Publisher</a></strong></td>
						<td><strong><a href="${pageContext.request.contextPath}/Home?col=Year">Year</a></strong></td>
						<td><strong><a href="${pageContext.request.contextPath}/Home?col=ISBN">ISBN</a></strong></td>
					</tr>
					<tr class="text">
						<td><c:out value="${trade.getSenderBook().getTitle()}"/><td/>
						<td><c:out value="${trade.getSenderBook().getAuthor()}"/><td/>
						<td><c:out value="${trade.getSenderBook().getPublisher()}"/><td/>
						<td><c:out value="${trade.getSenderBook().getYear()}"/><td/>
						<td><c:out value="${trade.getSenderBook().getISBN()}"/><td/>
					</tr>
				</table>
			</div>
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