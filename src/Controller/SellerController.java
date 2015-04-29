package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.SellerDAO;
import VO.SellerVO;

/**
 * Servlet implementation class SellerController
 */
@WebServlet("/SellerController")
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerController() {
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
		String companyName = request.getParameter("companyname"),
			   firstName = request.getParameter("firstname"),
			   lastName = request.getParameter("lastname"),
			   username = request.getParameter("username"),
			   password = request.getParameter("password"),
			   email = request.getParameter("emailid"),
			   phNo = request.getParameter("phno"),
			   dob = request.getParameter("dob"),
			   address = request.getParameter("address"),
			   zip = request.getParameter("zip"),
			   routingNumber = request.getParameter("routingnumber"),
			   accountNumber = request.getParameter("accountnumber"),
			   paypal = request.getParameter("paypal"),
			   url = "/Seller_Buyer/registration_seller.jsp",
			   msg = "";
			boolean isAvailable = false;
			
			if(zip == null) zip = "";
			
			isAvailable = SellerDAO.checkUsernameAvailability(username);
			if(isAvailable){
				SellerVO svo = new SellerVO(companyName, firstName, lastName, username, password, email, phNo, dob, address, zip, 
											routingNumber, accountNumber, paypal, 0);
				SellerDAO.insert(svo);
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
