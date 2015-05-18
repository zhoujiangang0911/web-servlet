package cn.rjtraining.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cn.rjtraining.dao.ManagerDao;
import cn.rjtraining.jdbc.Dbconnect;
import cn.rjtraining.model.Manager;
import cn.rjtraining.model.Page;
import cn.rjtraining.model.Place;

public class ManagerDaoImpl implements ManagerDao {
	Dbconnect dc=new Dbconnect();
	public Manager find(long id) {
		String sql = "select * from manager where mid="+id;
		ResultSet rs= null;
		rs = dc.selectInfo(sql);
		Manager manager = new Manager();
		try {
			while(rs.next()){
				manager.setMid(rs.getLong(1));
				manager.setMname(rs.getString(2));
				manager.setPassword(rs.getString(3));
				manager.setMcollege(rs.getInt(4));
				manager.setMage(rs.getInt(5));
				manager.setMsex(rs.getString(6));
				manager.setMaddress(rs.getString(7));
				manager.setMphone(rs.getString(8));			
			}
		} catch (SQLException e) {
			return null;
		}
		if(manager.getMid()!=0){
			return manager;
		}else{
			return null;
		}
	}
	public boolean update(Manager manager) {
		long id=manager.getMid();
		String mname=manager.getMname();
		String psw=manager.getPassword();
		int college=manager.getMcollege();
		int mage=manager.getMage();
		String msex=manager.getMsex();
		String maddress=manager.getMaddress();
		String mphone=manager.getMphone();
		
		String sql ="update manager set mname='"+mname+"', password='"+psw+"',mcollege="+college+",mage="+mage+",msex='"+msex+"',maddress='"+maddress+"',mphone='"+mphone+"' where mid="+id;
		System.out.println(sql);
		int x=dc.noSelectInfo(sql);
		if(x<=0){
			return false;
		}else{
			return true;
		}
	}
	public int insert(Manager manager) {
		String sql="insert into manager values("+manager.getMid()+",'"+manager.getMname()+"','"+manager.getPassword()+"',"+manager.getMcollege()+","+manager.getMage()+",'"+manager.getMsex()+"',"+"'"+manager.getMaddress()+"',"+"'"+manager.getMphone()+"')";	
		int x=dc.noSelectInfo(sql);
		System.out.println(sql);
		return x;
	}
	public boolean deleteById(int id) {
		String sql="delete from manager where mid="+id;
		System.out.println(sql);
		int x = dc.noSelectInfo(sql);
		if(x>0){
			return true;
		}else{
		return false;
		}
	}
	
	public Page search(int pageNow) {
		Page page = new Page();
		List<Manager> list = new ArrayList<Manager>();
		ResultSet rs = null;
		String sql = "select * from Manager limit " + (pageNow - 1)
				* page.getPageSize() + ", " + page.getPageSize();
		String sql1 = "select count(*) from manager";
		rs = dc.selectInfo(sql1);
		try {
			while (rs.next()) {
				page.setRowCount(rs.getInt(1));
				page.setPageCount(rs.getInt(1));
			}
		} catch (SQLException e1) {
			System.out.println("计算rowcount出错");
		}
		rs = dc.selectInfo(sql);
		try {
			while (rs.next()) {
				Manager manager = new Manager();
				manager.setMid(rs.getLong(1));
				manager.setMname(rs.getString(2));
				manager.setPassword(rs.getString(3));
				manager.setMcollege(rs.getInt(4));
				manager.setMage(rs.getInt(5));
				manager.setMsex(rs.getString(6));
				manager.setMaddress(rs.getString(7));
				manager.setMphone(rs.getString(8));
				list.add(manager);
			}
		} catch (SQLException e) {
			System.out.println("sql语句异常");
		}
		page.setPageNow(pageNow);
		page.setDatas(list);
		return page;
	}
	public Page search(String type, String key, int pageNow) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		String sqll = "select count(*) from manager";
		List<Manager> list = new ArrayList<Manager>();
		Page page = new Page();
		page.setPageNow(pageNow);
		String sql = "select * from manager";
		if (key != null) {
			if (type.equals("mid") || type.equals("mname")) {
				sql = sql + " where " + type + "=" + key + " limit "
						+ (page.getPageNow() - 1) * page.getPageSize() + ","
						+ page.getPageSize();
				sqll = sqll + " where " + type + "=" + key;
			} else {
				sql = sql + " where " + type + " like '%" + key + "%' limit "
						+ (page.getPageNow() - 1) * page.getPageSize() + ","
						+ page.getPageSize();
				sqll = sqll + " where " + type + " like '%" + key + "%'";
			}
		}
		rs = dc.selectInfo(sqll);
		try {
			while (rs.next()) {
				page.setRowCount(rs.getInt(1));
				page.setPageCount(rs.getInt(1));
			}
		} catch (SQLException e1) {
			System.out.println("sql异常1");
		}
		rs = dc.selectInfo(sql);
		try {
			while (rs.next()) {
				Manager manager = new Manager();
				manager.setMid(rs.getLong(1));
				manager.setMname(rs.getString(2));
				manager.setPassword(rs.getString(3));
				manager.setMcollege(rs.getInt(4));
				manager.setMage(rs.getInt(5));
				manager.setMsex(rs.getString(6));
				manager.setMaddress(rs.getString(7));
				manager.setMphone(rs.getString(8));
				list.add(manager);
			}
		} catch (SQLException e) {
			System.out.println("sql异常");
		}
		page.setDatas(list);
		return page;
	}
	public int managersum() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		String sql = "select count(*) from manager";
        System.out.println(sql);
          int x =0;
       rs= dc.selectInfo(sql);
        try {
        	while (rs.next())
			x=rs.getInt(1);
			return x;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sql异常");
			e.printStackTrace();
		}
		return -1;
	}
	public int sex_sum(String sex) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from manager where msex='"+sex+"'";
        System.out.println(sql);
        ResultSet rs = null;
        int x =0;
        rs= dc.selectInfo(sql);
         try {
        	while (rs.next())
 			x=rs.getInt(1);
 			return x;
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			System.out.println("sql异常");
 			e.printStackTrace();
 		}
 		return -1;
	}
	
/*	public ArrayList<Place> findall(){
		// TODO Auto-generated method stub
		ArrayList<Place> places=new ArrayList<Place>();
		String sql = "select * from place ";
		ResultSet rs= null;
		rs = dc.selectInfo(sql);
		Place place = new Place();
		int i=0;
		try {
			while(rs.next()){
				place.setPid(rs.getInt(1+5*i));
				place.setPname(rs.getString(2+5*i));
				place.setPlongtitude(rs.getDouble(3+5*i));
				place.setPlatitude(rs.getDouble(4+5*i));
				place.setPinfo(rs.getString(5+5*i));			
			    i++;
			    
			    places.add(place);
			
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
	*/
}
