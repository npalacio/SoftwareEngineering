package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseWriter;
import models.Book;
import models.User;

/**
 * Servlet implementation class MyBooksServlet
 */
@WebServlet("/MyBooks")
public class MyBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyBooksServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/pages/MyBooks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		addBook(request, messages, getBookToAdd(request, messages));
		request.setAttribute("success", true);
		getServletContext().getRequestDispatcher("/WEB-INF/pages/MyBooks.jsp").forward(request, response);
	}
	
	private void addBook(HttpServletRequest request, Map<String, String> messages, Book book) {
		boolean result = false;
		if(book != null){
			try{
				result = DatabaseWriter.addBook(book);
				if(result) {
					messages.put("result", "Book successfully added!");
					request.setAttribute("success", true);
				} else {
					messages.put("result", "Unable to add book");
				}
			} catch(Exception e) {
				e.printStackTrace();
				messages.put("result", "Unable to add book to database, exception thrown");
			}
		} else {
			messages.put("result", "Unable to add book, invalid input");
		}
	}

	private Book getBookToAdd(HttpServletRequest request, Map<String, String> messages){
		String title = null;
		String author = null;
		String publisher = null;
		int year = 0;
		long isbn = 0;
		if(request.getParameter("title") != null){
			title = request.getParameter("title");
		} else {
			messages.put("title", "Title for book is required");
			return null;
		}
		//System.out.println(title);
		if(request.getParameter("author") != null){
			author = request.getParameter("author");
		} else {
			messages.put("author", "Author for book is required");
			return null;
		}
		if(request.getParameter("publisher") != null){
			publisher = request.getParameter("publisher");
		} else {
			messages.put("publisher", "Publisher for book is required");
			return null;
		}
		try{
			year = Integer.parseInt(request.getParameter("year"));
		} catch(NumberFormatException e) {
			//e.printStackTrace();
			System.out.println("Year not in valid format");
			messages.put("year", "Year is required and must be numeric");
			return null;
		}
		try{
			isbn = Integer.parseInt(request.getParameter("isbn"));
		} catch(NumberFormatException e) {
			//e.printStackTrace();
			System.out.println("ISBN not in valid format");
			messages.put("isbn", "ISBN is required and must be numeric");
			return null;
		}
		User user = (User) request.getSession().getAttribute("user");
		//TODO: Add checkbox to form so the person can decide if this book is available or not
		if(user != null){
			return new Book(user, title, author, publisher, year, isbn, true);
		} else {
			messages.put("user", "User could not be retrieved from session");
			return null;
		}
	}

}
