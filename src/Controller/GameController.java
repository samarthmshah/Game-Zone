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
import VO.GameCategoryVO;
import VO.GameSubCategoryVO;

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
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long cat_id = Long.parseLong(request.getParameter("cat_id")); 
//		long scat_id = Long.parseLong(request.getParameter("scat_id"));
		String game_poster_name = request.getParameter("game_poster_name"),
			   game_console = request.getParameter("game_console"),
			   game_name = request.getParameter("game_name");
		long game_price = Long.parseLong(request.getParameter("game_price")),
			 game_stock = Long.parseLong(request.getParameter("game_stock"));
		String game_youtube_url = request.getParameter("game_youtube_url"),
			   game_description = request.getParameter("game_description");
		System.out.println(cat_id);
//		System.out.println(scat_id);
		System.out.println(game_poster_name);
		System.out.println(game_name);
		System.out.println(game_console);
		System.out.println(game_price);
		System.out.println(game_stock);
		System.out.println(game_youtube_url);
		System.out.println(game_description);
		/// If gameposter is null then noimagefound
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
			nothing.put(0L, "No subcategories found");
			jsonString = new Gson().toJson(nothing);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);		// This does all the work
	}
};
