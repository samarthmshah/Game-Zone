package Controller;

import java.io.IOException;
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
import VO.BuyerVO;
import VO.CartVO;
import VO.GameVO;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
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
			if(flag.equals("deleteFromCart"))
				deleteFromCart(request, response);
			if(flag.equals("loadCart"))
				loadCart(request, response);
			if(flag.equals("clearCart"))
				clearCart(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");
		if(flag != null){
			if(flag.equals("insert"))
				insert(request, response);
			if(flag.equals("updateCartItem"))
				updateCartItem(request, response);
		}
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long buyer_id = Long.parseLong(request.getParameter("buyer_id")),
			 game_id = Long.parseLong(request.getParameter("game_id"));
		int game_quantity = Integer.parseInt(request.getParameter("game_quantity"));
		long cart_addedDT = new Date().getTime();
		HttpSession session = request.getSession();
		
		List<CartVO> ls = CartDAO.checkIfGameAlreadyInCart(buyer_id, game_id);
		// If it exists already, then simply add the quantity!
		if(ls!=null && ls.size()>0){
			Iterator<CartVO> itr = ls.iterator();
			while(itr.hasNext()){
				CartVO previous = itr.next();
				long cart_id = previous.getCart_id();
				game_quantity += previous.getGame_quantity();
				CartDAO.update(cart_id, game_quantity);
				session.setAttribute("msg", "The Game has been successfully updated in your shopping cart");
			}
		}
		
		else{
			CartDAO.insert(new CartVO(new BuyerVO(buyer_id), new GameVO(game_id), game_quantity, cart_addedDT));
			session.setAttribute("msg", "The Game has been successfully added to your shopping cart");
		}
		
		response.sendRedirect(request.getContextPath()+"/GameController?flag=showAllGames&userType=buyer");
	}

	protected void deleteFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long cart_id = Long.parseLong(request.getParameter("cart_id"));
		CartVO cvo = new CartVO(cart_id);
		CartDAO.delete(cvo);
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The Game has been successfully removed from your shopping cart");
		loadCart(request, response);
	}
	
	@SuppressWarnings("rawtypes")
	protected void loadCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long buyer_id = Long.parseLong(request.getParameter("buyer_id"));
		List myCart = CartDAO.loadCart(buyer_id);
		HttpSession session = request.getSession();
		session.setAttribute("myCart", myCart);
		response.sendRedirect(request.getContextPath()+"/Seller_Buyer/buyer_shoppingCart.jsp");
	}
	
	protected void updateCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long cart_id = Long.parseLong(request.getParameter("cart_id"));
		int game_quantity = Integer.parseInt(request.getParameter("quantity"));
		
		CartDAO.update(cart_id, game_quantity);
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The cart has been updated successfully");
		loadCart(request, response);
	}
	
	protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long buyer_id = Long.parseLong(request.getParameter("buyer_id"));
		CartDAO.deleteByBuyerID(buyer_id);
		HttpSession session = request.getSession();
		session.setAttribute("msg", "Cart successfully cleared");
		loadCart(request, response);
	}
};
