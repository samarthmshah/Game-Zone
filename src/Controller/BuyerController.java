package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BuyerDAO;
import VO.BuyerVO;

/**
 * Servlet implementation class Registration_BuyerServlet
 */
@WebServlet("/BuyerController")
public class BuyerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");
		if(flag != null && flag.equals("insert"))
			insert(request, response);
		}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstname"),
				   lastName = request.getParameter("lastname"),
				   username = request.getParameter("username"),
				   password = request.getParameter("password"),
				   email = request.getParameter("emailid"),
				   phNo = request.getParameter("phno"),
				   dob = request.getParameter("dob"),
				   address = request.getParameter("address"),
				   zip = request.getParameter("zip"),
				   paypal = request.getParameter("paypal"),
				   url = "/Seller_Buyer/registration_buyer.jsp",
				   msg = "";
			boolean isAvailable = false;
			
			if(zip == null) zip = "";
			
			isAvailable = BuyerDAO.checkUsernameAvailability(username);
			if(isAvailable){
				BuyerVO bvo = new BuyerVO(firstName, lastName, username, password, email, phNo, dob, address, zip, paypal, 0);
				BuyerDAO.insert(bvo);
				msg = "Account created successfully";
				url = "/Seller_Buyer/login.jsp";
			}
			else
				msg = "Username already taken.";
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath()+url);
	}
};