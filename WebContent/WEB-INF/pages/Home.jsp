<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="models.*" import="database.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- I moved the declaration of the database reader to the jsp page here because the login page needs to 
     redirect to this page if they login right and this page needs a database reader object to populate the list -->
<%@ page import="database.DatabaseReader"%>
<jsp:useBean id="dbr" class="database.DatabaseReader" />

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
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/Home">BooksOutForHarambe</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
				<li><a href="${pageContext.request.contextPath}/MyBooks">MyBooks</a></li>
				<li><a href="${pageContext.request.contextPath}/AddBook">Add Book</a></li>
				<li><a href="${pageContext.request.contextPath}/Notifications">Notifications</a></li>
				<li><a href="${pageContext.request.contextPath}/ContactUs">ContactUs</a></li>
			</ul>
		</div>
	</nav>
	<h3>Welcome, <c:out value="${username}"/></h3>
	<h5>Here are the books available for purchasing/trading today:</h5>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-offset-1">
				<c:if
					test="${!messages.result.isEmpty() && messages.result.contains('Can')}">
					<span class="text-danger">${messages.result}</span>
				</c:if>
				<c:if
					test="${!messages.result.isEmpty() && messages.result.contains('failed')}">
					<span class="text-danger">${messages.result}</span>
				</c:if>
			</div>
		</div>
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<table class="table table-bordered table-condensed table-striped">
					<tr>
						<td><strong><a href="${pageContext.request.contextPath}/Home?col=Title">Title</a></strong></td>
						<td><strong><a href="${pageContext.request.contextPath}/Home?col=Author">Author</a></strong></td>
						<td><strong><a href="${pageContext.request.contextPath}/Home?col=Publisher">Publisher</a></strong></td>
						<td><strong><a href="${pageContext.request.contextPath}/Home?col=Year">Year</a></strong></td>
						<td><strong><a href="${pageContext.request.contextPath}/Home?col=ISBN">ISBN</a></strong></td>
						<td><strong><a href="${pageContext.request.contextPath}/Home?col=Price">Price</a></strong></td>
					</tr>
					<c:forEach items="${dbr.getAvailableBooks(column)}" var="book">
					
						<tr class="text">
							<td class="italic"><c:out value="${book.getTitle()}"/></td>
							<td><c:out value="${book.getAuthor()}"/></td>
							<td><c:out value="${book.getPublisher()}"/></td>
							<td><c:out value="${book.getYear()}"/></td>
							<td><c:out value="${book.getISBN()}"/></td>
							<td><fmt:formatNumber value="${book.getPrice()}" type="currency"/></td>
							<td class="text-primary btn btn-sm"><a
								href="${pageContext.request.contextPath}/Trade?id=${book.getId()}"><button>Trade</button></a>&nbsp;
								<form method="post" action="${pageContext.request.contextPath}/Home">
									<input type="hidden" name="purchase" value="${book.getId()}">
									<input type="submit" name="purchasebutton" value="Purchase">
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
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