package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseReader;
import models.Book;
import models.Trade;
import models.User;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Trade")
public class TradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			getServletContext().getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
			return;
		}
		
		DatabaseReader dbr = new DatabaseReader();
		request.setAttribute("dbr", dbr);
		request.setAttribute("column", request.getParameter("col"));
		Book SenderBook = dbr.findBook(Integer.parseInt(request.getParameter("id")));
		User SenderUser = SenderBook.getOwner();
		Trade trade = new Trade(SenderUser, user, SenderBook, null);
		request.setAttribute("trade", trade);
		
    	getServletContext().getRequestDispatcher("/WEB-INF/pages/Trade.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    
}