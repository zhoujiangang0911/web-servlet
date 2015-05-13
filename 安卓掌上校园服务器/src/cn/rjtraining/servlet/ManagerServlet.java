package cn.rjtraining.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.rjtraining.dao.ManagerDao;
import cn.rjtraining.dao.impl.ManagerDaoImpl;
import cn.rjtraining.model.Manager;
import cn.rjtraining.model.Page;

public class ManagerServlet extends HttpServlet {
	
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
		if (action != null) {
			if (action.equals("list")) {
				doList(req, res);
			}else if (action.equals("delete")) {
				doDelete(req, res);
			}  else if (action.equals("update")) {
				doUpdate(req, res);
			} else if (action.equals("manadd")) {
				doManAdd(req, res);
			}else if (action.equals("insert")) {
				doInsert(req, res);
			} else if (action.equals("search")) {
				doSearch(req, res);
			}else if (action.equals("reup")) {
				doReup(req, res);
			}
			
		}
	}



	

	
	
	private void doSearch(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		int x = 1;
		String pageNow = req.getParameter("pageNow");
		String type = req.getParameter("type");
		String key = req.getParameter("key");
		ManagerDao sd = new ManagerDaoImpl();
		if (pageNow != null && Integer.parseInt(pageNow) > 1) {
			x = Integer.parseInt(pageNow);
		}
		Page page = sd.search(type, key, x);
		if (page != null) {
			req.setAttribute("data", page);
			req.setAttribute("type", type);
			req.setAttribute("key", key);
			try {
				req.getRequestDispatcher("system/manSearch.jsp").forward(
						req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doManAdd(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		try {
			req.getRequestDispatcher("system/manAdd.jsp").forward(
					req, res);
		} catch (ServletException e) {

		} catch (IOException e) {

		}
		
	}

	private void doReup(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ManagerDao sd = new ManagerDaoImpl();
		
		String mid = req.getParameter("mid");
		String mname = req.getParameter("mname");
		String password = req.getParameter("password");
		String mcollege = req.getParameter("mcollege");
		String mage = req.getParameter("mage");
		String msex = req.getParameter("msex");
		String maddress = req.getParameter("maddress");
		String mphone = req.getParameter("mphone");
		
		String pageNow=req.getParameter("pageNow");
		Manager manager = new Manager();
		if (mid != null && password != null && mname!=null) {
			manager.setMid(Integer.parseInt(mid));
			manager.setMname(mname);
			manager.setPassword(password);
			manager.setMcollege(Integer.parseInt(mcollege));
			manager.setMage(Integer.parseInt(mage));
			manager.setMsex(msex);
			manager.setMaddress(maddress);
			manager.setMphone(mphone);
			sd.update(manager);
			res.sendRedirect("manSearch?action=list&pageNow="+pageNow);
		}
		else
		{
			System.out.println("信息填写不完全！");	
		}
	}

	
	protected void doDelete(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		int id = 0;
		ManagerDao sd = new ManagerDaoImpl();
		String pageNow = req.getParameter("pageNow");
		String mid = req.getParameter("mid");
		if (mid != null) {
			id = Integer.parseInt(mid);
			sd.deleteById(id);
			res.sendRedirect("manSearch?action=list&pageNow=" + pageNow);
		}
	}



	public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String mid = req.getParameter("mid");
		String pageNow= req.getParameter("pageNow");
		ManagerDao sd = new ManagerDaoImpl();
		Manager manager = null;
		if (mid != null) {
			manager = sd.find(Integer.parseInt(mid));
		}

		req.setAttribute("man", manager);
		req.setAttribute("pageNow", pageNow);
		req.getRequestDispatcher("system/manUpdate.jsp").forward(req, res);
	}

	public void doInsert(HttpServletRequest req, HttpServletResponse res) {
		
		ManagerDao sd = new ManagerDaoImpl();
		String mid = req.getParameter("mid");
		String mname = req.getParameter("mname");
		String password = req.getParameter("password");
		String mcollege = req.getParameter("mcollege");
		String mage = req.getParameter("mage");
		String msex = req.getParameter("msex");
		String maddress = req.getParameter("maddress");
		String mphone = req.getParameter("mphone");

		Manager manager = new Manager();


		if (mid != null && password!=null&&mname!=null) {
			long id=Integer.parseInt(mid);
			int college=Integer.parseInt(mcollege);
			int age=Integer.parseInt(mage);
			
			manager.setMid(id);
			manager.setMname(mname);
			manager.setPassword(password);
			manager.setMcollege(college);
			manager.setMage(age);
			manager.setMsex(msex);
			manager.setMaddress(maddress);
			manager.setMphone(mphone);
			
			int y=sd.insert(manager);
			if (y>0) {
				try {
					res.sendRedirect("manSearch?action=list");
				} catch (Exception e) {
					System.out.println("跳转出错");
				}
			} else {
				try {
					res.sendRedirect("manSearch?action=manadd&msg=Insert Error");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
		
		
	}

	public void doList(HttpServletRequest req, HttpServletResponse res) {
		ManagerDao sd = new ManagerDaoImpl();
		int pageNow = 1;
		String pa = req.getParameter("pageNow");
		if (pa != null) {
			pageNow = Integer.parseInt(pa);
		}
		Page page = sd.search(pageNow);
		if (page != null) {
			req.setAttribute("data", page);
			try {
				req.getRequestDispatcher("system/manList.jsp").forward(
						req, res);
			} catch (ServletException e) {

			} catch (IOException e) {

			}
		}
	}
}
