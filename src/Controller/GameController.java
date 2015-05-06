package Controller;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Model.AddCategoryDAO;
import Model.AddSubCategoryDAO;
import Model.GameDAO;
import VO.GameCategoryVO;
import VO.GameSubCategoryVO;
import VO.GameVO;
import VO.SellerVO;

/**
 * Servlet implementation class GameController
 */
@WebServlet("/GameController")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameController() {
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
			if(flag.equals("loadCatAndScat"))
				loadCategories(request, response);
			else if(flag.equals("loadScatDynamically"))
				loadScatDynamically(request, response);	
			else if(flag.equals("showAllGamesFromSeller"))
				showAllGamesFromSeller(request, response);
			else if(flag.equals("showAllGames"))
				showAllGames(request, response);
			else if(flag.equals("productPage"))
				productPage(request, response);
			else if(flag.equals("editGame"))
				editGame(request, response);
			else if(flag.equals("deleteGame"))
				deleteGame(request, response);
			else if(flag.equals("showGamesByCat"))
				showGamesByCat(request, response);
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
			if(flag.equals("update"))
				update(request, response);
			else if(flag.equals("search"))
				search(request, response);
		}
	}
	
	protected void loadCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GameCategoryVO> categoryList = AddCategoryDAO.showAll();
		HttpSession session = request.getSession();
		session.setAttribute("categoryList", categoryList);
		response.sendRedirect(request.getContextPath()+"/Seller_Buyer/seller_addGame.jsp");
	}
