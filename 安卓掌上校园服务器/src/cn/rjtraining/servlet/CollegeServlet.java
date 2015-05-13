package cn.rjtraining.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.rjtraining.dao.CollegeDao;
import cn.rjtraining.dao.impl.CollegeDaoImpl;
import cn.rjtraining.model.College;
import cn.rjtraining.model.Page;

public class CollegeServlet extends HttpServlet {

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
			} else if (action.equals("insert")) {
				doInsert(req, res);
			} else if (action.equals("update")) {
				doUpdate(req, res);
			} else if (action.equals("search")) {
				doSearch(req, res);
			} else if (action.equals("coladd")) {
				doSelectBan(req,res);
			}else if(action.equals("delete")){
				doDelete(req,res);
			}else if(action.equals("reup")){
				doReup(req, res);
			}
		}		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		int id = 0;
		CollegeDao bd = new CollegeDaoImpl();
		String pageNow = req.getParameter("pageNow");
		String cid = req.getParameter("cid");
		if (cid != null) {
			id = Integer.parseInt(cid);
			bd.deleteById(id);
			res.sendRedirect("colSearch?action=list&pageNow=" + pageNow);
		}
	}
	
	private void doReup(HttpServletRequest req, HttpServletResponse res) throws IOException {
		CollegeDao bd = new CollegeDaoImpl();
		String cid = req.getParameter("cid");
		String cname = req.getParameter("cname");
		String cleader = req.getParameter("cleader");
		String cmajorsum = req.getParameter("cmajorsum");
		String cbanjisum = req.getParameter("cbanjisum");
		String cinfo = req.getParameter("cinfo");
		String pageNow=req.getParameter("pageNow");
		College college = new College();
		if (cid != null && cname != null ) {
			college.setCid(Integer.parseInt(cid));
			college.setCname(cname);
			college.setCleader(cleader);
			college.setCmajorsum(Integer.parseInt(cmajorsum));
			college.setCbanjisum(Integer.parseInt(cbanjisum));
			college.setCinfo(cinfo);
			bd.update(college);
			res.sendRedirect("colSearch?action=list&pageNow="+pageNow);
		}
	}
	
	protected void doSelectBan(HttpServletRequest req, HttpServletResponse res) {
		CollegeDao bd=new CollegeDaoImpl();
		List<College> list=bd.searchForStu(2);
		if(list!=null){
			req.setAttribute("college", list);
			try {
				req.getRequestDispatcher("system/colAdd.jsp").forward(req, res);
			} catch (Exception e) {
				System.out.println("跳转异常");
			}
		}
	}

	protected void doSearch(HttpServletRequest req, HttpServletResponse res) {
		int x = 1;
		String pageNow = req.getParameter("pageNow");
		String type = req.getParameter("type");
		String key = req.getParameter("key");
		CollegeDao cd = new CollegeDaoImpl();
		if (pageNow != null && Integer.parseInt(pageNow) > 1) {
			x = Integer.parseInt(pageNow);
		}
		Page page = cd.search(type, key, x);
		if (page != null) {
			req.setAttribute("data", page);
			req.setAttribute("type", type);
			req.setAttribute("key", key);
			try {
				req.getRequestDispatcher("system/collegeList.jsp").forward(
						req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String cid = req.getParameter("cid");
		String pageNow= req.getParameter("pageNow");
		CollegeDao cd = new CollegeDaoImpl();
		College co=null;
		if (cid!=null) {
			co = cd.find(Integer.parseInt(cid));
		}
		req.setAttribute("co", co);
		req.setAttribute("pageNow", pageNow);
		req.getRequestDispatcher("system/collegeUpdate.jsp").forward(req, res);
	}


	public void doInsert(HttpServletRequest req, HttpServletResponse res) {
		CollegeDao cd = new CollegeDaoImpl();
		String cid = req.getParameter("cid");
		String cname = req.getParameter("cname");
		String cleader = req.getParameter("cleader");
		String cmajorsum = req.getParameter("cmajorsum");
		String cbanjisum = req.getParameter("cbanjisum");
		String cinfo = req.getParameter("cinfo");
		String pageNow=req.getParameter("pageNow");
		
		College col=new College();
		if(cid==""){
			req.setAttribute("msg", "<font color=red>学院编号不能为空</font>");
			try {
			req.getRequestDispatcher("system/collegeAdd.jsp").forward(
						req, res);
			} catch (ServletException e) {
				System.err.println("servet Excption");
			} catch (IOException e) {
				System.err.println("Io Excption");
			}
		}else{
		if (cname != "") {
			col.setCid(Integer.parseInt(cid));
			col.setCname(cname);
			col.setCleader(cleader);
			col.setCmajorsum(Integer.parseInt(cmajorsum));
			col.setCbanjisum(Integer.parseInt(cbanjisum));
			col.setCinfo(cinfo);
			College y=cd.find(Integer.parseInt(cid));
			if(y==null){
				int x = cd.insert(col);
			if (x > 0) {
				try {
					res.sendRedirect("colSearch?action=list");
				} catch (Exception e) {
					System.out.println("跳转出错");
				}
			} else {
				req.setAttribute("msg", "<font color=red>插入出错</font>");
				try {
					req.getRequestDispatcher("system/collegeAdd.jsp").forward(req, res);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}else{
				req.setAttribute("msg", "<font color=red>此学院已经存在</font>");
				try {
					req.getRequestDispatcher("system/collegeAdd.jsp").forward(
							req, res);
				} catch (ServletException e) {
					System.err.println("servet Excption");
				} catch (IOException e) {
					System.err.println("Io Excption");
				}
			}
		}
	}
	}
	public void doList(HttpServletRequest req, HttpServletResponse res) {
	/*	
	 * 通过接口BanjiDao声明对象bj，上转型BanjiDaoImpl来创建对象。
	 * */
		CollegeDao cd = new CollegeDaoImpl();
		/*
		 * 自定义个变量pageNow，初始化值1
		 * */
		int pageNow = 1;
		/*
		 * 接受前台传来值的一个属性名为pageNow的变量
		 * */
		String pa = req.getParameter("pageNow");
		
		if (pa != null) {
			pageNow = Integer.parseInt(pa);
		}
/*		
 * 1.谁来调方法，这里是BanjiDao的对象来调。
		2.这个方法需要传什么参数。
		3.这个方法是否有返回值，如果没有，那么它的作用是什么，如果有，返回值是什么。
		*/
		Page page = cd.search(pageNow);
		if (page != null) {
			//以前使用的是jsp页面内置属性，request，page，session，aplication
			//他们分别的setAttribute()和getAttribute()。setAttribute()向jsp内置对象中添加属性。getAttribute()通过属性名拿到内置对象中的属性值。
			//页面对象没有setParameter()和getParameter()方法。
			req.setAttribute("data", page);
			//首先req是HttpServletRequest的对象，是Servlet引擎从jsp页面中获得信息，将这些信息打包放入了HttpServletRequest对象中。
			//作用都是一样的，setAttribute()是往对象中添加属性和属性值。向HttpServletRequest对象中添加属性。
			//getAttribute()是从对象中通过属性名得到属性值。
			//getParmeter()在后台拿到从表单、超链接中传来的值。他同样是HttpServletRequest的对象中的方法。
			try {
				req.getRequestDispatcher("system/collegeList.jsp").forward(
						req, res);
			} catch (ServletException e) {

			} catch (IOException e) {

			}
		}
	}
}
