package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TuLanh;
import model.Account;
import model.AccountRole;
import service.AccountService;
import service.TuLanhService;
//import service.UserService;

/**
 * Servlet implementation class demo
 */
@WebServlet("/demo")
public class Demo extends HttpServlet {

	private final AccountService accountService = new AccountService();
	private final TuLanhService tuLanhService = new TuLanhService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		List<TuLanh> tuLanhs = new ArrayList<TuLanh>();
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountService.getAllAccount();
		tuLanhs  = tuLanhService.getAllTuLanh();
		if(accounts.size()==0 && tuLanhs.size()==0) {
			
		try {
		tuLanhs.add(new TuLanh("SamSung", "red", 40.0f,120,2, 0L,10,BigDecimal.valueOf(3000.0f), false,"Tủ lạnh SamSung 120 Lít", "Làm lạnh nhanh","Teo" ,"No",format.parse("1/7/2022"), format.parse("1/7/2022")));
		tuLanhs.add(new TuLanh("Toshiba", "black", 55.0f,120,1, 1L,10,BigDecimal.valueOf(4000.0f), false,"Tủ lạnh Toshiba 120 Lít", "Làm lạnh nhanh","Teo" ,"Teo",format.parse("1/7/2022"), format.parse("11/7/2022")));
		tuLanhs.add(new TuLanh("Aqua", "black", 35.0f,150,1, 2L,10,BigDecimal.valueOf(4500.0f), false,"Tủ lạnh Aqua 150 Lít", "Làm lạnh nhanh","Teo" ,"Chi",format.parse("1/7/2022"), format.parse("12/7/2022")));
		tuLanhs.add(new TuLanh("Panasonic", "White", 40.0f,300,2, 3L,10,BigDecimal.valueOf(3400.0f), false,"Tủ lạnh Panasonic 300 Lít", "Làm lạnh nhanh","Teo" ,"Teo",format.parse("1/7/2022"), format.parse("11/7/2022")));
		tuLanhs.add(new TuLanh("SamSung", "red", 40.0f,200,4, 4L,10,BigDecimal.valueOf(3200.0f), false,"Tủ lạnh SamSung 200 Lít", "Làm lạnh nhanh","Chi" ,"No",format.parse("1/7/2022"), format.parse("9/7/2022")));
		tuLanhs.add(new TuLanh("Toshiba", "White", 40.0f,110,2, 5L,10,BigDecimal.valueOf(4400.0f), false,"Tủ lạnh Toshiba 110 Lít", "Làm lạnh nhanh","Teo" ,"No",format.parse("1/7/2022"), format.parse("7/7/2022")));
		tuLanhs.add(new TuLanh("Panasonic", "red", 40.0f,190,4, 6L,10,BigDecimal.valueOf(5000.0f), false,"Tủ lạnh Panasonic 190 Lít", "Làm lạnh nhanh","No" ,"No",format.parse("1/7/2022"), format.parse("6/7/2022")));
		tuLanhs.add(new TuLanh("Panasonic", "Blue", 40.0f,120,2, 7L,0,BigDecimal.valueOf(3000.0f), false,"Tủ lạnh Panasonic 120 Lít", "Làm lạnh nhanh","Teo" ,"No",format.parse("1/7/2022"), format.parse("6/7/2022")));
		tuLanhs.add(new TuLanh("Toshiba", "black", 40.0f,250,4, 8L,10,BigDecimal.valueOf(3400.0f), false,"Tủ lạnh Toshiba 250 Lít", "Làm lạnh nhanh","No" ,"No",format.parse("1/7/2022"), format.parse("10/7/2022")));
		tuLanhs.add(new TuLanh("Panasonic", "Gray", 40.0f,240,4, 9L,0,BigDecimal.valueOf(3600.0f), false,"Tủ lạnh Panasonic 240 Lít", "Làm lạnh nhanh","No" ,"No",format.parse("1/7/2022"), format.parse("11/7/2022")));
		Account account = new Account("demo1", "demo1", AccountRole.ADMIN, true);
		accountService.addAccount(account);
		Account account2 = new Account("demo2", "demo1", AccountRole.USER, true);
		accountService.addAccount(account2);
		Account account3 = new Account("demo3", "demo1", AccountRole.USER, true);
		accountService.addAccount(account3);
		for (TuLanh tuLanh : tuLanhs) {
			new TuLanhService().insertTuLanh(tuLanh);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
		
//		List<TuLanh> u = TuLanhService.getTuLanhFilter("price");
//		for (TuLanh tuLanh : u) {
//			System.out.println(tuLanh);
//		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
