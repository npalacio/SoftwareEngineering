package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseWriter;

/**
 * Servlet implementation class RemoveBookServlet
 */
@WebServlet("/RemoveBook")
public class RemoveBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveBookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//getServletContext().getRequestDispatcher("/WEB-INF/pages/MyBooks.jsp").forward(request, response);
		request.getRequestDispatcher("/MyBooks").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//messages is used to display messages to the user based on the result of the book removal
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		DatabaseWriter writer = new DatabaseWriter();
		int id = 0;
		try{
			id = Integer.parseInt(request.getParameter("id"));
		} catch(NumberFormatException e){
			System.out.println("Book ID not in correct format or null, book not removed");
		}
		//MyBooks.jsp looks for the keywords 'succeeded' or 'failed' so it knows what message to display to user
		if(id != 0) {
			boolean success = writer.deleteBook(id);
			if(success) messages.put("result", "Book removal succeeded!");
		} else {
			messages.put("result", "Book removal failed");
		}
		//getServletContext().getRequestDispatcher("/WEB-INF/pages/MyBooks.jsp").forward(request, response);
		request.getRequestDispatcher("/MyBooks").forward(request, response);
	}

}
