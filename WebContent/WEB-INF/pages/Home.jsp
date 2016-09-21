<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="models.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BooksOutForHarambe</title>
</head>
<body>
<ul>
	<li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
	<li><a href="${pageContext.request.contextPath}/MyBooks">MyBooks</a></li>
	<li><a href="${pageContext.request.contextPath}/Notifications">Notifications</a></li>
	<li><a href="${pageContext.request.contextPath}/ContactUs">ContactUs</a></li>
</ul>
<h1>This is the Home page<br /></h1>
<h3>User Login</h3>
<form method='get'>
	User Name: 
	<input type='text' name='UserName'>
	Password: 
	<input type='password' name='Password'>
	<input type='submit' value='Submit'>

</form>
<h2>List of Trades</h2>
	<%
		Book exampleBook = new Book("Title", "Author", "Publisher", "Date", 123456789);
		Trade exampleTrade = new Trade(exampleBook, "Seller Name", 14.99);

		out.println("Name: " + exampleTrade.getBook().getName() + "<br />");
		out.println("Author: " + exampleTrade.getBook().getAuthor() + "<br />");
		out.println("Publisher: " + exampleTrade.getBook().getPublisher() + "<br />");
		out.println("Date Published: " + exampleTrade.getBook().getDate() + "<br />");
		out.println("ISBN: " + exampleTrade.getBook().getISBN() + "<br />");
		out.println("<input type='button' value='Request Trade'>");
	%>

</body>
</html>