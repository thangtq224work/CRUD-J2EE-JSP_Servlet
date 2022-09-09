package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AccountRole;
import model.TuLanh;
import service.TuLanhService;

@WebServlet({ "/tu-lanh" })
public class TuLanhServlet extends HttpServlet {
	TuLanhService tuLanhService = null;

	public TuLanhServlet() {
		super();
		tuLanhService = new TuLanhService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<TuLanh> arrayList = null;
		String nameSearch = request.getParameter("nameSearch");
		String priceFrom = request.getParameter("priceFrom");
		String isDeleted = request.getParameter("isDeleted");
		String priceTo = request.getParameter("priceTo");
		if (nameSearch == null || priceFrom == null || priceTo == null) {
			request.setAttribute("loginError", "Thông tin không hợp lệ");
		} else if(nameSearch.trim().length() >50 || priceFrom.trim().length() > 9 || priceTo.trim().length()> 9  ){
			request.setAttribute("loginError", "Thông tin nhập vào quá dài");
		}
		else {

			if (request.getSession().getAttribute("role") == AccountRole.ADMIN) {
				System.out.println("ADMIN");
				try {
					arrayList = (ArrayList<TuLanh>) tuLanhService.getTuLanhFilterAdmin(isDeleted, nameSearch, priceFrom,
							priceTo);
					if(priceFrom.trim().length()>0 && priceTo.trim().length() > 0) {
						request.setAttribute("from", priceFrom.trim());
						request.setAttribute("to", priceTo.trim());
					}
					request.setAttribute("isDel", isDeleted);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("loginError", "Thông tin không hợp lệ");
				}
				

			} else {

				try {
					arrayList = (ArrayList<TuLanh>) tuLanhService.getTuLanhFilterOtherRole(nameSearch, priceFrom, priceTo);
					if(priceFrom.trim().length()>0 && priceTo.trim().length() > 0) {
						request.setAttribute("from", priceFrom.trim());
						request.setAttribute("to", priceTo.trim());
					}
//					System.out.println(arrayList.size());
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Err");
					request.setAttribute("loginError", "Thông tin không hợp lệ");
				}
			
			}
			request.setAttribute("nameSearch1", nameSearch);
		}	
		request.setAttribute("tuLanhList", arrayList);
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
