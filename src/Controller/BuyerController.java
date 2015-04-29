package Controller;

import java.io.IOException;
import java.util.List;

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
		String flag = request.getParameter("flag");
		if(flag != null){
			if(flag.equals("load"))
				load(request, response);
			else if(flag.equals("approve"))
				approve(request, response);
			else if(flag.equals("decline"))
				decline(request, response);
			else if(flag.equals("delete"))
				deleteBuyer(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");
		if(flag != null && flag.equals("insert"))
			insert(request, response);
		else if(flag.equals("message"))
			message(request, response);
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
	
	protected void load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BuyerVO> ls = BuyerDAO.showAll();
		HttpSession session = request.getSession();
		session.setAttribute("buyerList", ls);
		response.sendRedirect(request.getContextPath()+"/Admin/"+request.getParameter("url"));
	}
	
	protected void approve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long buyer_id = Long.parseLong(request.getParameter("buyer_id"));
		BuyerDAO.updateStatus(buyer_id, "approve");
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The buyer has been successfully approved.");
		load(request, response);
	}
	
	protected void decline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long buyer_id = Long.parseLong(request.getParameter("buyer_id"));
		BuyerDAO.updateStatus(buyer_id, "decline");
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The buyer has been declined.");
		load(request, response);
	}
	
	protected void deleteBuyer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long buyer_id = Long.parseLong(request.getParameter("buyer_id"));
		BuyerDAO.deleteBuyer(buyer_id);
		HttpSession session = request.getSession();
		session.setAttribute("msg", "The buyer has been deleted successfully.");
		load(request, response);
	}
	
	protected void message(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("buyer_id") != null){
			long buyer_id = Long.parseLong(request.getParameter("buyer_id"));
			String subject = request.getParameter("subject"),
				   message = request.getParameter("message");
			
			System.out.println("ID "+buyer_id);
			System.out.println("Subject "+subject);
			System.out.println("Message "+message);
			HttpSession session = request.getSession();
			session.setAttribute("msg", "The message has been sent successfully.");
		}
		else{
			HttpSession session = request.getSession();
			session.setAttribute("msg", "Please select a buyer first!");
		}
		response.sendRedirect(request.getContextPath()+"/Admin/contact_buyers.jsp");
	}
};