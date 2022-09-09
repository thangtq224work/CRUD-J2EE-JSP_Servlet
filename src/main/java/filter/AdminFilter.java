package filter;

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

import model.AccountRole;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/tu-lanh/delete")
public class AdminFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		if(session.getAttribute("role") == AccountRole.ADMIN) {
			chain.doFilter(req, res);
		}
		else {
			((HttpServletResponse) res).getWriter().append("Not Allowed");
		}
	}


}
