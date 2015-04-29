package Controller;

import java.io.IOException;
import java.util.List;

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
		String flag = request.getParameter("flag");
		if(flag != null){
			if(flag.equals("load"))
				load(request, response);
			else if(flag.equals("approve"))
				approve(request, response);
			else if(flag.equals("decline"))
				decline(request, response);
			else if(flag.equals("delete"))
				deleteSeller(request, response);
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
			else if(flag.equals("message"))
				message(request, response);
		}
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
	
	protected void load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SellerVO> ls = SellerDAO.showAll();
		HttpSession session = request.getSession();
		session.setAttribute("sellerList", ls);
		response.sendRedirect(request.getContextPath()+"/Admin/"+request.getParameter("url"));
	}
	
	protected void approve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long seller_id = Long.parseLong(request.getParameter("seller_id"));
		SellerDAO.updateStatus(seller_id, "approve");
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The seller has been successfully approved.");
		load(request, response);
	}
	
	protected void decline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long seller_id = Long.parseLong(request.getParameter("seller_id"));
		SellerDAO.updateStatus(seller_id, "decline");
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The seller has been declined.");
		load(request, response);
	}
	
	protected void deleteSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long seller_id = Long.parseLong(request.getParameter("seller_id"));
		SellerDAO.deleteSeller(seller_id);
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The seller has been deleted successfully.");
		load(request, response);
	}
	
	protected void message(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("seller_id") != null){
			long seller_id = Long.parseLong(request.getParameter("seller_id"));
			String subject = request.getParameter("subject"),
				   message = request.getParameter("message");
			
			System.out.println("ID "+seller_id);
			System.out.println("Subject "+subject);
			System.out.println("Message "+message);
			HttpSession session = request.getSession();
			session.setAttribute("msg", "The message has been sent successfully.");
		}
		else{
			HttpSession session = request.getSession();
			session.setAttribute("msg", "Please select a seller first!");
		}
		response.sendRedirect(request.getContextPath()+"/Admin/contact_sellers.jsp");
	}
};