//	
//	protected void insert(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		long seller_id = Long.parseLong(request.getParameter("seller_id"));
//		long cat_id = Long.parseLong(request.getParameter("cat_id"));
//
//		long scat_id = Long.parseLong(request.getParameter("scat_id"));
//		GameSubCategoryVO gscvo = null;
//		if (scat_id == 0) { // The user didn't choose
//			HttpSession session = request.getSession();
//			session.setAttribute("msg", "Please choose a subcategory!");
//		} 
//		else {
//			if (scat_id == -1)
//				gscvo = null;
//			else
//				gscvo = new GameSubCategoryVO(scat_id);
//
//			String game_poster_name = request.getParameter("game_poster_name");
//			if (game_poster_name == null)
//				game_poster_name = "no_image_available.png";
//
//			String game_console = request.getParameter("game_console"), game_name = request
//					.getParameter("game_name");
//			double game_price = Double.parseDouble(request.getParameter("game_price"));
//			double game_shipping_charges = Double.parseDouble(request.getParameter("game_shipping_charges"));
//			int game_stock = Integer.parseInt(request.getParameter("game_stock"));
//			String game_youtube_url = request.getParameter("game_youtube_url"), 
//				   game_description = request.getParameter("game_description");
//			
//
//			GameVO gvo = new GameVO(new SellerVO(seller_id), game_poster_name,
//									new GameCategoryVO(cat_id), gscvo, game_console, game_name,
//									game_price, game_shipping_charges, game_stock, game_youtube_url, game_description, 0);
//			GameDAO.insert(gvo);
//			HttpSession session = request.getSession();
//			session.removeAttribute("fileName");
//			session.setAttribute("msg",
//					"The game was successfully added to the list of your products");
//		}
//		response.sendRedirect(request.getContextPath()+ "/Seller_Buyer/seller_addGame.jsp");
//	}
	
	protected void insert(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long seller_id = Long.parseLong(request.getParameter("seller_id"));
		long cat_id = Long.parseLong(request.getParameter("cat_id"));

		long scat_id = Long.parseLong(request.getParameter("scat_id"));
		GameSubCategoryVO gscvo = null;
		if (scat_id == 0) { // The user didn't choose
			HttpSession session = request.getSession();
			session.setAttribute("msg", "Please choose a subcategory!");
		} 
		else {
			if (scat_id == -1)
				gscvo = null;
			else
				gscvo = new GameSubCategoryVO(scat_id);

			String game_poster_name = request.getParameter("game_poster_name");
			if (game_poster_name == null)
				game_poster_name = "no_image_available.png";

			String game_console = request.getParameter("game_console"), 
					game_name = request.getParameter("game_name");
			
			HttpSession session = request.getSession();
			String message = new String();
			double game_price = 0d;
			int game_price_flag = 0;
			try{
				game_price = Double.parseDouble(request.getParameter("game_price"));
				game_price_flag = 1;
				session.setAttribute("game_price", game_price);
			}
			catch(NumberFormatException exception)
			{
				if(message.equals(""))
						message ="Please Enter Numerical Price";
				else message+= ", Numerical Price";
			}
			
			int game_stock =0;
			int game_stock_flag =0;
			try{
				game_stock = Integer.parseInt(request.getParameter("game_stock"));
				game_stock_flag = 1;
			}
			catch(NumberFormatException exception)
			{
				if(message.equals(""))
					message ="Please Enter Numerical Stock";
				else message+= ", Numerical Stock";
			}
			
			double game_shipping_charges = 0d;
			int game_shipping_charges_flag = 0;
			try{
				game_shipping_charges = Double.parseDouble(request.getParameter("game_shipping_charges"));
				game_shipping_charges_flag = 1;
			}
			catch(NumberFormatException exception)
			{
				if(message.equals(""))
					message ="Please Enter Numerical Shipping Charges";
				else message+= ", Numerical Shipping Charges";
			}
			
			String game_youtube_url = request.getParameter("game_youtube_url"), 
				   game_description = request.getParameter("game_description");
			
			if(game_price_flag == 1 && game_shipping_charges_flag == 1 && game_stock_flag == 1)
			{
				GameVO gvo = new GameVO(new SellerVO(seller_id), game_poster_name,
									new GameCategoryVO(cat_id), gscvo, game_console, game_name,
									game_price, game_shipping_charges, game_stock, game_youtube_url, game_description, 0, new Date().getTime());
				GameDAO.insert(gvo);
				message = "The game was successfully added to the list of your products";
			}
			
			session.removeAttribute("fileName");
			session.setAttribute("msg", message);
		}
		response.sendRedirect(request.getContextPath()+ "/Seller_Buyer/seller_addGame.jsp");
	}
	
	protected void loadScatDynamically(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonString; 
		long cat_id = Long.parseLong(request.getParameter("cat_id"));
		List<GameSubCategoryVO> ls = AddSubCategoryDAO.getTuplesByCatID(cat_id);
		
		if(!ls.isEmpty()){
			Iterator<GameSubCategoryVO> itr = ls.iterator();
			
			Map<Long, String> subcategories = new LinkedHashMap<Long, String>();	// We need to maintain order.
			
			subcategories.put(0L, "Select a subcategory");
			
			while(itr.hasNext()){
				GameSubCategoryVO gscvo = itr.next();
				subcategories.put(gscvo.getScat_id(), gscvo.getScat_name());
			}
			jsonString = new Gson().toJson(subcategories);	// Converts java object to json objects.
		}
		else{
			Map<Long, String> nothing = new LinkedHashMap<Long, String>();	// We need to maintain order.
			nothing.put(-1L, "No subcategories found");
			jsonString = new Gson().toJson(nothing);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);		// This does all the work
	}
	
	protected void showAllGamesFromSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long seller_id = Long.parseLong(request.getParameter("seller_id"));
		List<GameVO> gameList = GameDAO.getGamesBySeller_id(seller_id);
		List<GameCategoryVO> categoryList = AddCategoryDAO.showAll();
		HttpSession session = request.getSession();
		session.setAttribute("gameList", gameList);
		session.setAttribute("categoryList", categoryList);
		response.sendRedirect(request.getContextPath()+"/Seller_Buyer/seller_viewGamesBySeller.jsp");
	}
	
