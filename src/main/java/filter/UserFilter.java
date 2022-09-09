package filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountRole;
@WebFilter({
	"/tu-lanh/update",
	"/tu-lanh/insert"
})
public class UserFilter extends HttpFilter{
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		if(session.getAttribute("role") == null) {
			((HttpServletResponse) res).getWriter().append("Not Allowed");
		}
		else {
			chain.doFilter(req, res);
		}
	}
}
