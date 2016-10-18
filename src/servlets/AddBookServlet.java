package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseReader;
import database.DatabaseWriter;
import models.Book;
import models.User;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBook")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/MyBooks").forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setReader(request);
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		addBook(request, messages, getBookToAdd(request, messages));
		request.setAttribute("isPost", true);
		request.getRequestDispatcher("/MyBooks").forward(request, response);
		//getServletContext().getRequestDispatcher("/WEB-INF/pages/MyBooks.jsp").forward(request, response);
	}
	
	private void setReader(HttpServletRequest request){
		User user = new User("npalacio", "fakePassword");
		request.setAttribute("user", user);
		DatabaseReader dbr = new DatabaseReader();
		request.setAttribute("dbr", dbr);
	}
	
	private void addBook(HttpServletRequest request, Map<String, String> messages, Book book) {
		boolean result = false;
		//System.out.println("Book title: " + book.getTitle());
		if(book != null){
			try{
				result = DatabaseWriter.addBook(book);
				//System.out.println("Bood Added? " + result);
				if(result) {
					request.setAttribute("success", true);
					clearBookValues(request);
				} else {
					messages.put("result", "Unable to add book");
				}
			} catch(Exception e) {
				e.printStackTrace();
				messages.put("result", "Unable to add book to database, exception thrown");
			}
		} else {
			messages.put("result", "Unable to add book, invalid input");
			request.setAttribute("success", false);
		}
	}

	private Book getBookToAdd(HttpServletRequest request, Map<String, String> messages){
		boolean validData = true;
		String title = null;
		String author = null;
		String publisher = null;
		int year = 0;
		long isbn = 0;
		if(!request.getParameter("title").isEmpty()){
			title = request.getParameter("title");
			request.setAttribute("title", title);
			//System.out.println("Title is not null: " + title);
		} else {
			messages.put("title", "Title for book is required");
			validData = false;
		}
		//System.out.println(title);
		if(!request.getParameter("author").isEmpty()){
			author = request.getParameter("author");
			request.setAttribute("author", author);
		} else {
			messages.put("author", "Author for book is required");
			validData = false;
		}
		if(!request.getParameter("publisher").isEmpty()){
			publisher = request.getParameter("publisher");
			request.setAttribute("publisher", publisher);
		} else {
			messages.put("publisher", "Publisher for book is required");
			validData = false;
		}
		try{
			year = Integer.parseInt(request.getParameter("year"));
			request.setAttribute("year", year);
		} catch(NumberFormatException e) {
			//e.printStackTrace();
			//System.out.println("Year not in valid format");
			messages.put("year", "Year is required and must be numeric");
			validData = false;
		}
		try{
			isbn = Long.parseLong(request.getParameter("isbn"));
			request.setAttribute("isbn", isbn);
		} catch(NumberFormatException e) {
			//e.printStackTrace();
			System.out.println("ISBN not in valid format");
			messages.put("isbn", "ISBN is required and must be numeric");
			validData = false;
		}
		//Creating new user everytime for testing/developing purposes, normally user variable should be retrieved from session
		//User user = (User) request.getSession().getAttribute("user");
		User user = new User("npalacio", "fakePassword");
		//System.out.println("Username = " + user.getName());
		//TODO: Add checkbox to form so the person can decide if this book is available or not
		if(user == null){
			messages.put("user", "User could not be retrieved from session");
			validData = false;
		}
		if(validData){
			return new Book(user, title, author, publisher, year, isbn, true);
		} else {
			return null;
		}
	}
	
	private void clearBookValues(HttpServletRequest request){

		request.setAttribute("title", null);
		request.setAttribute("author", null);
		request.setAttribute("publisher", null);
		request.setAttribute("year", null);
		request.setAttribute("isbn", null);
	}
}
