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

import model.AccountRole;
import model.Size;
import model.TuLanh;
import service.TuLanhService;

@WebServlet({ "/tu-lanh/update", "/tu-lanh/delete" })
public class ModifieldTuLanhServlet extends HttpServlet {
	private TuLanhService tuLanhService = null;
	private TuLanh tuLanh = null;

	public ModifieldTuLanhServlet() {
		tuLanhService = new TuLanhService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = req.getRequestURI();
		if (uri.contains("update")) {
			sendDataToForm(req, resp);
		} else {
			deleteTuLanh(req, resp);
			resp.sendRedirect(req.getContextPath() + "/home");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		System.out.println(id);
		System.out.println(tuLanh);
		if (tuLanh == null) {
			req.getSession().setAttribute("updateSuccess", "Không hợp lệ");
			try {

				resp.sendRedirect(req.getContextPath() + "/home");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (tuLanh.getId() != Long.parseLong(id)) {
			req.getSession().setAttribute("updateSuccess", "Dữ liệu không hợp lệ");
			resp.sendRedirect(req.getContextPath() + "/home");
		} else {
			update(req, resp);

//			tuLanh = null;
		}
	}

	private void deleteTuLanh(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		System.out.println(id);
		if (id == null) {
			request.getSession().setAttribute("updateSuccess", "Không hợp lệ");
		} else {
			try {
				if (!tuLanhService.getTuLanhByID(Long.parseLong(id)).getIsDeleted()) {
					tuLanhService.deleteTuLanh(id);
					request.getSession().setAttribute("updateSuccess", "Xóa thành công");
				}
			} catch (Exception e) {
				request.getSession().setAttribute("updateSuccess", "Không hợp lệ");
			}

		}
	}

	private void sendDataToForm(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		if (id == null) {
			request.getSession().setAttribute("updateSuccess", "Không hợp lệ");
			try {
				System.out.println("dem");
				doGet(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			tuLanh = tuLanhService.getTuLanhByID(Long.valueOf(id));
			if (tuLanh == null) {
				request.setAttribute("loginError", "Id không tồn tại");
				try {
					String contextPath = request.getContextPath() + "/home";
//					response.sendRedirect(contextPath);
					request.getRequestDispatcher("/home").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				request.setAttribute("tulanh", tuLanh);
				request.setAttribute("createDate", simpleDateFormat.format(tuLanh.getCreateDate()));
				request.setAttribute("lastModifieldDate", simpleDateFormat.format(tuLanh.getLastModifieldDate()));

				try {
					request.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		} else {
			System.out.println("demo2");
			TuLanh tuLanh2 = null;
			try {
				Boolean isDeleted = true;
				if (req.getSession().getAttribute("role") == AccountRole.USER) {
					isDeleted = tuLanh.getIsDeleted();
				} else {
					System.out.println("demo");
					isDeleted = req.getParameter("isDeleted") != null ? true : false;
					System.out.println(isDeleted);
				}

//				Long id = Long.valueOf(req.getParameter("id"));
				Integer capacityValue = Integer.valueOf(capacity), qualityValue = Integer.valueOf(quantity),
						numOfWingValue = Integer.valueOf(numOfWing);
				Float w = Float.parseFloat(weight);
				Float weightValue = (float) (Math.round(w * 100.0) / 100.0), p = Float.valueOf(price);
				BigDecimal priceValue = BigDecimal.valueOf(Math.round(p * 100.0) / 100.0);
				if (capacityValue <= 0 || qualityValue <= 0 || numOfWingValue <= 0 || weightValue <= 0 || p <= 0
						|| numOfWingValue > Size.WING || weightValue > Size.WEIGHT || capacityValue > Size.CAPACITY
						|| qualityValue > Size.QUANTITY
						) {
					throw new Exception();
				}
				System.out.println(priceValue);
				String user = (String) req.getSession().getAttribute("userLogin");
				tuLanh2 = new TuLanh(brand, color, weightValue, capacityValue, numOfWingValue, tuLanh.getId(),
						qualityValue, priceValue, isDeleted, name, description, tuLanh.getCreateUser(), user,
						tuLanh.getCreateDate(), new Date());

				tuLanhService.updateTuLanh(tuLanh2);
				req.getSession().setAttribute("updateSuccess", "Cập nhật thành công");
				resp.sendRedirect(req.getContextPath() + "/home");

			} catch (Exception e) {
//				e.printStackTrace();
				req.setAttribute("error", "Dữ liệu không hợp lệ");
				doGet(req, resp);
//				req.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(req, resp);
			}
		}

	}
}
