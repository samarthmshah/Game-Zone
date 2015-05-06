package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
				loadMyOrders(request, response);
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
	
	protected void loadSellerOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
