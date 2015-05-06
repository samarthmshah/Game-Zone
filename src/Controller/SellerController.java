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

import Model.BuyerDAO;
import Model.PasswordHash;
import Model.SellerDAO;
import VO.BuyerVO;
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
			else if(flag.equals("loadAccountDetails"))
				loadAccountDetails(request, response);
			else if(flag.equals("contactSellerThroughEmail"))	
				loadSellerInfo(request, response);
			else if(flag.equals("deactivate"))	
				deactivate(request, response);
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
			else if(flag.equals("messageSeller4mAdmin"))
				messageSeller4mAdmin(request, response);
			else if(flag.equals("contactSeller"))
				contactSeller(request, response);
			else if(flag.equals("update"))
				update(request, response);
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
			msg = "Account created successfully. An activation link has been sent to your email";
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
	
	protected void messageSeller4mAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("seller_id") != null){
			long seller_id = Long.parseLong(request.getParameter("seller_id"));
			String subject = request.getParameter("subject"),
				   message = request.getParameter("message");
			if(subject == null) subject = "";
			if(message == null) message = "";
			
			String seller_username = "",
				   TO="";
			
			Iterator<SellerVO> seller_itr = SellerDAO.getSellerById(seller_id).iterator();
			while(seller_itr.hasNext()){
				SellerVO svo = seller_itr.next();
				seller_username = svo.getUsername();
				TO = svo.getEmail();
			}
			
			final String FROM = "developers.gamezone@gmail.com";	// Basic details of my account
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
	        Session msg_session = Session.getInstance(props,
	        		new javax.mail.Authenticator() {
	      		         protected PasswordAuthentication getPasswordAuthentication() {
	      		            return new PasswordAuthentication(USERNAME, PASSWORD);
	      		         }
		    });

		    try{
		    	// Create a default MimeMessage object.
		         MimeMessage msg2buyer = new MimeMessage(msg_session);

		         // Set From: header field of the header.
				msg2buyer.setFrom(new InternetAddress(FROM, "Administrator"));	// Shows that it is sent by sellers username

		         // Set To: header field of the header.
		         msg2buyer.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
			         
	        	// Set Subject: header field
		         msg2buyer.setSubject(subject);	// Subject set by the seller.
		         msg2buyer.setContent("<h1>Dear "+seller_username+",</h1>"
      								+ "<p>The following message is sent to you by the Administrator.</p><hr/>"
      								+ "<p><strong>"+ message +"</strong></p><hr/>"
      								+ "<p>Please contact him back on developers.gamezone@gmail.com</p>"
      								+ "<br/><p>Happy Gaming.<br/>"
      								+ "<strong>Game-Zone Team</strong></p>", "text/html");	// The message written by Admin.
		         msg2buyer.saveChanges();
		         Transport.send(msg2buyer); // Send message
		      }
		    
		    catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
		    
			HttpSession session = request.getSession();
			session.setAttribute("msg", "The message has been sent successfully");
		}
		else{
			HttpSession session = request.getSession();
			session.setAttribute("msg", "Please select a seller first!");
		}
		response.sendRedirect(request.getContextPath()+"/Admin/contact_sellers.jsp");
	}
	
	protected void contactSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("seller_id") != null){
			long seller_id = Long.parseLong(request.getParameter("seller_id"));
			long buyer_id = Long.parseLong(request.getParameter("buyer_id"));
			String subject = request.getParameter("subject"),
				   message = request.getParameter("message");
			if(subject == null) subject = "";
			if(message == null) message = "";
			
			String seller_username = "",
				   TO="",
				   buyer_username="",
				   buyer_firstname="",
				   buyer_lastname="",
				   buyer_email="";
			
			Iterator<SellerVO> seller_itr = SellerDAO.getSellerById(seller_id).iterator();
			while(seller_itr.hasNext()){
				SellerVO svo = seller_itr.next();
				seller_username = svo.getUsername();
				TO = svo.getEmail();
			}
			
			Iterator<BuyerVO> buyer_itr = BuyerDAO.getBuyerById(buyer_id).iterator();
			while(buyer_itr.hasNext()){
				BuyerVO bvo = buyer_itr.next();
				buyer_firstname = bvo.getFirstname();
				buyer_lastname = bvo.getLastname();
				buyer_email = bvo.getEmail();
			}
			
			final String FROM = "developers.gamezone@gmail.com";	// Basic details of my account
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
	        Session msg_session = Session.getInstance(props,
	        		new javax.mail.Authenticator() {
	      		         protected PasswordAuthentication getPasswordAuthentication() {
	      		            return new PasswordAuthentication(USERNAME, PASSWORD);
	      		         }
		    });

		    try{
		    	// Create a default MimeMessage object.
		         MimeMessage msg2buyer = new MimeMessage(msg_session);

		         // Set From: header field of the header.
				msg2buyer.setFrom(new InternetAddress(FROM, buyer_username));	// Shows that it is sent by sellers username

		         // Set To: header field of the header.
		         msg2buyer.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
			         
	        	// Set Subject: header field
		         msg2buyer.setSubject(subject);	// Subject set by the seller.
		         msg2buyer.setContent("<h1>Dear "+seller_username+",</h1>"
      								+ "<p>The following message is sent to you by "+buyer_firstname+" "+buyer_lastname+".</p><hr/>"
      								+ "<p><strong>"+ message +"</strong></p><hr/>"
      								+ "<p>Please contact him back on "+buyer_email+"</p>"
      								+ "<br/><p>Happy Gaming.<br/>"
      								+ "<strong>Game-Zone Team</strong></p>", "text/html");	// The message written by seller.
		         msg2buyer.saveChanges();
		         Transport.send(msg2buyer); // Send message
		         System.out.println("Sent message successfully!");
		      }
		    
		    catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
		    
			HttpSession session = request.getSession();
			session.setAttribute("msg", "The message has been sent successfully.");
		}
		else{
			HttpSession session = request.getSession();
			session.setAttribute("msg", "Please select a seller first!");
		}
		response.sendRedirect(request.getContextPath()+"/Seller_Buyer/buyer_messageSeller.jsp");
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
		
		protected void loadAccountDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			long seller_id = Long.parseLong(request.getParameter("seller_id"));
			HttpSession session = request.getSession();
			session.setAttribute("sellerInfo", SellerDAO.getSellerById(seller_id));
			response.sendRedirect(request.getContextPath()+"/Seller_Buyer/seller_accountDetails.jsp");
		}
		
		protected void loadSellerInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<SellerVO> sellerList = SellerDAO.showAll();
			HttpSession session = request.getSession();
			session.setAttribute("sellerList", sellerList);
			response.sendRedirect(request.getContextPath()+"/Seller_Buyer/buyer_messageSeller.jsp");
		}
		
		protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			long seller_id = Long.parseLong(request.getParameter("seller_id"));
			String companyName = request.getParameter("companyname"),
				   firstName = request.getParameter("firstname"),
				   lastName = request.getParameter("lastname"),
				   email = request.getParameter("email"),
				   phNo = request.getParameter("phNo"),
				   address = request.getParameter("address"),
				   zip = request.getParameter("zip"),
				   routingNumber = request.getParameter("routingNumber"),
				   accountNumber = request.getParameter("accountNumber"),
				   paypal = request.getParameter("paypal");
					
			if(zip == null) zip = "";
			
			SellerDAO.update(seller_id, companyName, firstName, lastName, email, phNo, address, zip, routingNumber, accountNumber, paypal);
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", "The account is updated successfully");
			loadAccountDetails(request, response);
		}
		
		protected void deactivate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			long seller_id = Long.parseLong(request.getParameter("seller_id"));
			SellerDAO.deactivate(seller_id);
			request.getSession().setAttribute("loggedIn", "false");
			response.sendRedirect(request.getContextPath()+"/Seller_Buyer/seller_index.jsp");
		}
};
