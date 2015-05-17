package cn.rjtraining.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.rjtraining.dao.CollegeDao;
import cn.rjtraining.dao.DistrictDao;
import cn.rjtraining.dao.UserDao;
import cn.rjtraining.dao.UserTypeDao;
import cn.rjtraining.dao.impl.CollegeDaoImpl;
import cn.rjtraining.dao.impl.DistrictDaoImpl;
import cn.rjtraining.dao.impl.UserDaoImpl;
import cn.rjtraining.dao.impl.UserTypeDaoImpl;
import cn.rjtraining.model.College;
import cn.rjtraining.model.District;
import cn.rjtraining.model.User;
import cn.rjtraining.model.UserType;

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
		 UserTypeDao dd = new UserTypeDaoImpl();
		List< District> list= dao.findDistrictByAllSheng();
		List<UserType> ls=dd.findUserTypeAllType();
		List<String> ll = new ArrayList<>();
		
		CollegeDao daoss = new CollegeDaoImpl();
		List<College> collegels = daoss.findAllCollege();
		req.setAttribute("listcollege", collegels);
		
		req.setAttribute("listsheng",list);
		req.setAttribute("usertype",ls);
		try {
			req.getRequestDispatcher("register.jsp").forward(req, res);
		} catch (Exception e) {
			System.out.println("Ìø×ªÒì³£");
		}
	
	}

	private void doList(HttpServletRequest req, HttpServletResponse res) {
		
		 
	}	 
	


}
