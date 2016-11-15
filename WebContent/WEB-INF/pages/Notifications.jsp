<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="database.DatabaseReader"%>
<jsp:useBean id="dbr" class="database.DatabaseReader" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Notifications</title>
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
				<a class="navbar-brand" href="${pageContext.request.contextPath}/Home">BooksOutForHarambe</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
				<li><a href="${pageContext.request.contextPath}/MyBooks">My Books</a></li>
				<li><a href="${pageContext.request.contextPath}/AddBook">Add Book</a></li>
				<li><a href="${pageContext.request.contextPath}/Notifications">Notifications</a></li>
				<li><a href="${pageContext.request.contextPath}/ContactUs">Contact Us</a></li>
			</ul>
		</div>
	</nav>
	
	<c:set var="msgs" scope="request" value="${dbr.getMessages(user)}"/>
	<c:if test="${!msgs.isEmpty()}">
	<h2 class="text-center">Messages:</h2>
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<table class="table table-bordered table-condensed table-striped">
				<tr>
					<td><strong>Message</strong></td>
				</tr>
				<c:forEach items="${msgs}" var="msg">
					<tr class="text">
						<td class="text-danger"><c:out value="${msg.getMessage()}"/></td>
						<td>
							<a class="btn btn-md" href="${pageContext.request.contextPath}/DismissMessage?id=${msg.getId()}">
								<button>Ok</button>
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	</c:if>
	
	<c:if test="${msgs.isEmpty()}">
		<h2 class="text-center">No Messages to display</h2>
	</c:if>	
	
	<c:set var="myTrades" scope="request" value="${dbr.getTradesByReceiver(user)}"/>
	<c:if test="${!myTrades.isEmpty()}">
	<h2 class="text-center">Trades proposed to you:</h2>
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<table class="table table-bordered table-condensed table-striped">
				<tr>
					<td><strong>Sender</strong></td>
					<td><strong>Receiver</strong></td>
					<td><strong>Sender's Book</strong></td>
					<td><strong>Receiver's Book</strong></td>
				</tr>
				<c:forEach items="${myTrades}" var="trade">
					<tr class="text">
						<td><c:out value="${trade.getSender().getName()}"/></td>
						<td><c:out value="${trade.getRecipient().getName()}"/></td>
						<td class="italic"><c:out value="${trade.getSenderBook().getTitle()}"/></td>
						<td class="italic"><c:out value="${trade.getRecipientBook().getTitle()}"/></td>
						<td class="text-primary">
							<a class="btn btn-sm" href="${pageContext.request.contextPath}/RespondToTrade?result=true&id=${trade.getId()}">
								<button>Accept</button>
							</a>
						</td>
						<td>
							<a class="btn btn-sm" href="${pageContext.request.contextPath}/RespondToTrade?result=false&id=${trade.getId()}">
								<button>Decline</button>
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	</c:if>
	
	<c:if test="${myTrades.isEmpty()}">
		<h2 class="text-center">No Trades received</h2>
	</c:if>	

	<c:set var="myProposedTrades" scope="request" value="${dbr.getTradesBySender(user)}"/>
	<c:if test="${!myProposedTrades.isEmpty()}">
	<h2 class="text-center">Trades you have proposed:</h2>
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<table class="table table-bordered table-condensed table-striped">
				<tr>
					<td><strong>Sender</strong></td>
					<td><strong>Receiver</strong></td>
					<td><strong>Sender's Book</strong></td>
					<td><strong>Receiver's Book</strong></td>
				</tr>
				<c:forEach items="${myProposedTrades}" var="trade">
					<tr class="text">
						<td><c:out value="${trade.getSender().getName()}"/></td>
						<td><c:out value="${trade.getRecipient().getName()}"/></td>
						<td class="italic"><c:out value="${trade.getSenderBook().getTitle()}"/></td>
						<td class="italic"><c:out value="${trade.getRecipientBook().getTitle()}"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	</c:if>
	
	<c:if test="${myProposedTrades.isEmpty()}">
		<h2 class="text-center">No Trades proposed</h2>
	</c:if>	
	

	<div class="footer text-muted">
		<p>Books Out For Harambe, BOFH&copy; 2016</p>
		<p>1110 S 67th St Omaha NE 68182</p>
		<!-- 	<p>Contributors: Nick Palacio, Alejandra Iniguez, Joseph Stein</p><br/> -->
		<p>1-800-555-5555</p>
	</div>
</body>
</html>