package cn.rjtraining.servlet;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

import sun.misc.Cache;

import cn.rjtraining.dao.CollegeDao;
import cn.rjtraining.dao.UserDao;
import cn.rjtraining.dao.impl.CollegeDaoImpl;
import cn.rjtraining.dao.impl.UserDaoImpl;
import cn.rjtraining.model.College;
import cn.rjtraining.model.User;

public class UserCollegeServlet extends HttpServlet {

	
	/**
	 * Constructor of the object.
	 */
	public UserCollegeServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CollegeDao dao = new CollegeDaoImpl();
		List<College> ls = dao.findAllCollege();
		UserDao userdao = new UserDaoImpl();
		List<User> userls = userdao.findAllUser();
		int a = ls.size();
		int colleges[] = new int [a];
		for (int i =0;i<a;i++){
			colleges[i]= userdao.findByCollegeId(ls.get(i).getCid());
		}
		request.setAttribute("collegeuser", colleges);
		request.setAttribute("collegelist", ls);
		request.setAttribute("userlist", userls);
		try {
			request.getRequestDispatcher("jfree.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Ìø×ªÒì³£");
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
