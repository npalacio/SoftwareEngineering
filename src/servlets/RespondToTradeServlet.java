package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseReader;
import database.DatabaseWriter;
import models.Message;
import models.Trade;
import models.User;

/**
 * Servlet implementation class RespondToTradeServlet
 */
@WebServlet("/RespondToTrade")
public class RespondToTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RespondToTradeServlet() {
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
		String result = request.getParameter("result");
		int tradeId = 0;
		tradeId =  Integer.parseInt(request.getParameter("id"));
		if(tradeId == 0) {
			System.out.println("No tradeId passed into RespondToTrade servlet");
			getServletContext().getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
			return;
		}
		DatabaseReader dbr = new DatabaseReader();
		DatabaseWriter dbw = new DatabaseWriter();
		Trade trade = dbr.findTrade(tradeId);
		User sender = trade.getSender();
		User receiver = trade.getRecipient();
		Message message = null;
		String msg = null;
		if(result == null){
			System.out.println("Result not passed in on RespondToTrade servlet, returning");
			getServletContext().getRequestDispatcher("/WEB-INF/pages/Notifications.jsp").forward(request, response);
			return;
		} else {
			if(result == "true") {
				//accept trade
				//Send message to sender of trade that it was accepted
				msg = receiver.getName() + " has accepted your trade. You will receive '" + trade.getRecipientBook().getTitle() + 
					  "' and they will receive '" + trade.getSenderBook().getTitle() + "'.";
				message = new Message(trade.getSender(), msg);
				dbw.addMessage(message);
				//Delete trade
				dbw.removeTradeById(tradeId);
				//Remove both books from database
				dbw.deleteBook(trade.getSenderBook().getId());
				dbw.deleteBook(trade.getRecipientBook().getId());
			} else {
				//decline trade
				//Send message to sender of trade that it was declined
				msg = receiver.getName() + " has declined your trade.";
				message = new Message(trade.getSender(), msg);
				dbw.addMessage(message);
				//Delete trade
				dbw.removeTradeById(tradeId);
				//Remove both books from database
				dbw.deleteBook(trade.getSenderBook().getId());
				dbw.deleteBook(trade.getRecipientBook().getId());
			}			
		}
		getServletContext().getRequestDispatcher("/WEB-INF/pages/Notifications.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
