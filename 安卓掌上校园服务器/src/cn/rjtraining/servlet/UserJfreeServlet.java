package cn.rjtraining.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.rjtraining.dao.CollegeDao;
import cn.rjtraining.dao.DistrictDao;
import cn.rjtraining.dao.UserDao;
import cn.rjtraining.dao.impl.CollegeDaoImpl;
import cn.rjtraining.dao.impl.DistrictDaoImpl;
import cn.rjtraining.dao.impl.UserDaoImpl;
import cn.rjtraining.model.College;
import cn.rjtraining.model.District;
import cn.rjtraining.model.User;

public class UserJfreeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserJfreeServlet() {
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
	 * @param response the response send by the1 server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DistrictDao dao = new DistrictDaoImpl();
		List<District> ls = dao.findDistrictByAllSheng();
		UserDao userdao = new UserDaoImpl();
		List<User> userls = userdao.findAllUser();
		int a = ls.size();
		int district[] = new int [a];
		for (int i =0;i<a;i++){
			district[i]= userdao.findByDistrict(ls.get(i).getId());
		}
		request.setAttribute("districteuser", district);
		request.setAttribute("districtlist", ls);
		request.setAttribute("userlist", userls);
	
		try {
			request.getRequestDispatcher("userjfree.jsp").forward(request, response);
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
