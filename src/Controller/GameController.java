package Controller;

import java.io.IOException;
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
				loadCatAndScat(request, response);
			if(flag.equals("loadScatDynamically"))
				loadScatDynamically(request, response);
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
		}
	}
	
	protected void loadCatAndScat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GameCategoryVO> categoryList = AddCategoryDAO.showAll();
		List<GameSubCategoryVO> subCategoryList = AddSubCategoryDAO.showAll();
		HttpSession sess = request.getSession();
		sess.setAttribute("categoryList", categoryList);
		sess.setAttribute("subategoryList", subCategoryList);
		response.sendRedirect(request.getContextPath()+"/Seller_Buyer/seller_addGame.jsp");
	}
	
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

			String game_console = request.getParameter("game_console"), game_name = request
					.getParameter("game_name");
			double game_price = Double.parseDouble(request.getParameter("game_price"));
			double game_shipping_charges = Double.parseDouble(request.getParameter("game_shipping_charges"));
			int game_stock = Integer.parseInt(request.getParameter("game_stock"));
			String game_youtube_url = request.getParameter("game_youtube_url"), 
				   game_description = request.getParameter("game_description");
			

			GameVO gvo = new GameVO(new SellerVO(seller_id), game_poster_name,
									new GameCategoryVO(cat_id), gscvo, game_console, game_name,
									game_price, game_shipping_charges, game_stock, game_youtube_url, game_description);
			GameDAO.insert(gvo);
			HttpSession session = request.getSession();
			session.removeAttribute("fileName");
			session.setAttribute("msg",
					"The game was successfully added to the list of your products");
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
};