//	protected void showAllGames(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<GameVO> allGames = GameDAO.showAll();
//		HttpSession session = request.getSession();
//		session.setAttribute("gameList", gameList);
//		response.sendRedirect(request.getContextPath()+"/Seller_Buyer/seller_viewGames.jsp");
//	}
	
	protected void productPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long game_id = Long.parseLong(request.getParameter("game_id"));
		List<GameVO> gameInfo = GameDAO.getGameByGame_id(game_id);
		HttpSession session = request.getSession();
		session.setAttribute("gameInfo", gameInfo);
		response.sendRedirect(request.getContextPath()+"/Seller_Buyer/seller_gamePage.jsp");
	}
	
	protected void editGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long game_id = Long.parseLong(request.getParameter("game_id"));
		String userType = request.getParameter("userType"),
			   url = "";
		List<GameVO> game2bedited = GameDAO.getGameByGame_id(game_id);
		List<GameCategoryVO> categoryList = AddCategoryDAO.showAll();
		HttpSession session = request.getSession();
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("game2bedited", game2bedited);
		
		if(userType.equals("seller")){
			url = request.getContextPath()+"/Seller_Buyer/seller_updateGame.jsp";
		}
		else
			url = request.getContextPath()+"/Admin/updateGame.jsp";

		response.sendRedirect(url);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long game_id = Long.parseLong(request.getParameter("game_id"));
		long seller_id = Long.parseLong(request.getParameter("seller_id"));
		long cat_id = Long.parseLong(request.getParameter("cat_id"));

		long scat_id = Long.parseLong(request.getParameter("scat_id"));
		GameSubCategoryVO gscvo = null;
		if (scat_id == 0) { // The user didn't choose
			HttpSession session = request.getSession();
			session.setAttribute("msg", "Please choose a subcategory!");
		} 
		else {
			if (scat_id == -1)
				gscvo = null;
			else
				gscvo = new GameSubCategoryVO(scat_id);

			String game_poster_name = request.getParameter("game_poster_name");
			if (game_poster_name == null) game_poster_name = "no_image_available.png";

			String game_console = request.getParameter("game_console"), 
				   game_name = request.getParameter("game_name");
			double game_price = Double.parseDouble(request.getParameter("game_price"));
			double game_shipping_charges = Double.parseDouble(request.getParameter("game_shipping_charges"));
			int game_stock = Integer.parseInt(request.getParameter("game_stock"));
			String game_youtube_url = request.getParameter("game_youtube_url"), 
				   game_description = request.getParameter("game_description");
			
			GameVO gvo = new GameVO();
			gvo.setGame_id(game_id);
			gvo.setCat_id(new GameCategoryVO(cat_id));
			gvo.setGame_console(game_console);
			gvo.setGame_description(game_description);
			gvo.setGame_name(game_name);
			gvo.setGame_poster_name(game_poster_name);
			gvo.setGame_price(game_price);
			gvo.setGame_ratings(0);
			gvo.setGame_shipping_charges(game_shipping_charges);
			gvo.setGame_stock(game_stock);
			gvo.setGame_youtube_url(game_youtube_url);
			gvo.setScat_id(gscvo);
			gvo.setSeller_id(new SellerVO(seller_id));
			GameDAO.update(gvo);
			HttpSession session = request.getSession();
			session.removeAttribute("fileName");
			session.setAttribute("msg", "The game was successfully updated");
		}
		response.sendRedirect(request.getContextPath()+ "/Seller_Buyer/seller_updateGame.jsp");
	}
	
	protected void deleteGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long game_id = Long.parseLong(request.getParameter("game_id"));
		GameDAO.delete(game_id);
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The Game has been successfully deleted");
		showAllGamesFromSeller(request, response);
	}
		
	protected void showAllGames(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userType = request.getParameter("userType"),
				url = "";
		// All the games
		List<GameVO> allGames = GameDAO.showAll();
		List<GameCategoryVO> categoryList = AddCategoryDAO.showAll();
		HttpSession session = request.getSession();
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("allGames", allGames);
		
		if(userType.equals("seller")){
			url = request.getContextPath()+"/Seller_Buyer/seller_viewAllGames.jsp";
		}
		else if(userType.equals("buyer")){
			url = request.getContextPath()+"/Seller_Buyer/buyer_viewAllGames.jsp";
		}
		else
			url = request.getContextPath()+"/Admin/edit_games.jsp";

		response.sendRedirect(url);
	}
		
	protected void showGamesByCat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long cat_id = Long.parseLong(request.getParameter("cat_id"));
		String userType = request.getParameter("userType"),
			   url = "";
		
		List<GameVO> gamesByCat = GameDAO.getGamesByCat_id(cat_id);
		List<GameCategoryVO> categoryList = AddCategoryDAO.showAll();
		HttpSession session = request.getSession();

		session.setAttribute("categoryList", categoryList);
		session.setAttribute("gamesByCat", gamesByCat);
		
		if(userType.equals("seller")){
			url = request.getContextPath()+"/Seller_Buyer/seller_viewGamesByCat.jsp";
		}
		else if(userType.equals("buyer")){
			url = request.getContextPath()+"/Seller_Buyer/buyer_viewGamesByCat.jsp";
		}
		response.sendRedirect(url);
	}
	
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputString = request.getParameter("search"),
			   userType = request.getParameter("userType"),
			   url = "";
		List<GameVO> searchResults = GameDAO.search(inputString);

		List<GameCategoryVO> categoryList = AddCategoryDAO.showAll();
		
		HttpSession session = request.getSession();
		
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("searchResults", searchResults);
		
		if(userType.equals("buyer")){
			url = request.getContextPath()+"/Seller_Buyer/buyer_searchResults.jsp";
		}
		else if (userType.equals("seller")) {
			url = request.getContextPath()+"/Seller_Buyer/seller_searchResults.jsp";
		}
		else {
			url = request.getContextPath()+"/Admin/admin_searchResults.jsp";
		}
		response.sendRedirect(url);
	}
};
