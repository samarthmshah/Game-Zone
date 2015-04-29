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
 * Servlet Filter implementation class AdminLoginFilter
 */
@WebFilter("/Admin/*")
public class AdminLoginCheckFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AdminLoginCheckFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String uri = req.getRequestURI();
		HttpSession sesh = req.getSession();
		
		if (uri.contains("admin_login.jsp")
				|| uri.contains("/css") 
				|| uri.contains("/js")
				|| uri.contains("/images")
				|| uri.contains("/fonts"))
			chain.doFilter(request, response);

		else if (session == null
				|| session.getAttribute("adminLoggedIn") == null
				|| !(((String) session.getAttribute("adminLoggedIn")).equals("true"))){
			sesh.setAttribute("msg", "Please log in again to continue.");
			res.sendRedirect(req.getContextPath() + "/Admin/admin_login.jsp");
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
};