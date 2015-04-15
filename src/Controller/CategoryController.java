package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.AddCategoryDAO;


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
		String cat_name = request.getParameter("cat_name").trim();
		String cat_description = request.getParameter("cat_description").trim().replaceAll("\n", " ").replaceAll("\r",".");
		System.out.println(cat_name);
		System.out.println(cat_description);
		AddCategoryDAO.insert(cat_name, cat_description);
//		request.setAttribute("msg", "The category has been added successfully.");
//		getServletContext().getRequestDispatcher("/Admin/addCategory.jsp").forward(request, response);
		HttpSession sess = request.getSession(true);
		sess.setAttribute("msg", "The category has been added successfully.");
		response.sendRedirect(request.getContextPath()+"/Admin/addCategory.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String flag = request.getParameter("flag");
	}

}
