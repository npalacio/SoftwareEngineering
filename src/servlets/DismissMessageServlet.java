package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;

/**
 * Servlet implementation class DismissMessageServlet
 */
@WebServlet("/DismissMessageServlet")
public class DismissMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DismissMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			getServletContext().getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
			return;
		}
		int id = 0;
		id = Integer.parseInt(request.getParameter("id"));
		if(id == 0) {
			System.out.println("Id either not given or set as 0 in DismissMessage servlet");
			getServletContext().getRequestDispatcher("/WEB-INF/pages/Notifications.jsp").forward(request, response);
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
