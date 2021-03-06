<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="models.*" import="database.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Add Book</title>
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
	<div class="container-fluid" style="margin-bottom: 30px;">
		<h2>Add Book</h2>
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<form role="form" method="post"
					action="${pageContext.request.contextPath}/AddBook">
					<div class="form-group">
						<c:if
							test="${!messages.result.isEmpty() && messages.result != null}">
							<span class="text-danger">${messages.result}</span>
						</c:if>
						<c:if test="${!messages.user.isEmpty() && messages.user != null}">
							<br />
							<span class="text-danger">${messages.user}</span>
						</c:if>
					</div>
					<div class="form-group">
						<label>Title:</label> <input type="text" name="title"
							value="${title}" class="form-control">
						<c:if
							test="${!messages.title.isEmpty() && messages.title != null}">
							<span class="text-danger">*${messages.title}</span>
						</c:if>
					</div>
					<div class="form-group">
						<label>Author:</label> <input type="text" name="author"
							value="${author}" class="form-control">
						<c:if
							test="${!messages.author.isEmpty() && messages.author != null}">
							<span class="text-danger">*${messages.author}</span>
						</c:if>
					</div>
					<div class="form-group">
						<label>Publisher:</label> <input type="text" name="publisher"
							value="${publisher}" class="form-control">
						<c:if
							test="${!messages.publisher.isEmpty() && messages.publisher != null}">
							<span class="text-danger">*${messages.publisher}</span>
						</c:if>
					</div>
					<div class="form-group">
						<label>Year:</label> <input type="text" name="year"
							value="${year}" class="form-control">
						<c:if test="${!messages.year.isEmpty() && messages.year != null}">
							<span class="text-danger">*${messages.year}</span>
						</c:if>
					</div>
					<div class="form-group">
						<label>ISBN:</label><input type="text" name="isbn" value="${isbn}"
							class="form-control">
						<c:if test="${!messages.isbn.isEmpty() && messages.isbn != null}">
							<span class="text-danger">*${messages.isbn}</span>
						</c:if>
					</div>
					<div class="form-group">
						<label>Price:</label><input type="text" name="price" value="${price}"
							class="form-control">
						<c:if test="${!messages.price.isEmpty() && messages.price != null}">
							<span class="text-danger">*${messages.price}</span>
						</c:if>
					</div>
					<div class="form-group">
						<label>Available</label><input type="checkbox" class="checkbox" name="isAvailable" value="available" checked>
					</div>
					<input type="submit" value="Add Book" class="btn btn-default">
				</form>
				<c:if test="${isPost && success}">
					<p class="text-success">Book added!</p>
				</c:if>
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