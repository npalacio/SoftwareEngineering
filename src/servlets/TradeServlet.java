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
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		DatabaseReader dbr = new DatabaseReader();
		request.setAttribute("column", request.getParameter("col"));
		request.setAttribute("dbr", dbr);
		Book senderBook = dbr.findBook(Integer.parseInt(request.getParameter("id")));
		User senderUser = senderBook.getOwner();
		trade = new Trade(senderUser, user, senderBook, null);
		request.setAttribute("trade", trade);
		
    	getServletContext().getRequestDispatcher("/WEB-INF/pages/Trade.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
    	User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			getServletContext().getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
			return;
		}
		
    	Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
    	DatabaseReader dbr = new DatabaseReader();
    	DatabaseWriter dbw = new DatabaseWriter();
    	Book recipientBook = dbr.findBook(Integer.parseInt(request.getParameter("sendbook")));
    	trade.setRecipientBook(recipientBook);
    	request.setAttribute("trade", trade);
    	request.setAttribute("column", request.getParameter("col"));
    	request.setAttribute("dbr", dbr);
    	if(trade.getSender().getName().equals(trade.getRecipient().getName())){
    		messages.put("result", "Can not trade two of your own books.");
    		getServletContext().getRequestDispatcher("/WEB-INF/pages/Trade.jsp").forward(request, response);
    	}else{
    		dbw.addTrade(trade);
        	getServletContext().getRequestDispatcher("/WEB-INF/pages/Notifications.jsp").forward(request, response);
    	}
	}
    
}