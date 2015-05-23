package cn.rjtraining.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.rjtraining.dao.PlaceDao;
import cn.rjtraining.jdbc.Dbconnect;
import cn.rjtraining.model.Page;
import cn.rjtraining.model.Place;


public class PlaceDaoImpl implements PlaceDao {
	Dbconnect dc=new Dbconnect();
	
	public Place find(int id) {
		String sql = "select * from place where pid="+id;
		ResultSet rs= null;
		rs = dc.selectInfo(sql);
		Place place = new Place();
		try {
			while(rs.next()){
//				int x =  rs.getInt(1);
//				user.setUid(x);
				place.setPid(rs.getInt(1));
				place.setPname(rs.getString(2));
				place.setPlongtitude(rs.getDouble(3));
				place.setPlatitude(rs.getDouble(4));
				place.setPinfo(rs.getString(5));
//				user.setRole(rs.getString(4));				
			}
		} catch (SQLException e) {
			return null;
		}
		if(place.getPid()!=0){
			return place;
		}else{
			return null;
		}
	}
	public boolean update(Place place) {
		int id=place.getPid();
		String pname=place.getPname();
		double plongtitude=place.getPlongtitude();
		double platitude=place.getPlatitude();
		String pinfo=place.getPinfo();
		String sql ="update place set pname='"+pname+"',plongtitude ="+plongtitude+",platitude ="+platitude+",pinfo ='"+pinfo+"' where pid="+id;
		System.out.println(sql);
		int x=dc.noSelectInfo(sql);
		if(x<=0){
			return false;
		}else{
			return true;
		}
	}
	
	public int insert(Place place) {
		String sql="insert into place values("+place.getPid()+",'"+place.getPname()+"',"+place.getPlongtitude()+","+place.getPlatitude()+",'"+place.getPinfo()+"')";	
		int x=dc.noSelectInfo(sql);
		return x;
	}
	public boolean deleteById(int id) {
		String sql="delete from place where pid="+id;
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
		List<Place> list = new ArrayList<Place>();
		ResultSet rs = null;
		String sql = "select * from place limit " + (pageNow - 1)
				* page.getPageSize() + ", " + page.getPageSize();
		String sql1 = "select count(*) from place";
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
				Place place = new Place();
				place.setPid(rs.getInt(1));
				place.setPname(rs.getString(2));
				place.setPlongtitude(rs.getDouble(3));
				place.setPlatitude(rs.getDouble(4));
				place.setPinfo(rs.getString(5));
				list.add(place);
			}
		} catch (SQLException e) {
			System.out.println("sql语句异常");
		}
		page.setPageNow(pageNow);
		page.setDatas(list);
		return page;
	}
/*	
	public Page search(String type, String key, int pageNow) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		String sqll = "select count(*) from place";
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
				manager.setMid(rs.getInt(1));
				manager.setMname(rs.getString(2));
				manager.setPassword(rs.getString(3));
				list.add(manager);
			}
		} catch (SQLException e) {
			System.out.println("sql异常");
		}
		page.setDatas(list);
		return page;
	}
	*/
	public Page search(String type, String key, int pageNow) {
		// TODO Auto-generated method stub
		return null;
	}
	public int placesum() {
		// TODO Auto-generated method stub
		
		String sql = "select count(*) from place";
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
	@Override
	public List<Place> findAllPlace() {
		List<Place> ls = new ArrayList<Place>();
		
		String sql = "select * from place";
		 ResultSet rs = null;
	        int x =0;
	        rs= dc.selectInfo(sql);
	        try {
	        	while (rs.next()){
	        		Place pl = new Place();
	        		pl.setPid(rs.getInt(1));
	        		pl.setPname(rs.getString("pname"));
	        		pl.setPlongtitude(rs.getDouble(3));
	        		pl.setPlatitude(rs.getDouble(4));
	        		pl.setPinfo(rs.getString("pinfo"));
	        		ls.add(pl);
	        	}
	 			
	 		} catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			System.out.println("sql����");
	 			e.printStackTrace();
	 		}
		
		return ls;
	}
}
