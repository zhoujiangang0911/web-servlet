package cn.rjtraining.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.rjtraining.dao.DistrictDao;
import cn.rjtraining.dao.UserDao;
import cn.rjtraining.dao.impl.DistrictDaoImpl;
import cn.rjtraining.dao.impl.UserDaoImpl;
import cn.rjtraining.model.District;
import cn.rjtraining.model.User;

public class DistrictServlete extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		 DistrictDao dao = new DistrictDaoImpl();
		 
		List< District> list= dao.findDistrictByAllSheng();
		List<String> ll = new ArrayList<>();
		ll.add("a");
		ll.add("b");
		ll.add("c");
		ll.add("d");
		
		for (String string : ll) {
			System.out.println(string);
		}
		for (District district : list) {
			System.out.println(district.getId());
		}
		System.out.println(list.size());
		req.setAttribute("listsheng",list);
		try {
			req.getRequestDispatcher("register.jsp").forward(req, res);
		} catch (Exception e) {
			System.out.println("跳转异常");
		}
	
	}

	private void doList(HttpServletRequest req, HttpServletResponse res) {
		UserDao ud = new UserDaoImpl();  //创建一个UserDao类的对象。这里使用了上转型。
		 DistrictDao dao = new DistrictDaoImpl();
		 
		List< District> list= dao.findDistrictByAllSheng();
		System.out.println("ser"+list.size());
		req.setAttribute("listsheng",list);
		try {
			req.getRequestDispatcher("register.jsp").forward(req, res);
		} catch (Exception e) {
			System.out.println("跳转异常");
		}
		 
	}	 
	


}
