package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.CartDAO;
import Model.GameDAO;
import Model.OrderDAO;
import VO.BuyerVO;
import VO.CartVO;
import VO.GameVO;
import VO.OrderVO;
import VO.SellerVO;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");
		if(flag != null){
			if(flag.equals("loadMyOrders"))
				loadMyOrders(request, response);
			if(flag.equals("loadSellerOrders"))
				loadSellerOrders(request, response);
			if(flag.equals("cancel"))
				cancelOrder(request, response);
			if(flag.equals("setShipped"))
				setShipped(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");
		System.out.println("flag="+flag);
		if(flag != null){
			if(flag.equals("insert"))
				insert(request, response);
			if(flag.equals("loadCheckout"))
				loadCheckout(request, response);
		}
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long buyer_id = Long.parseLong(request.getParameter("buyer_id"));
		double order_total_cost = Double.parseDouble(request.getParameter("total_overall_amt"));
		String order_shipping_address = request.getParameter("shipping_address");
		GameVO gvo=null;
		SellerVO svo = null;
		int quantity=0;
		String orderDT = new SimpleDateFormat("MM/dd/yyyy").format(new Date()),
			   shippingDT = "";
		int order_status = 0; // Not shipped yet
		
		List<CartVO> buyerCart = CartDAO.getCartByBuyerID(buyer_id);
		Iterator<CartVO> itr = buyerCart.iterator();
		while(itr.hasNext()){
			CartVO cvo = itr.next();
			gvo = cvo.getGame_id();
			quantity = cvo.getGame_quantity();
			OrderDAO.insert(new OrderVO(new BuyerVO(buyer_id), gvo, quantity, orderDT, shippingDT, 
										order_status, order_total_cost, order_shipping_address));
			GameDAO.reduceStock(gvo.getGame_id(), quantity);	// Reduce the stock as well.
			svo = gvo.getSeller_id();
			sendOrderNotification(svo, gvo, cvo);
			
		}
		CartDAO.deleteByBuyerID(buyer_id); 	// Safe to clear the cart now.
		
		
		// Send email to all the sellers pertaining to this order
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", "Your order has been placed successfully");
		loadMyOrders(request, response);
	}
	
	@SuppressWarnings("rawtypes")
	protected void loadCheckout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long buyer_id = Long.parseLong(request.getParameter("buyer_id"));
		List myCart = CartDAO.loadCart(buyer_id);
		HttpSession session = request.getSession();
		session.setAttribute("myCart", myCart);
		response.sendRedirect(request.getContextPath()+"/Seller_Buyer/buyer_checkout.jsp");
	}
	
	protected void loadMyOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long buyer_id = Long.parseLong(request.getParameter("buyer_id"));
		List<OrderVO> myOrders = OrderDAO.getOrdersByBuyerID(buyer_id);
		HttpSession session = request.getSession();
		session.setAttribute("myOrders", myOrders);
		response.sendRedirect(request.getContextPath()+"/Seller_Buyer/buyer_viewOrders.jsp");
	}
	
	@SuppressWarnings("rawtypes")
	protected void loadSellerOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long seller_id = Long.parseLong(request.getParameter("seller_id"));
		List sellerOrders = OrderDAO.getOrdersBySellerID(seller_id);
		HttpSession session = request.getSession();
		session.setAttribute("sellerOrders", sellerOrders);
		response.sendRedirect(request.getContextPath()+"/Seller_Buyer/seller_viewOrders.jsp");
	}
	
	protected void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long order_id = Long.parseLong(request.getParameter("order_id"));
		int result = OrderDAO.cancelOrder(order_id);
		String userType = request.getParameter("userType");
		HttpSession session = request.getSession();
		if(result>0)
			session.setAttribute("msg", "Order cancelled successfully");
		else
			session.setAttribute("msg", "Couldn't cancel order");
		
		if(userType.equals("buyer"))
			loadMyOrders(request, response);
		else
			loadSellerOrders(request, response);
	}
	
	protected void setShipped(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long order_id = Long.parseLong(request.getParameter("order_id"));
		int result = OrderDAO.setShipped(order_id);
		HttpSession session = request.getSession();
		if(result>0)
			session.setAttribute("msg", "Order shipped successfully");
		else
			session.setAttribute("msg", "Couldn't ship order");
		loadSellerOrders(request, response);
	}
	
	private void sendOrderNotification(SellerVO svo, GameVO gvo, CartVO cvo)
	{    
		// Sender's email ID
	    final String FROM = "developers.gamezone@gmail.com";
	    String USERNAME = "developers.gamezone@gmail.com";
	    String PASSWORD = "samarthshah";
	    final String HOST = "smtp.gmail.com";

	    // Get system properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", "587");
	    
        // Get the default Session object.
        Session session = Session.getInstance(props,
        		new javax.mail.Authenticator() {
      		         protected PasswordAuthentication getPasswordAuthentication() {
      		            return new PasswordAuthentication(USERNAME, PASSWORD);
      		         }
	    });
        
        String username = svo.getUsername();
        String TO = svo.getEmail();
	    try{
	    	// Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
			message.setFrom(new InternetAddress(FROM));

	         // Set To: header field of the header.
	         message.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		         
	        // Set Subject: header field
	         message.setSubject("Someone purchased your product!");
	         message.setContent("<h1>Dear "+username+",</h1>"
								+ "<p>Someone just purchased your product.</p>"
								+ "<table border=\"1\" style=\" border-style: dotted; \"> "
								+ "<thead> <tr> <th>Game</th> <th>MRP</th> <th>Buyer Info</th> <th>Quantity</th> </tr></thead>"
								+ "<tbody>"
								+ "<tr>"
								+ "<td>"+ gvo.getGame_name() + "</td>"
								+ "<td> $ "+ gvo.getGame_price() + "</td>"
								+ "<td>"+ cvo.getBuyer_id().getFirstname() +" "+ cvo.getBuyer_id().getLastname() +"</td>"
								+ "<td>"+ cvo.getGame_quantity() + "</td>"
								+ "</tr></tbody></table>"
								+ "<br/><p>Happy Gaming.<br/>"
								+ "<strong>Game-Zone Team</strong></p>", "text/html");
	         message.saveChanges();
	         Transport.send(message); // Send message
	         System.out.println("Sent message successfully!");
	      }
	    
	    catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }
	
}
