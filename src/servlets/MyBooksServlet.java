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

import database.DatabaseReader;
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
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			getServletContext().getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
			return;
		}
		setDatabaseReader(request, user);
		//The column attribute would have been put in the query string when the user picks a column
		//The column attribute is used in the query to the database to select how to order the books returned
		request.setAttribute("column", request.getParameter("col"));
		getServletContext().getRequestDispatcher("/WEB-INF/pages/MyBooks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void setDatabaseReader(HttpServletRequest request, User user){
//		User user = new User("npalacio", "fakePassword");
		request.setAttribute("user", user);
		DatabaseReader dbr = new DatabaseReader();
		request.setAttribute("dbr", dbr);
	}
}
