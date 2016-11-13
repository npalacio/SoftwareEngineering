package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseReader;
import database.DatabaseWriter;
import models.Book;
import models.User;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
		HttpSession session = request.getSession();
		//Since we still do not have a login page we will hardcode a user in that can be passed around for the other servlets to use
		session.setAttribute("user", user);
		//The jsp page creates the database reader
		request.setAttribute("column", request.getParameter("col"));
		request.setAttribute("username", user.getName());
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		getServletContext().getRequestDispatcher("/WEB-INF/pages/Home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			getServletContext().getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		request.setAttribute("column", request.getParameter("col"));
		request.setAttribute("username", user.getName());
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		DatabaseWriter writer = new DatabaseWriter();
		DatabaseReader reader = new DatabaseReader();
		int id = 0;
		String page = null;
		boolean bool = true;
		try{
			id = Integer.parseInt(request.getParameter("purchase"));
		} catch(NumberFormatException e){
			System.out.println("Book ID not in correct format or null, book not removed");
		}
		
		User seller = reader.findBook(id).getOwner();
		
		if(id != 0) {
			for(Book book : reader.getMyBooks(user, "Title")) {
				if(book.getId() == id) {
					messages.put("result", "Can not purchase a book you already own.");
					page = "/WEB-INF/pages/Home.jsp";
					bool = false;
					break;
				}
			}
			
			if(bool == true) {
				boolean success = writer.deleteBook(id);
				page = "/WEB-INF/pages/Notifications.jsp";
				if(success) {
					//Send a message to the seller and the buyer.
				}
			}
		} else {
			messages.put("result", "Book purchase failed");
			page = "/WEB-INF/pages/Home.jsp";
		}
		
		getServletContext().getRequestDispatcher(page).forward(request, response);
	}

}
