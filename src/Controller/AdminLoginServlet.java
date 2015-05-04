package Controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



//import Model.AdminAuth;
import Model.AdminAuthDAO;

/**
 * Servlet implementation class AdminAuth
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username"), 
			   password = request.getParameter("password"),
			   msg = "", 
			   url = request.getContextPath()+"/Admin/admin_login.jsp",
			   loggedIn = "false";
		HashMap<String, String> adminInfo = null;
		
		if (username == null)
			username = "";
		if (password == null)
			password = "";
		int unlen = username.length();
		int pwlen = password.length();
		if (unlen == 0 && pwlen == 0)
			msg = "Please fill in your username and password";

		if (unlen == 0 || pwlen == 0) {
			if (unlen == 0)
				msg += "<br/>" + "Please input username";
			if (pwlen == 0)
				msg += "<br/>" + "Please input password";
		} 
		else {
			adminInfo = (HashMap<String, String>) AdminAuthDAO.checkUserPass(username, password);
			if (adminInfo != null && adminInfo.get("loggedIn") == "true") {
				loggedIn = "true";
				url = request.getContextPath()+"/Admin/index.jsp";
			} 
			else
				msg = "Incorrect username or password";
			try {
				AdminAuthDAO.DB_Close();
			} 
			catch (Throwable th) {
				System.err.println("Unable to close.");
			}
		}
		HttpSession session = request.getSession(true);
		
		if (loggedIn.equals("true")) {
			session.setAttribute("adminLoggedIn", "true");
			session.setAttribute("firstName", adminInfo.get("firstName"));
			session.setAttribute("lastName", adminInfo.get("lastName"));
		}
		session.setAttribute("msg", msg);
		response.sendRedirect(url);
	}
};