package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import service.AccountService;

@WebServlet({"/login","/logout"})
public class LoginServlet extends HttpServlet {
	private final AccountService accountService = new AccountService();
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if(uri.contains("login")) {
			req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);			
		}
		else {
			HttpSession session = req.getSession();
			if(session.getAttribute("userLogin") != null) {
				session.removeAttribute("userLogin");
				session.removeAttribute("role");
			}
			resp.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		if (user.trim().isEmpty() || pass.trim().isEmpty()) {
			System.out.println("empty");
			request.setAttribute("mess", "Nhập đủ thông tin");
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").include(request, response);
		} else {
			Account u = accountService.getUser(user, pass);
			System.out.println(u);
			if (u == null) {
				request.setAttribute("mess", "Thông tin tài khoản hoặc mật khẩu không chính xác");
				request.getRequestDispatcher("/WEB-INF/view/login.jsp").include(request, response);

			} else {
				HttpSession session = request.getSession();
				session.setAttribute("userLogin", u.getUsername());
				session.setAttribute("role", u.getRole());
				response.sendRedirect(request.getContextPath()+"/home");
			}
		}

	}

}
