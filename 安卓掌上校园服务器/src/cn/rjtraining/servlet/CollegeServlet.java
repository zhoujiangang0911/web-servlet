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
				System.out.println("��ת�쳣");
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
			req.setAttribute("msg", "<font color=red>ѧԺ��Ų���Ϊ��</font>");
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
					System.out.println("��ת����");
				}
			} else {
				req.setAttribute("msg", "<font color=red>�������</font>");
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
				req.setAttribute("msg", "<font color=red>��ѧԺ�Ѿ�����</font>");
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
	 * ͨ���ӿ�BanjiDao��������bj����ת��BanjiDaoImpl����������
	 * */
		CollegeDao cd = new CollegeDaoImpl();
		/*
		 * �Զ��������pageNow����ʼ��ֵ1
		 * */
		int pageNow = 1;
		/*
		 * ����ǰ̨����ֵ��һ��������ΪpageNow�ı���
		 * */
		String pa = req.getParameter("pageNow");
		
		if (pa != null) {
			pageNow = Integer.parseInt(pa);
		}
/*		
 * 1.˭����������������BanjiDao�Ķ���������
		2.���������Ҫ��ʲô������
		3.��������Ƿ��з���ֵ�����û�У���ô����������ʲô������У�����ֵ��ʲô��
		*/
		Page page = cd.search(pageNow);
		if (page != null) {
			//��ǰʹ�õ���jspҳ���������ԣ�request��page��session��aplication
			//���Ƿֱ��setAttribute()��getAttribute()��setAttribute()��jsp���ö�����������ԡ�getAttribute()ͨ���������õ����ö����е�����ֵ��
			//ҳ�����û��setParameter()��getParameter()������
			req.setAttribute("data", page);
			//����req��HttpServletRequest�Ķ�����Servlet�����jspҳ���л����Ϣ������Щ��Ϣ���������HttpServletRequest�����С�
			//���ö���һ���ģ�setAttribute()����������������Ժ�����ֵ����HttpServletRequest������������ԡ�
			//getAttribute()�ǴӶ�����ͨ���������õ�����ֵ��
			//getParmeter()�ں�̨�õ��ӱ����������д�����ֵ����ͬ����HttpServletRequest�Ķ����еķ�����
			try {
				req.getRequestDispatcher("system/collegeList.jsp").forward(
						req, res);
			} catch (ServletException e) {

			} catch (IOException e) {

			}
		}
	}
}
