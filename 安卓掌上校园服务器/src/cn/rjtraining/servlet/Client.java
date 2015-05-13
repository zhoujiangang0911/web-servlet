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
	 * 通过Http协议对客户端传来的不同请求做相应的处理
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
		    	//用户登录
			      login(request, response);
		       }
		    else if(action.equals("register"))
		    {
		    	//用户注册
		    	register(request, response);

		    }
		   else if(action.equals("loadinfo"))
		 
		    {
		    	//地点信息载入
		    	infoload(request, response);

		    }
		   else if(action.equals("userrequire"))
				 
		    {
		    	//用户信息查看
			   userrequire(request, response);

		    }
		   else if(action.equals("update"))
				 
		    {
		    	//用户信息更新
			   userupdate(request, response);

		    }
		    
		    else if(action.equals("status"))
				 
		    {
		    	//系统使用状态描述
		    	systemstatus(request, response);

		    }
	
		    
		    
	}
	
	private void systemstatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		/*
		*系统使用状态
		*
		*
		*/
		System.out.println("---->get");
   	    System.out.println(action);
		
   	 CollegeDao cd = new CollegeDaoImpl();  //创建一个UserDao类的对象。这里使用了上转型。
	  
   	  int collegesum = cd.collegesum();//学院总数 
	  int majorsum=cd.majorsum();      //专业总数
	  int banjisum=cd.banjisum();      //班级总数
	  
	  PlaceDao pd=new PlaceDaoImpl();
	  int placesum=pd.placesum();      //地点总数
	  
	  ManagerDao md=new ManagerDaoImpl();
	  int managersum=md.managersum();  //注册的用户数量
	  int sex_male=md.sex_sum("男");   //男生用户个数
	  int sex_female=md.sex_sum("女"); //女生用户个数
	  
	  UserDao ud=new UserDaoImpl();
	  int usersum=ud.usersum();        //服务器管理员数目
	  
	  String status="       西安科技大学共有"+collegesum+"个学院，"+majorsum+"个专业，"+banjisum+"个班级。"//
	  +"掌上校园系统共包括西安科技大学的"+placesum+"个地点信息。"+"\r\n       该系统目前拥有"+usersum+"个服务器端管理员，"+"已有"+managersum+"个用户注册使用本系统。"//
	  +"其中，男生"+sex_male+"个、女生"+sex_female+"个。";
	  
	  response.setCharacterEncoding("utf-8");
	  response.setContentType("text/html;charset=UTF-8");
	  List<Status> statuses = new ArrayList<Status>();
	  
	  Status s=new Status(status);
	  statuses.add(s);
	  PrintWriter out=response.getWriter();

	      
	       if(statuses!=null)	
	         {
	    	   //系统状态查询成功
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
	          System.out.println("系统信息查询结束！！");
	         
	   			  //查询成功
//	   			   out.print("success");
	         }
	   		
	   		else
	   		{
	   			System.out.println("系统信息查询失败");
	   			out.print("failed");
	   		}

		
		
		
		
		
		
		
		
		
	}
	private void userupdate(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		/*
		*用户信息更新
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
		
		long iuid = Integer.parseInt(updateMid);  //通过Integer类提供的parseInt()方法，将String类型的数据转换成int类型。
		  ManagerDao md = new ManagerDaoImpl();  //创建一个UserDao类的对象。这里使用了上转型。
		  Manager man = md.find(iuid); 
		  
		  response.setCharacterEncoding("utf-8");
		  response.setContentType("text/html;charset=UTF-8");
		  PrintWriter out=null;
		  
		  try{
		       out=response.getWriter();
		       if(man!=null)
		         {
		    	   //用户存在,可以更新
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
		   			  //更新正确
		   			   out.print("success");
		   			}
		   			else
		   			{
		   				System.out.println("更新插入失败");
		   				out.print("failed");
		   				
		   			}
		   		   }
		          
		       else
		          {
		        	//更新错误，用户不存在
		    	   System.out.println("更新错误，用户不存在");
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
		*用户信息查询
		*
		*
		*/
		System.out.println("---->get");
   	    System.out.println(action);
 	    String userid=request.getParameter("userId");
 	
 	long iuid = Integer.parseInt(userid);  //通过Integer类提供的parseInt()方法，将String类型的数据转换成int类型。
	  ManagerDao md = new ManagerDaoImpl();  //创建一个UserDao类的对象。这里使用了上转型。
	  Manager manager = md.find(iuid); 
	  
	  response.setCharacterEncoding("utf-8");
	  response.setContentType("text/html;charset=UTF-8");
	//  PrintWriter out=null;
	  List<Manager> mans = new ArrayList<Manager>();
	  mans.add(manager);
	  PrintWriter out=response.getWriter();

	      
	       if(mans!=null)	
	         {
	    	   //用户存在
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
	          System.out.println("个人信息查询结束！！");
	         
	   			  //查询成功
//	   			   out.print("success");
	         }
	   		
	   		else
	   		{
	   			System.out.println("查询失败");
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
//       System.out.println("载入结束！！");
		
		
		
		
	}
	//
	private void infoload(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException{
		//, JsonGenerationException, JsonMappingException 
	/*
		*地点信息载入
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
        System.out.println("载入结束！！");
		
		
		
		
		
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
//	    System.out.println("查询结束！！");
	}
	
	public List<Place> findall(){
		/*
		*在数据库查找并返回所有地点信息
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
		*用户注册
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
		
		long iuid = Integer.parseInt(registerMid);  //通过Integer类提供的parseInt()方法，将String类型的数据转换成int类型。
		  ManagerDao md = new ManagerDaoImpl();  //创建一个UserDao类的对象。这里使用了上转型。
		  Manager man = md.find(iuid); 
		  
		  response.setCharacterEncoding("utf-8");
		  response.setContentType("text/html;charset=UTF-8");
		  PrintWriter out=null;
		  
		  try{
		       out=response.getWriter();
		       if(man==null)
		         {
		    	   //用户不存在
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
		   			  //注册正确
		   			   out.print("success");
		   			}
		   			else
		   			{
		   				System.out.println("注册插入失败");
		   				out.print("failed");
		   				
		   			}
		   		   }
		          }
		       else
		          {
		        	//注册错误，用户已存在
		    	   System.out.println("注册错误，用户已存在");
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
		*用户登录
		*
		*
		*/
		
		String loginName=request.getParameter("LoginName");
		  String loginPassword=request.getParameter("LoginPassword");

		  System.out.println(loginName);
		  System.out.println(loginPassword);

		  long iuid = Integer.parseInt(loginName);  //通过Integer类提供的parseInt()方法，将String类型的数据转换成int类型。
		  ManagerDao md = new ManagerDaoImpl();  //创建一个UserDao类的对象。这里使用了上转型。
		  Manager manager = md.find(iuid);  
		  response.setCharacterEncoding("utf-8");
		  response.setContentType("text/html;charset=UTF-8");
		  PrintWriter out=null;

		  try{
		       out=response.getWriter();
		       if(iuid==manager.getMid()&&loginPassword.equals(manager.getPassword()))
		         {
		           //登录正确
		           out.print("success");
		          }
		       else
		          {
		        	//登陆错误
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
