package cn.rjtraining.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JFreeServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8383894493427589433L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		
		req.setAttribute("one", 100);
		req.setAttribute("two", 20);
		req.setAttribute("three", 50);
		req.setAttribute("four", 30);
		final RequestDispatcher rd = req.getRequestDispatcher("jfree.jsp");
		try {
//			resp.sendRedirect("htttp://www.baidu.com");
			rd.forward(req, resp);
		} catch (Exception e) {
			System.out.println("Ìø×ªÒì³£");
			e.printStackTrace();
		}
		return;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//super.doGet(req, resp);
		doPost(req, resp);
	}
}
