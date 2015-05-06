package Controller;

import java.io.IOException;
import java.util.Iterator;
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
import VO.GameSubCategoryVO;

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
			if(flag.equals("loadCategories"))
				loadCategories(request, response);
			else if(flag.equals("insert"))
				insert(request, response);
			else if(flag.equals("loadSubCategories"))
				loadSubCategories(request, response);
			else if(flag.equals("edit"))
				edit(request, response);
			else if(flag.equals("update"))
				update(request, response);
			else if(flag.equals("delete"))
				delete(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void loadCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<GameCategoryVO> ls = AddCategoryDAO.showAll();
		HttpSession session = request.getSession();
		session.setAttribute("load", ls);
		response.sendRedirect(request.getContextPath()+"/Admin/addSubCategory.jsp");
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("cat_id") != null){
			long cat_id = Long.parseLong(request.getParameter("cat_id"));
			String scat_name = request.getParameter("scat_name");
			String scat_description = request.getParameter("scat_description");
			AddSubCategoryDAO.insert(new GameCategoryVO(cat_id), scat_name, scat_description);
			session.setAttribute("msg", "The subcategory has been added successfully.");
		}
		else{
			session.setAttribute("msg", "Please select the category.");
		}
		response.sendRedirect(request.getContextPath()+"/Admin/addSubCategory.jsp");
	}
	
	protected void loadSubCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<GameSubCategoryVO> ls = AddSubCategoryDAO.showAll();
		
/*		Iterator<GameSubCategoryVO> itr = ls.iterator();
		while (itr.hasNext()) {
			GameSubCategoryVO gameSubCategoryVO = (GameSubCategoryVO) itr.next();
			System.out.println(gameSubCategoryVO.getCat_id().getCat_id()+" "+gameSubCategoryVO.getScat_name());
		}
*/		
		HttpSession session = request.getSession();
		session.setAttribute("subCategoryList", ls);
		response.sendRedirect(request.getContextPath()+"/Admin/editSubCategory.jsp");
	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long cat_id = Integer.MIN_VALUE;
		long scat_id = Long.parseLong(request.getParameter("scat_id"));
		List<GameSubCategoryVO> ls = AddSubCategoryDAO.edit(scat_id);	//scat_id, gcvoObj, scat_name, scat_description in ls
		Iterator<GameSubCategoryVO> itr = ls.iterator();
		while (itr.hasNext()) {		//Assuming only one entry is returned which should be the case
			GameSubCategoryVO gscvo = (GameSubCategoryVO) itr.next();
			cat_id = gscvo.getCat_id().getCat_id();
		}
		List<GameCategoryVO> categoryDetails = AddCategoryDAO.edit(cat_id);	//Query = SELECT * FROM GCVO where cat_id = cat_id
		//categoryList = cat_id, cat_name, cat_description
		HttpSession session = request.getSession();
		session.setAttribute("subcategory2bedited", ls);
		session.setAttribute("categorydetails", categoryDetails);
		response.sendRedirect(request.getContextPath()+"/Admin/addSubCategory.jsp");
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long scat_id = Long.parseLong(request.getParameter("scat_id")),
			 cat_id = Long.parseLong(request.getParameter("cat_id"));
		
		String scat_name = request.getParameter("scat_name"),
			   scat_description = request.getParameter("scat_description");
		
		GameCategoryVO gcvo = new GameCategoryVO();
		gcvo.setCat_id(cat_id);
		
		AddSubCategoryDAO.update(gcvo, scat_id, scat_name, scat_description);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The subcategory is successfully updated.");
		response.sendRedirect(request.getContextPath()+"/Admin/addSubCategory.jsp");
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long scat_id = Long.parseLong(request.getParameter("scat_id"));
		AddSubCategoryDAO.delete(scat_id);
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The subcategory is successfully deleted.");
		loadSubCategories(request, response);
	}
};