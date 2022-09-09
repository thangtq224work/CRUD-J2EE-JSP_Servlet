package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Size;
import model.TuLanh;
import service.TuLanhService;

@WebServlet("/tu-lanh/insert")
public class InsertTuLanhServlet extends HttpServlet {

	public final TuLanhService tuLanhService = new TuLanhService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String brand = req.getParameter("brand"), color = req.getParameter("color"),
				weight = req.getParameter("weight"), capacity = req.getParameter("capacity"),
				numOfWing = req.getParameter("numOfWing"), quantity = req.getParameter("quantity"),
				price = req.getParameter("price"), name = req.getParameter("name"),
				description = req.getParameter("description");
		if (brand == null || color == null || weight == null || capacity == null || numOfWing == null
				|| quantity == null || price == null || name == null || description == null) {
			req.setAttribute("error", "Thông tin không đáng tin cậy");
			doGet(req, resp);
		} else if (brand.trim().isEmpty() || color.trim().isEmpty() || weight.trim().isEmpty()
				|| capacity.trim().isEmpty() || numOfWing.trim().isEmpty() || quantity.trim().isEmpty()
				|| price.trim().isEmpty() || name.trim().isEmpty() || description.trim().isEmpty()) {
			req.setAttribute("error", "Nhập đủ thông tin");
			doGet(req, resp);
		} else if (brand.trim().length() > 30 || color.trim().length() > 30 || weight.trim().length() > 5
				|| capacity.trim().length() > 5 || numOfWing.trim().length() > 2 || quantity.trim().length() > 6
				|| (price.contains(".") ? (price.trim().substring(0, price.trim().indexOf("."))).length() > 8
						: price.trim().length() > 8)
						|| name.trim().length() > 50 || description.trim().length() > 50) {
			req.setAttribute("error", "Nhập thông tin quá dài");
			doGet(req, resp);
		}

		else {
			TuLanh tuLanh = null;
			try {
				Boolean isDeleted = req.getParameter("isDeleted") != null ? false : true;
				System.out.println(isDeleted);
//				Long id = Long.valueOf(req.getParameter("id"));
				Integer capacityValue = Integer.valueOf(capacity), qualityValue = Integer.valueOf(quantity),
						numOfWingValue = Integer.valueOf(numOfWing);
				Float weightValue = Float.valueOf(weight), p = Float.valueOf(price);
				BigDecimal priceValue = BigDecimal.valueOf(p);
				if (capacityValue <= 0 || qualityValue <= 0 || numOfWingValue <= 0 || weightValue <= 0 || p <= 0
						|| numOfWingValue > Size.WING || weightValue > Size.WEIGHT || capacityValue > Size.CAPACITY
						|| qualityValue > Size.QUANTITY) {
					throw new Exception();
				} else {

					String user = (String) req.getSession().getAttribute("userLogin");
					tuLanh = new TuLanh(brand, color, weightValue, capacityValue, numOfWingValue, null, qualityValue,
							priceValue, isDeleted, name, description, user, user, new Date(), new Date());
					System.out.println("chạy qua");
					tuLanhService.insertTuLanh(tuLanh);
					req.getSession().setAttribute("insertSuccess", "Thêm thành công");
					resp.sendRedirect(req.getContextPath() + "/home");
				}
			} catch (Exception e) {
				req.setAttribute("error", "Dữ liệu không hợp lệ");
				e.printStackTrace();
				doGet(req, resp);
			}
//			System.out.println("chạy qua");
//			tuLanhService.insertTuLanh(tuLanh);
//			req.setAttribute("tulanh", tuLanh);
		}
	}
}
