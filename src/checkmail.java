import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class Checkmail {
	public static void main(String [] args)
	   {    
	      final String PASSWORD = "samarthshah";
	      
	      // Sender's email ID needs to be mentioned
	      final String FROM = "developers.gamezone@gmail.com";

	   // Recipient's email ID needs to be mentioned.
	      String to = "samarthmshah@gmail.com";
	      
	      final String HOST = "smtp.gmail.com";
	      
	      // Get system properties
	      Properties properties = new Properties();

	      // Setup mail server
	      properties.put("mail.transport.protocol", "smtp");
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      
	      properties.put("mail.store.protocol", "pop3");

	      // Get the default Session object.
	      Session mailSession = Session.getInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(mailSession);

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setContent("This is actual message", "text/html");
	         
	         InternetAddress sender = new InternetAddress(FROM, "Game-Zone");
	         InternetAddress receiver = new InternetAddress(to);
	         message.setFrom(sender);
	         message.setRecipient(Message.RecipientType.TO, receiver);
	         message.setSentDate(new Date());
	         message.saveChanges();
	         
	         Transport transport = mailSession.getTransport();
	         transport.connect(HOST, 587, FROM, PASSWORD);
	         transport.sendMessage(message, message.getAllRecipients());
	         transport.close();
	         
	         System.out.println("Sent message successfully!");
	         
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	      catch (UnsupportedEncodingException uee) {
		         uee.printStackTrace();
		      }
	   }
	}


