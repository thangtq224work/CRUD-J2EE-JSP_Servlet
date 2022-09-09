package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TuLanh;
import model.Account;
import model.AccountRole;
import service.TuLanhService;

@WebServlet({ "/home" })
public class HomeServlet extends HttpServlet {
	TuLanhService tuLanhService = null;

	public HomeServlet() {
		tuLanhService = new TuLanhService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<TuLanh> arrayList = (ArrayList<TuLanh>) tuLanhService.getAllTuLanh();
		if((req.getSession().getAttribute("role")) == AccountRole.ADMIN) {
			arrayList = (ArrayList<TuLanh>) tuLanhService.getAllTuLanhAdmin();
		}
		req.setAttribute("tuLanhList", arrayList);
		req.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
}
