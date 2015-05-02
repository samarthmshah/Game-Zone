package Controller;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.*;
import javax.mail.internet.*;

import Model.BuyerDAO;
import Model.BuyerKeyDAO;
import VO.BuyerKeyVO;
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
	/*
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
	*/
	
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
				msg = "Please Confirm Your Username and Key";
				url = "/Seller_Buyer/confirmation.jsp";
			
				//Generating random string
				final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				Random rnd = new Random();
				int len=5;
				StringBuilder sb = new StringBuilder( len );
				for( int i = 0; i < len; i++ ) 
				sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
				String rand_key=sb.toString();
				
				//inserting random key with its username into table buyerkeyvo_dtl
				BuyerKeyVO bkvo = new BuyerKeyVO( username, rand_key);
				BuyerKeyDAO.insert(bkvo);
				System.out.println("Done with both insertions");
				//sending email verifcation with a key
				sendEmail(username,email,rand_key);
			}
			else
				msg = "Username already taken.";
			HttpSession session = request.getSession();
			System.out.println("1");
			session.setAttribute("msg", msg);
			System.out.println("2");
			response.sendRedirect(request.getContextPath()+url);
			System.out.println("3");
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
	
	//Method for sending confirmation messages with key
		public void sendEmail(String username,String to,String rand_key)
		{    
			// Sender's email ID needs to be mentioned
		    String from = "dineshgprs@gmail.com";

		    // Assuming you are sending email from Gmail
		    String host = "smtp.gmail.com";
		    String USERNAME = "dineshgprs@gmail.com";
		    String PASSWORD = "k2kb2bdbgv";

		    // Get system properties
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", "587");
		    
	        // Get the default Session object.
	        Session session = Session.getInstance(props,
	        		new javax.mail.Authenticator() {
	      		         protected PasswordAuthentication getPasswordAuthentication() {
	      		            return new PasswordAuthentication(USERNAME, PASSWORD);
	      		         }
	        });

		    try{
		    	// Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));

		         // Set Subject: header field
		         message.setSubject("Activate Your GameZone Account!!");

		         // Now set the actual message
		         message.setText("Hey "+username+",\nWelcome to GameZone :)\nActivation Key: "+rand_key+"\n\nPlease enter the activation Key!!");

		         // Send message
		         Transport.send(message);
		         System.out.println("Sent message successfully....");
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
		   }

};