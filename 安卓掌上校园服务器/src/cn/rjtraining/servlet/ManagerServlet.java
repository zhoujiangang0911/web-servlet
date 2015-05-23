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
import cn.rjtraining.dao.ManagerDao;
import cn.rjtraining.dao.UserDao;
import cn.rjtraining.dao.UserTypeDao;
import cn.rjtraining.dao.impl.CollegeDaoImpl;
import cn.rjtraining.dao.impl.DistrictDaoImpl;
import cn.rjtraining.dao.impl.ManagerDaoImpl;
import cn.rjtraining.dao.impl.UserDaoImpl;
import cn.rjtraining.dao.impl.UserTypeDaoImpl;
import cn.rjtraining.model.College;
import cn.rjtraining.model.District;
import cn.rjtraining.model.Manager;
import cn.rjtraining.model.Page;
import cn.rjtraining.model.User;
import cn.rjtraining.model.UserType;

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
			}else if (action.equals("otherlist")) {
				doOtherList(req, res);
			}else if (action.equals("update_other")) {
				doUpdate_other(req, res);
			}else if (action.equals("reup_other")) {
				doReup_other(req, res);
			}else if (action.equals("delete_other")) {
				doDelete_other(req, res);
			} 
			
			

		}
	}



	

	
	
	private void doDelete_other(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		int id = 0;
		ManagerDao sd = new ManagerDaoImpl();
		UserDao ud=new UserDaoImpl();
		
		String pageNow = req.getParameter("pageNow");
		String uid = req.getParameter("uid");
		if (uid != null) {
			id = Integer.parseInt(uid);
			sd.deleteById(id);
			ud.deleteById(id);                        
			try {
				res.sendRedirect("manSearch?action=otherlist&pageNow=" + pageNow);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void doReup_other(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		ManagerDao sd = new ManagerDaoImpl();
		UserDao   ud=new UserDaoImpl();
		
		String uid = req.getParameter("uid");
		String uname = req.getParameter("uname");
		String password = req.getParameter("password");
		String usertype = req.getParameter("usertype");
		
		
		String pageNow=req.getParameter("pageNow");
		
		Manager manager = new Manager();
		User user=new User();
		if (uid != null && password != null && uname!=null) {
			manager.setMid(Integer.parseInt(uid));
			manager.setMname(uname);
			manager.setPassword(password);
			
			
			user.setUid(Integer.parseInt(uid));
			user.setUname(uname);
			user.setPassword(password);
			user.setUsertypeid(Integer.parseInt(usertype));
			
			
			sd.update(manager);
			ud.update(user);
			
			try {
				res.sendRedirect("manSearch?action=otherlist&pageNow="+pageNow);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("信息填写不完全！");	
		}
	}

	private void doUpdate_other(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		String uid = req.getParameter("uid");
		String pageNow= req.getParameter("pageNow");
//		ManagerDao sd = new ManagerDaoImpl();
		UserDao ud=new UserDaoImpl();
		
		User user = null;
		if (uid != null) {
			user = ud.find(Integer.parseInt(uid));
			int current_usertype=user.getUsertypeid();                    //记录当前用户的身份类型
		}

		req.setAttribute("user", user);
		req.setAttribute("pageNow", pageNow);
		try {
			req.getRequestDispatcher("system/man_other_Update.jsp").forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doOtherList(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		ManagerDao sd = new ManagerDaoImpl();
		int pageNow = 1;
		String pa = req.getParameter("pageNow");
		if (pa != null) {
			pageNow = Integer.parseInt(pa);
		}
		Page page = sd.search_other(pageNow);
		if (page != null) {
			req.setAttribute("data", page);
			try {
				req.getRequestDispatcher("system/otherlist.jsp").forward(
						req, res);
			} catch (ServletException e) {

			} catch (IOException e) {

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
		DistrictDao dao = new DistrictDaoImpl();
		 UserTypeDao dd = new UserTypeDaoImpl();
		List< District> list= dao.findDistrictByAllSheng();
		List<UserType> ls=dd.findUserTypeAllType();
		List<String> ll = new ArrayList<String>();
		
		CollegeDao daoss = new CollegeDaoImpl();
		List<College> collegels = daoss.findAllCollege();
		req.setAttribute("listcollege", collegels);
		
		req.setAttribute("listsheng",list);
		req.setAttribute("usertype",ls);
		try {
			req.getRequestDispatcher("system/manAdd.jsp").forward(
					req, res);
		} catch (ServletException e) {

		} catch (IOException e) {

		}
		
	}

	private void doReup(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ManagerDao sd = new ManagerDaoImpl();
		UserDao   ud=new UserDaoImpl();
		
		String uid = req.getParameter("uid");
		String uname = req.getParameter("uname");
		String password = req.getParameter("password");
		String collegeid = req.getParameter("collegeid");
		String age = req.getParameter("age");
		String sex = req.getParameter("sex");
		String address = req.getParameter("address");
		String phone = req.getParameter("phone");
		
		String pageNow=req.getParameter("pageNow");
		
		Manager manager = new Manager();
		User user=new User();
		if (uid != null && password != null && uname!=null) {
			manager.setMid(Integer.parseInt(uid));
			manager.setMname(uname);
			manager.setPassword(password);
			manager.setMcollege(Integer.parseInt(collegeid));
			manager.setMage(Integer.parseInt(age));
			manager.setMsex(sex);
			manager.setMaddress(address);
			manager.setMphone(phone);
			
			user.setUid(Integer.parseInt(uid));
			user.setUname(uname);
			user.setPassword(password);
			user.setUsertypeid(1);
			user.setCollegeid(Integer.parseInt(collegeid));
			user.setAge(Integer.parseInt(age));
			user.setSex(sex);
			user.setPhone(phone);
			user.setAddress(address);
			
			sd.update(manager);
			ud.update(user);
			
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
		UserDao ud=new UserDaoImpl();
		
		String pageNow = req.getParameter("pageNow");
		String uid = req.getParameter("uid");
		if (uid != null) {
			id = Integer.parseInt(uid);
			sd.deleteById(id);
			ud.deleteById(id);                        
			res.sendRedirect("manSearch?action=list&pageNow=" + pageNow);
		}
	}



	public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String pageNow= req.getParameter("pageNow");
//		ManagerDao sd = new ManagerDaoImpl();
		UserDao ud=new UserDaoImpl();
		
		User user = null;
		if (uid != null) {
			user = ud.find(Integer.parseInt(uid));
		}

		req.setAttribute("user", user);
		req.setAttribute("pageNow", pageNow);
		req.getRequestDispatcher("system/manUpdate.jsp").forward(req, res);
	}

	public void doInsert(HttpServletRequest req, HttpServletResponse res) {
		
		ManagerDao sd = new ManagerDaoImpl();
		UserDao  ud=new UserDaoImpl();
		
		String mid = req.getParameter("uid");
		String mname = req.getParameter("uname");
		String password = req.getParameter("password");
		String mcollege = req.getParameter("college");
		String mage = req.getParameter("age");
		String msex = req.getParameter("sex");
		String maddress = req.getParameter("address");
		String mphone = req.getParameter("phone");
		String Districtid = req.getParameter("sheng");

		Manager manager = new Manager();
        User user=new User();

		if (mid != null && password!=null&&mname!=null) {
			long id=Integer.parseInt(mid);
			int college=Integer.parseInt(mcollege);
			int age=Integer.parseInt(mage);
			int sheng=Integer.parseInt(Districtid);
			int usertype=1;
			
			manager.setMid(id);
			manager.setMname(mname);
			manager.setPassword(password);
			manager.setMcollege(college);
			manager.setMage(age);
			manager.setMsex(msex);
			manager.setMaddress(maddress);
			manager.setMphone(mphone);
			
			user.setAddress(maddress);
			user.setAge(age);
			user.setCollegeid(college);
			user.setDistrictid(sheng);
			user.setPassword(password);
			user.setPhone(mphone);
			user.setSex(msex);
			user.setUid(id);
			user.setUname(mname);
			user.setUsertypeid(usertype);
			
			int x=ud.insert(user);
			int y=sd.insert(manager);
			if (y>0&&x>0) {
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
		CollegeDao daoss = new CollegeDaoImpl();
		List<College> collegels = daoss.findAllCollege();
		req.setAttribute("listcollege", collegels);
		if (page != null) {
			req.setAttribute("data", page);
			System.out.println(page.getDatas());
			try {
				req.getRequestDispatcher("system/manList.jsp").forward(
						req, res);
			} catch (ServletException e) {

			} catch (IOException e) {

			}
		}
	}
}
