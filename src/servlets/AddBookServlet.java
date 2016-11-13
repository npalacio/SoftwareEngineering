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
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			getServletContext().getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
			return;
		}
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		getServletContext().getRequestDispatcher("/WEB-INF/pages/AddBook.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Used to display error/success messages to the user
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		addBook(request, messages, getBookToAdd(request, messages));
		request.setAttribute("isPost", true);
		getServletContext().getRequestDispatcher("/WEB-INF/pages/AddBook.jsp").forward(request, response);
	}
		
	private void addBook(HttpServletRequest request, Map<String, String> messages, Book book) {
		boolean result = false;
		//System.out.println("Book title: " + book.getTitle());
		DatabaseWriter writer = new DatabaseWriter();
		if(book != null){
			try{
				result = writer.addBook(book);
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
		} else {
			messages.put("title", "Title for book is required");
			validData = false;
		}
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
			messages.put("year", "Year is required and must be numeric");
			validData = false;
		}
		try{
			isbn = Long.parseLong(request.getParameter("isbn"));
			request.setAttribute("isbn", isbn);
		} catch(NumberFormatException e) {
			messages.put("isbn", "ISBN is required and must be numeric");
			validData = false;
		}
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			messages.put("user", "User could not be retrieved from session");
			validData = false;
		}
		boolean available = "available".equals(request.getParameter("isAvailable"));
		if(validData){
			return new Book(user, title, author, publisher, year, isbn, available);
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
