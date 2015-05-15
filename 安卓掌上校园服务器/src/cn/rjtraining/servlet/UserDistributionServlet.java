package cn.rjtraining.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.rjtraining.dao.PlaceDao;
import cn.rjtraining.dao.UserDao;
import cn.rjtraining.dao.impl.PlaceDaoImpl;
import cn.rjtraining.dao.impl.UserDaoImpl;
import cn.rjtraining.model.Page;
import cn.rjtraining.model.User;

public class UserDistributionServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserDistributionServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
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
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("list")) {
//				PlaceDao sd = new PlaceDaoImpl();
//				int pageNow = 1;
//				String pa = request.getParameter("pageNow");
//				if (pa != null) {
//					pageNow = Integer.parseInt(pa);
//				}
//				Page page = sd.search(pageNow);
				
				UserDao dao = new UserDaoImpl();	
				List<User>  lis = new ArrayList<User>();
 				lis = dao.findAllUser();
 				request.setAttribute("list",lis);
 				//request.setAttribute("data", page);
				try {
					request.getRequestDispatcher("system/userdistribution.jsp").forward(
							request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
	}

}
