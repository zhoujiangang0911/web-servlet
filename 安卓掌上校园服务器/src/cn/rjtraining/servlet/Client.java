package cn.rjtraining.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.rjtraining.dao.CollegeDao;
import cn.rjtraining.dao.ManagerDao;
import cn.rjtraining.dao.PlaceDao;
import cn.rjtraining.dao.UserDao;
import cn.rjtraining.dao.impl.CollegeDaoImpl;
import cn.rjtraining.dao.impl.ManagerDaoImpl;
import cn.rjtraining.dao.impl.PlaceDaoImpl;
import cn.rjtraining.dao.impl.UserDaoImpl;
import cn.rjtraining.jdbc.Dbconnect;
import cn.rjtraining.model.Manager;
import cn.rjtraining.model.Place;
import cn.rjtraining.model.Status;


public class Client extends HttpServlet {

	/*
	 * ͨ��HttpЭ��Կͻ��˴����Ĳ�ͬ��������Ӧ�Ĵ���
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Place>  places;
	Place place;
	static final String NAME="name";
	String action;
	
	public Client()
	{
		super();
	
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		    System.out.println("->servlet get");
		    request.setCharacterEncoding("utf-8");
     	    action=request.getParameter("action");
		    
		    if(action.equals("login"))
		    {
		    	//�û���¼
			      login(request, response);
		       }
		    else if(action.equals("register"))
		    {
		    	//�û�ע��
		    	register(request, response);

		    }
		   else if(action.equals("loadinfo"))
		 
		    {
		    	//�ص���Ϣ����
		    	infoload(request, response);

		    }
		   else if(action.equals("userrequire"))
				 
		    {
		    	//�û���Ϣ�鿴
			   userrequire(request, response);

		    }
		   else if(action.equals("update"))
				 
		    {
		    	//�û���Ϣ����
			   userupdate(request, response);

		    }
		    
		    else if(action.equals("status"))
				 
		    {
		    	//ϵͳʹ��״̬����
		    	systemstatus(request, response);

		    }
	
		    
		    
	}
	
	private void systemstatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		/*
		*ϵͳʹ��״̬
		*
		*
		*/
		System.out.println("---->get");
   	    System.out.println(action);
		
   	 CollegeDao cd = new CollegeDaoImpl();  //����һ��UserDao��Ķ�������ʹ������ת�͡�
	  
   	  int collegesum = cd.collegesum();//ѧԺ���� 
	  int majorsum=cd.majorsum();      //רҵ����
	  int banjisum=cd.banjisum();      //�༶����
	  
	  PlaceDao pd=new PlaceDaoImpl();
	  int placesum=pd.placesum();      //�ص�����
	  
	  ManagerDao md=new ManagerDaoImpl();
	  int managersum=md.managersum();  //ע����û�����
	  int sex_male=md.sex_sum("��");   //�����û�����
	  int sex_female=md.sex_sum("Ů"); //Ů���û�����
	  
	  UserDao ud=new UserDaoImpl();
	  int usersum=ud.usersum();        //����������Ա��Ŀ
	  
	  String status="       �����Ƽ���ѧ����"+collegesum+"��ѧԺ��"+majorsum+"��רҵ��"+banjisum+"���༶��"//
	  +"����У԰ϵͳ�����������Ƽ���ѧ��"+placesum+"���ص���Ϣ��"+"\r\n       ��ϵͳĿǰӵ��"+usersum+"���������˹���Ա��"+"����"+managersum+"���û�ע��ʹ�ñ�ϵͳ��"//
	  +"���У�����"+sex_male+"����Ů��"+sex_female+"����";
	  
	  response.setCharacterEncoding("utf-8");
	  response.setContentType("text/html;charset=UTF-8");
	  List<Status> statuses = new ArrayList<Status>();
	  
	  Status s=new Status(status);
	  statuses.add(s);
	  PrintWriter out=response.getWriter();

	      
	       if(statuses!=null)	
	         {
	    	   //ϵͳ״̬��ѯ�ɹ�
	         StringBuffer sb = new StringBuffer();
	   
	         sb.append('[');
	         
	         for (Status sta : statuses) 
	         {
	          sb.append('{').append("\"status\":").append("\""+sta.getStatus()+"\"");                        
	          sb.append('}').append(",");
	         }
	         
	          sb.deleteCharAt(sb.length() - 1);
	          sb.append(']');
	          out.write(new String(sb));
	          out.flush();
	          out.close();
	          System.out.println("ϵͳ��Ϣ��ѯ��������");
	         
	   			  //��ѯ�ɹ�
//	   			   out.print("success");
	         }
	   		
