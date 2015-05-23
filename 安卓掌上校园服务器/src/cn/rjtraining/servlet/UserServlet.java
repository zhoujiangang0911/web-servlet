package cn.rjtraining.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.rjtraining.dao.ManagerDao;
import cn.rjtraining.dao.UserDao;
import cn.rjtraining.dao.impl.ManagerDaoImpl;
import cn.rjtraining.dao.impl.UserDaoImpl;
import cn.rjtraining.model.Manager;
import cn.rjtraining.model.User;





public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	//重写了doGet()方法，执行的其实是doPost()方法。
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	//重写doPost()方法。
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		
		String uid = req.getParameter("uid");
		String psw = req.getParameter("password");
		String action=req.getParameter("action");
		String uname=req.getParameter("uname");
		String Districtid = req.getParameter("sheng");

		String age = req.getParameter("age");
		String sex = req.getParameter("sex");
		
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		
	
		
		if(action!=null){
			long iuid = Integer.parseInt(uid);  //通过Integer类提供的parseInt()方法，将String类型的数据转换成int类型。
			int dis = Integer.parseInt(Districtid);

       //  lx修改， 将usertype统一改为4，代表管理员			
			
			int ut = 4;                                      //代表管理员
			int age1=Integer.parseInt(age);
			
			UserDao ud = new UserDaoImpl();  //创建一个UserDao类的对象。这里使用了上转型。
			User user = ud.find(iuid);//通过UserDao()提供的find()方法，使用iuid去在数据库中查找，返回一个user对象。
			
			ManagerDao ddd = new ManagerDaoImpl();
			Manager manager = ddd.find(Long.parseLong(uid));
			
			if(user!=null&&manager!=null){
                    req.setAttribute("msg", "<font color=blue>用户已存在</font>");
					req.getRequestDispatcher("login.jsp").forward(req, res); 
			}
			else
			{
				user=new User();
				manager = new Manager();
				manager.setMid(Long.parseLong(uid));
				manager.setPassword(psw);
				manager.setMname(uname);
				manager.setMage(Integer.parseInt(age));
				manager.setMaddress(address);

				manager.setMphone(phone);
				manager.setMsex(sex);
				user.setUid(iuid);
				user.setPassword(psw);
				user.setUname(uname);
				user.setDistrictid(dis);
				user.setUsertypeid(ut);
				user.setAddress(address);
				user.setPhone(phone);

				user.setSex(sex);
				user.setAge(age1);
				ud.insert(user);
				ddd.insert(manager);
				req.setAttribute("msg", "<font color=blue>注册成功，请登录</font>");
					req.getRequestDispatcher("login.jsp").forward(req, res); 
			}
		}
		else
		{
		long iuid = Integer.parseInt(uid);  //通过Integer类提供的parseInt()方法，将String类型的数据转换成int类型。
		UserDao ud = new UserDaoImpl();  //创建一个UserDao类的对象。这里使用了上转型。
		User user = ud.find(iuid);              //通过UserDao()提供的find()方法，使用iuid去在数据库中查找，返回一个user对象。
		int y=ud.findType(iuid);
		if(y==4)
		{
		if (user != null) {                           //判断数据库中是否有对应的uid，是否有需要查找的用户。如果没有，提示该用户不存在。
			if (user.getPassword().equals(psw)) 
			   {  //如果找到用户，继续比对密码是否相同，如果不相同，提示密码不正确。
				
					HttpSession hs = req.getSession(true);//得到一个session，并且将用户名，密码，角色等信息都存入session中，以便方便的时候去使用。
					hs.setAttribute("uid", user.getUid());
					hs.setAttribute("uname", user.getUname());
					hs.setAttribute("password", user.getPassword());
					
					req.setAttribute("name", user.getUname());
					req.setAttribute("uid", user.getUid());
					req.getRequestDispatcher("system/adminIndex.jsp").forward(req, res);
				
			   } 
			 else {
				System.out.println("密码不相等");
				req.setAttribute("msg", "<font color=blue>密码或用户不匹配</font>");
				req.getRequestDispatcher("login.jsp").forward(req, res);      //密码匹配错误，继续跳转到login.jsp登陆界面。
			      }
		} else {
			System.out.println("用户不存在");
			req.setAttribute("msg", "<font color=blue>用户不存在</font>");
			req.getRequestDispatcher("login.jsp").forward(req, res);           //用户匹配错误，继续跳转到login.jsp登陆界面。
		}
	  }
		else
		{
			System.out.println("只有管理员才有权登录！");
			req.setAttribute("msg", "<font color=blue>当前用户无权限，只有管理员才有权登录！</font>");
			req.getRequestDispatcher("login.jsp").forward(req, res);        
		}
}
	}
}
