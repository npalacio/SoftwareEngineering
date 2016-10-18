<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="models.*" import="database.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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
	<h2>List of My Books For Sale or Trade</h2>
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
					<c:forEach items="${dbr.getMyBooks(user)}" var="book">
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

		<h2>Add Book</h2>
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<form role="form" method="post">
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
					<input type="submit" value="Add Book" class="btn btn-default">
				</form>
				<c:if test="${isPost && success}">
					<p class="text-success">Book added!</p>
				</c:if>
			</div>
		</div>
	</div>
	<br />
	<%-- 	<c:if test="${isPost && !success}">
		<div class="alert alert-danger">
			<ul>
				<c:if test="${!messages.result.isEmpty() && messages.result != null}"><li class="text-danger">*${messages.result}</li></c:if>
				<c:if test="${!messages.user.isEmpty() && messages.user != null}"><li class="text-danger">*${messages.user}</li></c:if>
				<c:if test="${!messages.title.isEmpty() && messages.title != null}"><li class="text-danger">*${messages.title}</li></c:if>
				<c:if test="${!messages.author.isEmpty() && messages.author != null}"><li class="text-danger">*${messages.author}</li></c:if>
				<c:if test="${!messages.publisher.isEmpty() && messages.publisher != null}"><li class="text-danger">*${messages.publisher}</li></c:if>
				<c:if test="${!messages.year.isEmpty() && messages.year != null}"><li class="text-danger">*${messages.year}</li></c:if>
				<c:if test="${!messages.isbn.isEmpty() && messages.isbn != null}"><li class="text-danger">*${messages.isbn}</li></c:if>
			</ul>
		</div>
	</c:if>
 --%>

	<div class="footer">
		<p>Books Out For Harambe, BOFH&copy; 2016</p>
		<p>1110 S 67th St Omaha NE 68182</p>
		<!-- 	<p>Contributors: Nick Palacio, Alejandra Iniguez, Joseph Stein</p><br/> -->
		<p>1-800-555-5555</p>
	</div>
</body>
</html>