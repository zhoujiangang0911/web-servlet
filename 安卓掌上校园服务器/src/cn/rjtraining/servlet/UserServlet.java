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
//		String uid = new String(req.getParameter("uid").getBytes("iso8859-1"),"utf-8");  //��uid�����մ�loginҳ�洫��ֵuid��������ͬ�����յ�������ΪString���͡�������û���ж�uid��password��role
////�Ƿ�Ϊ�գ���Ϊ��login.jspҳ���javascript���Ѿ����й��ж��ˣ����û����ҳ������жϣ���������Ҫ�жϸ������Ƿ�Ϊ�ա�
//		String psw = new String(req.getParameter("password").getBytes("iso8859-1"),"utf-8");
//		String action = new String(req.getParameter("action").getBytes("iso8859-1"),"utf-8");
//		String uname = new String(req.getParameter("uname").getBytes("iso8859-1"),"utf-8");
//		String Districtid = new String(req.getParameter("sheng").getBytes("iso8859-1"),"utf-8");
//		String usertype = new String(req.getParameter("usertype").getBytes("iso8859-1"),"utf-8");
//		String collegeid = new String(req.getParameter("college").getBytes("iso8859-1"),"utf-8");
//		String age = new String(req.getParameter("age").getBytes("iso8859-1"),"utf-8");
//		String sex = new String(req.getParameter("sex").getBytes("iso8859-1"),"utf-8");
//		String phone = new String(req.getParameter("phone").getBytes("iso8859-1"),"utf-8");
//		String address = new String(req.getParameter("address").getBytes("iso8859-1"),"utf-8");
		
		
		
		
		String uid = req.getParameter("uid");
		String psw = req.getParameter("password");
		String action=req.getParameter("action");
		String uname=req.getParameter("uname");
		String Districtid = req.getParameter("sheng");
		String usertype = req.getParameter("usertype");
		String collegeid = req.getParameter("college");
		String age = req.getParameter("age");
		String sex = req.getParameter("sex");
		
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		
	
		
		if(action!=null){
			int iuid = Integer.parseInt(uid);  //ͨ��Integer���ṩ��parseInt()��������String���͵�����ת����int���͡�
			int dis = Integer.parseInt(Districtid);
			int ut = Integer.parseInt(usertype);
			int college = Integer.parseInt(collegeid);
			UserDao ud = new UserDaoImpl();  //����һ��UserDao��Ķ�������ʹ������ת�͡�
			User user = ud.find(iuid);//ͨ��UserDao()�ṩ��find()������ʹ��iuidȥ�����ݿ��в��ң�����һ��user����
			if(user!=null){
                    req.setAttribute("msg", "<font color=blue>�û��Ѵ���</font>");
					req.getRequestDispatcher("login.jsp").forward(req, res); 
			}else{
				user=new User();
				user.setUid(iuid);
				user.setPassword(psw);
				user.setUname(uname);
				user.setDistrictid(dis);
				user.setUsertypeid(ut);
				user.setAddress(address);
				user.setPhone(phone);
				user.setCollegeid(college);
				user.setSex(sex);
				user.setAge(age);
				ud.insert(user);
				
				req.setAttribute("msg", "<font color=blue>ע��ɹ������¼</font>");
					req.getRequestDispatcher("login.jsp").forward(req, res); 
			}
		}else{
		int iuid = Integer.parseInt(uid);  //ͨ��Integer���ṩ��parseInt()��������String���͵�����ת����int���͡�
		UserDao ud = new UserDaoImpl();  //����һ��UserDao��Ķ�������ʹ������ת�͡�
		User user = ud.find(iuid);              //ͨ��UserDao()�ṩ��find()������ʹ��iuidȥ�����ݿ��в��ң�����һ��user����
		if (user != null) {                           //�ж����ݿ����Ƿ��ж�Ӧ��uid���Ƿ�����Ҫ���ҵ��û������û�У���ʾ���û������ڡ�
			if (user.getPassword().equals(psw)) 
			   {  //����ҵ��û��������ȶ������Ƿ���ͬ���������ͬ����ʾ���벻��ȷ��
				
					HttpSession hs = req.getSession(true);//�õ�һ��session�����ҽ��û��������룬��ɫ����Ϣ������session�У��Ա㷽���ʱ��ȥʹ�á�
					hs.setAttribute("uid", user.getUid());
					hs.setAttribute("uname", user.getUname());
					hs.setAttribute("password", user.getPassword());
					
					req.setAttribute("name", user.getUname());
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
}
}
