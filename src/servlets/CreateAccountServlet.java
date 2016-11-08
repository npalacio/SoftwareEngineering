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
import models.User;

/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccount")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/pages/CreateAccount.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username.isEmpty() || password.isEmpty()){
			messages.put("result", "*Username and Password are required");
			getServletContext().getRequestDispatcher("/WEB-INF/pages/CreateAccount.jsp").forward(request, response);
			return;
		}
		User user = new User(username, password);
		DatabaseWriter dbw = new DatabaseWriter();
		if(dbw.addUser(user)){
			request.getSession().setAttribute("user", user);
			getServletContext().getRequestDispatcher("/WEB-INF/pages/Home.jsp").forward(request, response);
			return;
		} else {
			messages.put("result", "Unable to add new user at this time!");
			getServletContext().getRequestDispatcher("/WEB-INF/pages/CreateAccount.jsp").forward(request, response);
			return;
		}
	}

}