	   		else
	   		{
	   			System.out.println("ϵͳ��Ϣ��ѯʧ��");
	   			out.print("failed");
	   		}

		
		
		
		
		
		
		
		
		
	}
	private void userupdate(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		/*
		*�û���Ϣ����
		*
		*
		*/
		System.out.println("---->get");
   	    System.out.println(action);
   	    
		String updateMid=request.getParameter("updateMid");
		String updateMname=request.getParameter("updateMname");
		       updateMname=new String(updateMname.getBytes("iso8859-1"),"utf-8");
		String updatePassword=request.getParameter("updatePassword");
		String updateMcollege=request.getParameter("updateMcollege");
		String updateMage=request.getParameter("updateMage");
		String updateMsex=request.getParameter("updateMsex");
		       updateMsex=new String(updateMsex.getBytes("iso8859-1"),"utf-8");
		String updateMaddress=request.getParameter("updateMaddress");
		       updateMaddress=new String(updateMaddress.getBytes("iso8859-1"),"utf-8");
		String updateMphone=request.getParameter("updateMphone");
		
		long iuid = Integer.parseInt(updateMid);  //ͨ��Integer���ṩ��parseInt()��������String���͵�����ת����int���͡�
		  ManagerDao md = new ManagerDaoImpl();  //����һ��UserDao��Ķ�������ʹ������ת�͡�
		  Manager man = md.find(iuid); 
		  
		  response.setCharacterEncoding("utf-8");
		  response.setContentType("text/html;charset=UTF-8");
		  PrintWriter out=null;
		  
		  try{
		       out=response.getWriter();
		       if(man!=null)
		         {
		    	   //�û�����,���Ը���
		    	   Manager manager = new Manager();


		   			int college=Integer.parseInt(updateMcollege);
		   			int age=Integer.parseInt(updateMage);
		   			
		   			
		   			
		   			manager.setMid(iuid);
		   			manager.setMname(updateMname);
		   			manager.setPassword(updatePassword);
		   			manager.setMcollege(college);
		   			manager.setMage(age);
		   			manager.setMsex(updateMsex);
		   			manager.setMaddress(updateMaddress);
		   			manager.setMphone(updateMphone);
		   			boolean y=md.update(manager);
		   			
		   			if(y)
		   			{
		   			  //������ȷ
		   			   out.print("success");
		   			}
		   			else
		   			{
		   				System.out.println("���²���ʧ��");
		   				out.print("failed");
		   				
		   			}
		   		   }
		          
		       else
		          {
		        	//���´����û�������
		    	   System.out.println("���´����û�������");
		        	out.print("failed");
		        	}
		      }finally
		   {
		          if(out!=null)
		      {
			      out.close();
		      }
		    }
		
		
		
		
	}
	private void userrequire(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException{
		//, JsonGenerationException, JsonMappingException 
		// TODO Auto-generated method stub
		/*
		*�û���Ϣ��ѯ
		*
		*
		*/
		System.out.println("---->get");
   	    System.out.println(action);
 	    String userid=request.getParameter("userId");
 	
 	long iuid = Integer.parseInt(userid);  //ͨ��Integer���ṩ��parseInt()��������String���͵�����ת����int���͡�
	  ManagerDao md = new ManagerDaoImpl();  //����һ��UserDao��Ķ�������ʹ������ת�͡�
	  Manager manager = md.find(iuid); 
	  
	  response.setCharacterEncoding("utf-8");
	  response.setContentType("text/html;charset=UTF-8");
	//  PrintWriter out=null;
	  List<Manager> mans = new ArrayList<Manager>();
	  mans.add(manager);
	  PrintWriter out=response.getWriter();

	      
	       if(mans!=null)	
	         {
	    	   //�û�����
	         StringBuffer sb = new StringBuffer();
	   
	         sb.append('[');
	         
	         for (Manager man : mans) 
	         {
	          sb.append('{').append("\"mid\":").append("\""+man.getMid()+"\"").append(",");                        
	          sb.append("\"mname\":").append("\""+man.getMname()+"\"").append(",");
	          sb.append("\"password\":").append("\""+man.getPassword()+"\"").append(",");
	          sb.append("\"mcollege\":").append("\""+man.getMcollege()+"\"").append(",");
	          sb.append("\"mage\":").append("\""+man.getMage()+"\"").append(",");
	          sb.append("\"msex\":").append("\""+man.getMsex()+"\"").append(",");
	          sb.append("\"maddress\":").append("\""+man.getMaddress()+"\"").append(",");
	          sb.append("\"mphone\":").append("\""+man.getMphone()+"\"");
	          sb.append('}').append(",");
	         }
	         
	          sb.deleteCharAt(sb.length() - 1);
	          sb.append(']');
	          out.write(new String(sb));
	          out.flush();
	          out.close();
	          System.out.println("������Ϣ��ѯ��������");
	         
	   			  //��ѯ�ɹ�
//	   			   out.print("success");
	         }
	   		
	   		else
	   		{
	   			System.out.println("��ѯʧ��");
	   			out.print("failed");
	   		}
	 
//	      }finally
//	   {
//	          if(out!=null)
//	      {
//		      out.close();
//	      }
//	    }
 	
// 	
// 	
// 	
// 	
//      PrintWriter out = response.getWriter();
//      List<Place> places = findall();
//      StringBuffer sb = new StringBuffer();
//
//      sb.append('[');
//     for (Place place : places) {
//
//   	  
//   	  
//       sb.append('{').append("\"pid\":").append("\""+place.getPid()+"\"").append(",");                        
//       sb.append("\"pname\":").append("\""+place.getPname()+"\"").append(",");
//       sb.append("\"plongtitude\":").append("\""+place.getPlongtitude()+"\"").append(",");
//       sb.append("\"platitude\":").append("\""+place.getPlatitude()+"\"").append(",");
//       sb.append("\"pinfo\":").append("\""+place.getPinfo()+"\"");
//       sb.append('}').append(",");
//   }
//       sb.deleteCharAt(sb.length() - 1);
//       sb.append(']');
//       out.write(new String(sb));
//       out.flush();
//       out.close();
//       System.out.println("�����������");
		
		
		
		
	}
	//
	private void infoload(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException{
		//, JsonGenerationException, JsonMappingException 
	/*
		*�ص���Ϣ����
		*
		*
		*/
		
		System.out.println("---->get");
    	 System.out.println(action);
       PrintWriter out = response.getWriter();
       List<Place> places = findall();
       StringBuffer sb = new StringBuffer();

       sb.append('[');
      for (Place place : places) {

    	  
    	  
        sb.append('{').append("\"pid\":").append("\""+place.getPid()+"\"").append(",");                        
        sb.append("\"pname\":").append("\""+place.getPname()+"\"").append(",");
        sb.append("\"plongtitude\":").append("\""+place.getPlongtitude()+"\"").append(",");
        sb.append("\"platitude\":").append("\""+place.getPlatitude()+"\"").append(",");
        sb.append("\"pinfo\":").append("\""+place.getPinfo()+"\"");
        sb.append('}').append(",");
    }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');
        out.write(new String(sb));
        out.flush();
        out.close();
        System.out.println("�����������");
		
		
		
		
		
//		request.setCharacterEncoding("utf-8");
//		String name=request.getParameter(NAME);
//		System.out.println(name);
//        System.out.println("->infoload");
//    	places=new ArrayList<Place>();
//		places=findall();
//		request.setCharacterEncoding("utf-8");
//		
//		List<Place> list=new ArrayList<Place>();
//	    ObjectMapper om=new ObjectMapper();
//	    System.out.println("list output");
//	    for(int i=0;i<places.size();i++)
//	    {
//	    	place=places.get(i);
//	    	if(place.getPid()>0)
//	    	{
//	    		list.add(new Place(place.getPid(),place.getPname(),place.getPlongtitude(),place.getPlatitude(),place.getPinfo()));
//	    	}
//	    	System.out.println(place.toString());
//	    }
//	    for(int i=0;i<list.size();i++)
//	    {
//	    	place=list.get(i);
//	    	System.out.println(place.toString());
//	    }
//	    if(list.size()>0){
//	    om.writeValue(response.getOutputStream(), list);
//	    }
//	    System.out.println("��ѯ��������");
	}
	
	public List<Place> findall(){
		/*
		*�����ݿ���Ҳ��������еص���Ϣ
		*
		*
		*/
		Dbconnect dc=new Dbconnect();
		List<Place> places=new ArrayList<Place>();
		String sql = "select * from place ";
		ResultSet rs= null;
		rs = dc.selectInfo(sql);
		Place place = new Place();
		int i=0;
		System.out.println("sql output");
		try {
			while(rs.next()){
				place.setPid(rs.getInt("pid"));
				place.setPname(rs.getString("pname"));
				place.setPlongtitude(rs.getDouble("plongtitude"));
				place.setPlatitude(rs.getDouble("platitude"));
				place.setPinfo(rs.getString("pinfo"));			
			    places.add(new Place(place.getPid(),place.getPname(),place.getPlongtitude(),place.getPlatitude(),place.getPinfo()));
			    i++;
			    System.out.println(place.toString());
			}
		} catch (SQLException e) {
			return null;
		}
		if(place.getPid()!=0){
			return places;
		}else{
			return null;
		}
	}
	
	private void register(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
		/*
		*�û�ע��
		*
		*
		*/
		
		String registerMid=request.getParameter("registerMid");
		String registerMname=request.getParameter("registerMname");
		       registerMname=new String(registerMname.getBytes("iso8859-1"),"utf-8");
		String registerPassword=request.getParameter("registerPassword");
		String registerMcollege=request.getParameter("registerMcollege");
		String registerMage=request.getParameter("registerMage");
		String registerMsex=request.getParameter("registerMsex");
		       registerMsex=new String(registerMsex.getBytes("iso8859-1"),"utf-8");
		String registerMaddress=request.getParameter("registerMaddress");
		       registerMaddress=new String(registerMaddress.getBytes("iso8859-1"),"utf-8");
		String registerMphone=request.getParameter("registerMphone");
		
		long iuid = Integer.parseInt(registerMid);  //ͨ��Integer���ṩ��parseInt()��������String���͵�����ת����int���͡�
		  ManagerDao md = new ManagerDaoImpl();  //����һ��UserDao��Ķ�������ʹ������ת�͡�
		  Manager man = md.find(iuid); 
		  
		  response.setCharacterEncoding("utf-8");
		  response.setContentType("text/html;charset=UTF-8");
		  PrintWriter out=null;
		  
		  try{
		       out=response.getWriter();
		       if(man==null)
		         {
		    	   //�û�������
		    	   Manager manager = new Manager();


		   		if (registerMid != null && registerPassword!=null&&registerMname!=null) 
		   			{
		   			int college=Integer.parseInt(registerMcollege);
		   			int age=Integer.parseInt(registerMage);
		   			
		   			
		   			
		   			manager.setMid(iuid);
		   			manager.setMname(registerMname);
		   			manager.setPassword(registerPassword);
		   			manager.setMcollege(college);
		   			manager.setMage(age);
		   			manager.setMsex(registerMsex);
		   			manager.setMaddress(registerMaddress);
		   			manager.setMphone(registerMphone);
		   			int y=md.insert(manager);
		   			
		   			if(y>0)
		   			{
		   			  //ע����ȷ
		   			   out.print("success");
		   			}
		   			else
		   			{
		   				System.out.println("ע�����ʧ��");
		   				out.print("failed");
		   				
		   			}
		   		   }
		          }
		       else
		          {
		        	//ע������û��Ѵ���
		    	   System.out.println("ע������û��Ѵ���");
		        	out.print("failed");
		        	}
		      }finally
		   {
		          if(out!=null)
		      {
			      out.close();
		      }
		    }
	}
	
	
	private void login(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		/*
		*�û���¼
		*
		*
		*/
		
		String loginName=request.getParameter("LoginName");
		  String loginPassword=request.getParameter("LoginPassword");

		  System.out.println(loginName);
		  System.out.println(loginPassword);

		  long iuid = Integer.parseInt(loginName);  //ͨ��Integer���ṩ��parseInt()��������String���͵�����ת����int���͡�
		  ManagerDao md = new ManagerDaoImpl();  //����һ��UserDao��Ķ�������ʹ������ת�͡�
		  Manager manager = md.find(iuid);  
		  response.setCharacterEncoding("utf-8");
		  response.setContentType("text/html;charset=UTF-8");
		  PrintWriter out=null;

		  try{
		       out=response.getWriter();
		       if(iuid==manager.getMid()&&loginPassword.equals(manager.getPassword()))
		         {
		           //��¼��ȷ
		           out.print("success");
		          }
		       else
		          {
		        	//��½����
		        	out.print("failed");
		        	}
		      }finally
		   {
		          if(out!=null)
		      {
			      out.close();
		      }
		    }
	}

	
	
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("->Servlet post");
	}
	
	
	
	
	
	
}
