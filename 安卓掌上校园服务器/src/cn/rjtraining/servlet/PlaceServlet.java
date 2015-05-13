package cn.rjtraining.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.rjtraining.dao.PlaceDao;
import cn.rjtraining.dao.impl.PlaceDaoImpl;
import cn.rjtraining.model.Page;
import cn.rjtraining.model.Place;

public class PlaceServlet extends HttpServlet {
	
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
			} else if (action.equals("plaadd")) {
				doPlaAdd(req, res);
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
		PlaceDao sd = new PlaceDaoImpl();
		if (pageNow != null && Integer.parseInt(pageNow) > 1) {
			x = Integer.parseInt(pageNow);
		}
		Page page = sd.search(type, key, x);
		if (page != null) {
			req.setAttribute("data", page);
			req.setAttribute("type", type);
			req.setAttribute("key", key);
			try {
				req.getRequestDispatcher("system/plaSearch.jsp").forward(
						req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doPlaAdd(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		try {
			req.getRequestDispatcher("system/plaAdd.jsp").forward(
					req, res);
		} catch (ServletException e) {

		} catch (IOException e) {

		}
		
	}

	private void doReup(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PlaceDao sd = new PlaceDaoImpl();
		String pid = req.getParameter("pid");
		String pname = req.getParameter("pname");
		String plongtitude=req.getParameter("plongtitude");
		String platitude= req.getParameter("platitude");
		String pinfo=req.getParameter("pinfo");
		
		String pageNow=req.getParameter("pageNow");
		Place place = new Place();
		if (pid != null ) {
			place.setPid(Integer.parseInt(pid));
			place.setPname(pname);
			place.setPlongtitude(Double.parseDouble(plongtitude));
			place.setPlatitude(Double.parseDouble(platitude));
			place.setPinfo(pinfo);
			sd.update(place);
			res.sendRedirect("plaSearch?action=list&pageNow="+pageNow);
		}
		else
		{
			System.out.println("信息填写不完全！");	
		}
	}

	
	protected void doDelete(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		int id = 0;
		PlaceDao sd = new PlaceDaoImpl();
		String pageNow = req.getParameter("pageNow");
		String pid = req.getParameter("pid");
		if (pid != null) {
			id = Integer.parseInt(pid);
			sd.deleteById(id);
			res.sendRedirect("plaSearch?action=list&pageNow=" + pageNow);
		}
	}



	public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String pid = req.getParameter("pid");
		String pageNow= req.getParameter("pageNow");
		PlaceDao sd = new PlaceDaoImpl();
		Place place = null;
		if (pid != null) {
			place = sd.find(Integer.parseInt(pid));
		}

		req.setAttribute("pla", place);
		req.setAttribute("pageNow", pageNow);
		req.getRequestDispatcher("system/plaUpdate.jsp").forward(req, res);
	}

	public void doInsert(HttpServletRequest req, HttpServletResponse res) {
		
		PlaceDao sd = new PlaceDaoImpl();
		String pid = req.getParameter("pid");
		String pname = req.getParameter("pname");
		String plongtitude = req.getParameter("plongtitude");
		String platitude = req.getParameter("platitude");
		String pinfo = req.getParameter("pinfo");

		Place place = new Place();


		if (pid != null ) {
			int id=Integer.parseInt(pid);
			
			place.setPid(id);
			place.setPname(pname);
			place.setPlongtitude(Double.parseDouble(plongtitude));
			place.setPlatitude(Double.parseDouble(platitude));
			place.setPinfo(pinfo);
			int y=sd.insert(place);
			if (y>0) {
				try {
					res.sendRedirect("plaSearch?action=list");
				} catch (Exception e) {
					System.out.println("跳转出错");
				}
			} else {
				try {
					res.sendRedirect("plaSearch?action=plaadd&msg=Insert Error");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
		
		
	}

	public void doList(HttpServletRequest req, HttpServletResponse res) {
		PlaceDao sd = new PlaceDaoImpl();
		int pageNow = 1;
		String pa = req.getParameter("pageNow");
		if (pa != null) {
			pageNow = Integer.parseInt(pa);
		}
		Page page = sd.search(pageNow);
		if (page != null) {
			req.setAttribute("data", page);
			try {
				req.getRequestDispatcher("system/plaList.jsp").forward(
						req, res);
			} catch (ServletException e) {

			} catch (IOException e) {

			}
		}
	}
}
