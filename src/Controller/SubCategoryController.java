package Controller;

import java.io.IOException;
import java.util.List;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.AddCategoryDAO;
import Model.AddSubCategoryDAO;
import VO.GameCategoryVO;

/**
 * Servlet implementation class SubCategoryController
 */
@WebServlet("/SubCategoryController")
public class SubCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubCategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");
		System.out.println("the flag is "+flag);
		if(flag != null){
			if(flag.equals("load"))
				load(request, response);
			else if(flag.equals("insert"))
				insert(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<GameCategoryVO> ls = AddCategoryDAO.showAll();
//		request.setAttribute("load", ls);
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/addSubCategory.jsp");
//		rd.forward(request, response);
		HttpSession sess = request.getSession();
		sess.setAttribute("load", ls);
		response.sendRedirect(request.getContextPath()+"/Admin/addSubCategory.jsp");
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long cat_id = Long.parseLong(request.getParameter("cat_id"));
		String scat_name = request.getParameter("scat_name");
		String scat_description = request.getParameter("scat_description");
		GameCategoryVO gcvo = new GameCategoryVO();
		gcvo.setCat_id(cat_id);
		System.out.println(cat_id);
		System.out.println(scat_name);
		System.out.println(scat_description);
		AddSubCategoryDAO.insert(gcvo, scat_name, scat_description);
//		request.setAttribute("msg", "The subcategory has been added successfully.");
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/addSubCategory.jsp");
//		rd.forward(request, response);
		HttpSession sess = request.getSession();
		sess.setAttribute("msg", "The subcategory has been added successfully.");
		response.sendRedirect(request.getContextPath()+"/Admin/addSubCategory.jsp");
	}
};