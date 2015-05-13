package cn.rjtraining.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.rjtraining.dao.UserDao;
import cn.rjtraining.dao.impl.UserDaoImpl;
import cn.rjtraining.model.User;

public class PswServlet extends HttpServlet {

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
		String action = req.getParameter("action");
		if (action.equals("update")) {
			doUpdate(req, res);
		} else if (action.equals("confirm")) {
			doConfirm(req, res);
		}
	}

	private void doConfirm(HttpServletRequest req, HttpServletResponse res) {
		String uid = req.getParameter("uid");
		String opsw = req.getParameter("oldpassword");
		String npsw = req.getParameter("newpsw");
		String repsw = req.getParameter("repsw");
		if (npsw.equals(repsw)) {
			User user = null;
			UserDao ud = new UserDaoImpl();
			if (!uid.equals("")) {
				int id = Integer.parseInt(uid);
				user = ud.find(id);
			}
			if (user != null) {
				String psw = user.getPassword();
				if (psw.equals(opsw)) {
					user.setPassword(npsw);
					ud.update(user);
					HttpSession hs = req.getSession();
					hs.invalidate();
					req.setAttribute("msg", "密码修改成功，请重新登陆");
					try {

						req.getRequestDispatcher("/login.jsp").forward(req, res);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					HttpSession hs = req.getSession();
					hs.invalidate();
					req.setAttribute("msg", "您输入的原始密码不正确，请重新登陆后再修改");
					try {
						req.getRequestDispatcher("/login.jsp")
								.forward(req, res);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			req.setAttribute("msg", "两次输入的密码不相等");
			try {
				req.getRequestDispatcher("/login.jsp").forward(req,
						res);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void doUpdate(HttpServletRequest req, HttpServletResponse res) {
		HttpSession hs = req.getSession();
		int uid = (Integer) hs.getAttribute("uid");
		String uname = (String) hs.getAttribute("uname");
		if (uid != 0 && uname != null) {
			req.setAttribute("uid", uid);
			req.setAttribute("uname", uname);
			try {
				req.getRequestDispatcher("system/pswUpdate.jsp").forward(req,
						res);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}