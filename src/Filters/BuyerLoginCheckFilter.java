package Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class BuyerLoginCheckFilter
 */
@WebFilter("/Seller_Buyer/*")
public class BuyerLoginCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public BuyerLoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String uri = req.getRequestURI();
		HttpSession sesh = req.getSession();
		String userInfoObjType = null;
		if(session!= null && session.getAttribute("userInfoObj")!= null)
			userInfoObjType = session.getAttribute("userInfoObj").getClass().getName();
		
		if(uri.contains("/login.jsp") 
				|| uri.contains("/registration_buyer.jsp")
				|| uri.contains("/registration_seller.jsp")
				|| uri.contains("/css") 
				|| uri.contains("/js")
				|| uri.contains("/images")
				|| uri.contains("/fonts")
				|| uri.contains("fileUploadTest.jsp"))
			chain.doFilter(request, response);
		
		else if(session == null
				|| session.getAttribute("loggedIn") == null
				|| !(((String) session.getAttribute("loggedIn")).equals("true"))){
			sesh.setAttribute("msg", "Please log in again to continue.");
			res.sendRedirect(req.getContextPath()+"/Seller_Buyer/login.jsp");
		}

		// Or if userInfoObj is of type Buyer but userType is seller then login.
		else if(userInfoObjType != null && userInfoObjType.equals("VO.BuyerVO") && uri.indexOf("seller") > 0){
			sesh.setAttribute("msg", "Please log in again to continue.");
			res.sendRedirect(req.getContextPath()+"/Seller_Buyer/login.jsp");
		}
		
		// Or if userInfoObj is of type Seller but userType is buyer then login.
		else if(userInfoObjType != null && userInfoObjType.equals("VO.SellerVO") && uri.indexOf("buyer") > 0){
			sesh.setAttribute("msg", "Please log in again to continue.");
			res.sendRedirect(req.getContextPath()+"/Seller_Buyer/login.jsp");
		}
		
		else
			chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
