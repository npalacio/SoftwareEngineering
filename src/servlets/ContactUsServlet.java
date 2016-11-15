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
import models.Message;
import models.User;

/**
 * Servlet implementation class ContactUsServlet
 */
@WebServlet("/ContactUs")
public class ContactUsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactUsServlet() {
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
		getServletContext().getRequestDispatcher("/WEB-INF/pages/ContactUs.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			getServletContext().getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
			return;
		}
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		String msg = request.getParameter("message");
		if(msg == null || msg.isEmpty()) {
			messages.put("result", "Message cannot be empty");
			getServletContext().getRequestDispatcher("/WEB-INF/pages/ContactUs.jsp").forward(request, response);
			return;
		}
		//This is the admin account I created in the database
		User admin = new User("admin", "administrator");
		Message message = new Message(admin, msg);
		DatabaseWriter dbw = new DatabaseWriter();
		if(dbw.addMessage(message)){
			messages.put("success", "Message successfully sent! Thank you for your feedback, Harambe would be proud!");
			getServletContext().getRequestDispatcher("/WEB-INF/pages/ContactUs.jsp").forward(request, response);
			return;
		}
		messages.put("result", "Message could not be sent :(");
		getServletContext().getRequestDispatcher("/WEB-INF/pages/ContactUs.jsp").forward(request, response);
	}

}
