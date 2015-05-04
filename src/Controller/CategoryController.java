package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.AddCategoryDAO;
import VO.GameCategoryVO;


/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {
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
//		String flag = request.getParameter("flag");
	}
	
	protected void load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GameCategoryVO> ls = AddCategoryDAO.showAll();
		HttpSession session = request.getSession();
		session.setAttribute("categoryList", ls);
		response.sendRedirect(request.getContextPath()+"/Admin/editCategory.jsp");
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cat_name = request.getParameter("cat_name").trim();
		String cat_description = request.getParameter("cat_description").trim().replaceAll("\n", " ").replaceAll("\r",".");
		System.out.println(cat_name);
		System.out.println(cat_description);
		AddCategoryDAO.insert(cat_name, cat_description);
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The category has been added successfully.");
		response.sendRedirect(request.getContextPath()+"/Admin/addCategory.jsp");
	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long cat_id = Long.parseLong(request.getParameter("cat_id"));
		List<GameCategoryVO> ls = AddCategoryDAO.edit(cat_id);
		if(ls != null){
			HttpSession session = request.getSession();
			session.setAttribute("category2bedited", ls);
			response.sendRedirect(request.getContextPath()+"/Admin/addCategory.jsp");
		}
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long cat_id = Long.parseLong(request.getParameter("cat_id"));
		String cat_name = request.getParameter("cat_name"),
			   cat_description = request.getParameter("cat_description");
		AddCategoryDAO.update(cat_id, cat_name, cat_description);
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The category is successfully updated.");
		response.sendRedirect(request.getContextPath()+"/Admin/addCategory.jsp");
	}
		
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long cat_id = Long.parseLong(request.getParameter("cat_id"));
		AddCategoryDAO.delete(cat_id);
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The category is successfully deleted.");
		load(request, response);
	}
};