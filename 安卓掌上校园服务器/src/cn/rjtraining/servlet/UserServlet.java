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
	//��д��doGet()������ִ�е���ʵ��doPost()������
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	//��дdoPost()������
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
			long iuid = Integer.parseInt(uid);  //ͨ��Integer���ṩ��parseInt()��������String���͵�����ת����int���͡�
			int dis = Integer.parseInt(Districtid);

       //  lx�޸ģ� ��usertypeͳһ��Ϊ4���������Ա			
			
			int ut = 4;                                      //�������Ա
			int age1=Integer.parseInt(age);
			
			UserDao ud = new UserDaoImpl();  //����һ��UserDao��Ķ�������ʹ������ת�͡�
			User user = ud.find(iuid);//ͨ��UserDao()�ṩ��find()������ʹ��iuidȥ�����ݿ��в��ң�����һ��user����
			
			ManagerDao ddd = new ManagerDaoImpl();
			Manager manager = ddd.find(Long.parseLong(uid));
			
			if(user!=null&&manager!=null){
                    req.setAttribute("msg", "<font color=blue>�û��Ѵ���</font>");
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
				req.setAttribute("msg", "<font color=blue>ע��ɹ������¼</font>");
					req.getRequestDispatcher("login.jsp").forward(req, res); 
			}
		}
		else
		{
		long iuid = Integer.parseInt(uid);  //ͨ��Integer���ṩ��parseInt()��������String���͵�����ת����int���͡�
		UserDao ud = new UserDaoImpl();  //����һ��UserDao��Ķ�������ʹ������ת�͡�
		User user = ud.find(iuid);              //ͨ��UserDao()�ṩ��find()������ʹ��iuidȥ�����ݿ��в��ң�����һ��user����
		int y=ud.findType(iuid);
		if(y==4)
		{
		if (user != null) {                           //�ж����ݿ����Ƿ��ж�Ӧ��uid���Ƿ�����Ҫ���ҵ��û������û�У���ʾ���û������ڡ�
			if (user.getPassword().equals(psw)) 
			   {  //����ҵ��û��������ȶ������Ƿ���ͬ���������ͬ����ʾ���벻��ȷ��
				
					HttpSession hs = req.getSession(true);//�õ�һ��session�����ҽ��û��������룬��ɫ����Ϣ������session�У��Ա㷽���ʱ��ȥʹ�á�
					hs.setAttribute("uid", user.getUid());
					hs.setAttribute("uname", user.getUname());
					hs.setAttribute("password", user.getPassword());
					
					req.setAttribute("name", user.getUname());
					req.setAttribute("uid", user.getUid());
					req.getRequestDispatcher("system/adminIndex.jsp").forward(req, res);
				
			   } 
			 else {
				System.out.println("���벻���");
				req.setAttribute("msg", "<font color=blue>������û���ƥ��</font>");
				req.getRequestDispatcher("login.jsp").forward(req, res);      //����ƥ����󣬼�����ת��login.jsp��½���档
			      }
		} else {
			System.out.println("�û�������");
			req.setAttribute("msg", "<font color=blue>�û�������</font>");
			req.getRequestDispatcher("login.jsp").forward(req, res);           //�û�ƥ����󣬼�����ת��login.jsp��½���档
		}
	  }
		else
		{
			System.out.println("ֻ�й���Ա����Ȩ��¼��");
			req.setAttribute("msg", "<font color=blue>��ǰ�û���Ȩ�ޣ�ֻ�й���Ա����Ȩ��¼��</font>");
			req.getRequestDispatcher("login.jsp").forward(req, res);        
		}
}
	}
}
