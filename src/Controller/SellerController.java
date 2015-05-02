package Controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.PasswordHash;
import Model.SellerDAO;
import VO.LinkSellerVO;
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
			else if(flag.equals("activation"))
				activation(request, response);
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
			String hashedPassword = null;
			try {
			hashedPassword = PasswordHash.createHash(password);			// Create a hashed Password
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(hashedPassword == null) hashedPassword = password;
			
			SellerVO svo = new SellerVO(companyName, firstName, lastName, username, hashedPassword, email, phNo, dob, address, zip, 
										routingNumber, accountNumber, paypal, 0);
			SellerDAO.insert(svo);
			sendActivationLink(svo, request, response);
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
	
	private void sendEmail(String username,String to,String activationLink, String msg)
	{    
		// Sender's email ID
	    final String FROM = "developers.gamezone@gmail.com";
	    String USERNAME = "developers.gamezone@gmail.com";
	    String PASSWORD = "samarthshah";
	    final String HOST = "smtp.gmail.com";

	    // Get system properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
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
			message.setFrom(new InternetAddress(FROM));

	         // Set To: header field of the header.
	         message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		         
	         if(msg.equals("Account Activation")){
	        	// Set Subject: header field
		         message.setSubject("Activate Your GameZone Account!!");
		         message.setContent("<h1>Below is your Account Activation Code, "+username+"</h1>"
		         					+ "<a href='"+ activationLink +"'>Click here to activate your account.</a>"
		         					+ "<p>Happy Gaming.<br/>"
		         					+ "<strong>Game-Zone Team</strong></p>", "text/html");
	         }
	         message.saveChanges();
	         Transport.send(message); // Send message
	         System.out.println("Sent message successfully!");
	      }
	    
	    catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }
	
		private void sendActivationLink(SellerVO svo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String link = generateHashCode(svo);
			Date dispatchDT = new Date();
			
			LinkSellerVO lsvo = new LinkSellerVO();
			lsvo.setDispatchDT(dispatchDT.getTime());
			lsvo.setLink(link);
			lsvo.setSeller_id(svo);
			lsvo.setStatus(0);
			SellerDAO.insert(lsvo);	// Insert an entry in the database
			
			String activationLink = "http://localhost:8080/game_zone_v1.0/SellerController?flag=activation&auth="+link;
			sendEmail(svo.getUsername(), svo.getEmail(), activationLink, "Account Activation");
		}
		
		private String generateHashCode(SellerVO svo){
			String myKey = svo.getUsername() + svo.getSeller_id();
			byte[] unEncodedHash = myKey.getBytes();
			
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("md5");
			} 
			catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			md.reset();		// Resets the MD for future use.
			md.update(unEncodedHash);	// Now update it by our key 
			byte[] encodedHash = md.digest(); // Compute digest, reset MD, and store it in encoded form.
			
			StringBuilder hashCode = new StringBuilder();
			
			for(int i=0; i<encodedHash.length; i++){
				if(((int)encodedHash[i] & 0xff) < 0x10){
					hashCode.append("0");
				}
				hashCode.append( Long.toString((int) encodedHash[i] & 0xff, 16) );
			}
			
			return hashCode.toString();
		}
		
		protected void activation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String link = request.getParameter("auth");
			List<LinkSellerVO> ls = SellerDAO.getTupleByLink(link);
			HttpSession session = request.getSession();
			if(ls != null){
				Iterator<LinkSellerVO> itr = ls.iterator();
				while(itr.hasNext()){
					LinkSellerVO lsvo = itr.next();
					long currentDT = new Date().getTime();	// milliseconds
					long dispatchDT = lsvo.getDispatchDT();	// // milliseconds
					if((currentDT - dispatchDT) <= 1000*60*60){	// 1 hour
						if(lsvo.getStatus() == 0){
							SellerDAO.updateStatus(lsvo.getSeller_id().getSeller_id(), "approve");	// Set status in Seller table
							lsvo.setActivationDT(currentDT);
							lsvo.setStatus(1);
							SellerDAO.updateLSVOOnActivation(lsvo);
							session.setAttribute("msg", "Account Activated");
						}
						else
							session.setAttribute("msg", "Account Already Activated");
					}
					else
						session.setAttribute("msg", "Activation Link has expired");
				}
			}
			else
				session.setAttribute("msg", "Invalid Activation Code");
			response.sendRedirect(request.getContextPath()+"/Seller_Buyer/login.jsp");
		}
};
