package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseReader;
import database.DatabaseWriter;
import models.Book;
import models.Trade;
import models.User;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Trade")
public class TradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Trade trade;
	
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
		Book senderBook = dbr.findBook(Integer.parseInt(request.getParameter("id")));
		User senderUser = senderBook.getOwner();
		trade = new Trade(senderUser, user, senderBook, null);
		request.setAttribute("trade", trade);
		
    	getServletContext().getRequestDispatcher("/WEB-INF/pages/Trade.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
    	DatabaseReader dbr = new DatabaseReader();
    	DatabaseWriter dbw = new DatabaseWriter();
    	String recipientBook = request.getParameter("sendbook");
    	trade.setRecipientBook(dbr.findBook(Integer.parseInt(recipientBook)));
    	dbw.addTrade(trade);
    	
    	getServletContext().getRequestDispatcher("/WEB-INF/pages/Notifications.jsp").forward(request, response);
	}
    
}