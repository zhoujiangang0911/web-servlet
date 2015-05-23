package cn.rjtraining.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.rjtraining.dao.CollegeDao;
import cn.rjtraining.jdbc.Dbconnect;
import cn.rjtraining.model.College;
import cn.rjtraining.model.Page;

public class CollegeDaoImpl implements CollegeDao {
	Dbconnect dc = new Dbconnect();

	public boolean deleteById(int cid) {
		String sql = "delete from college where cid=" + cid;
		System.out.println(sql);
		int x = dc.noSelectInfo(sql);
		if (x > 0) {
			return true;
		} else {
			return false;
		}
	}
	


	public College find(int cid) {
		String sql = "select * from college where cid=" + cid;
		System.out.println(sql);
		ResultSet rs = null;
		rs = dc.selectInfo(sql);
		College college = new College();
		try {
			while (rs.next()) {
				college.setCid(rs.getInt(1));
				college.setCname(rs.getString(2));
				college.setCleader(rs.getString(3));
				college.setCmajorsum(rs.getInt(4));
				college.setCbanjisum(rs.getInt(5));
				college.setCinfo(rs.getString(6));
			}
		} catch (SQLException e) {
			return null;
		}
		if (college.getCid() != 0) {
			return college;
		} else {
			return null;
		}
	}

	public int insert(College college) {
		String sql = "insert into college values(" + college.getCid() + ",'"
				+ college.getCname() + "','" + college.getCleader() + "',"+college.getCmajorsum()+","+college.getCbanjisum()+",'"
				+ college.getCinfo() + "')";
		System.out.println(sql);
		int x = dc.noSelectInfo(sql);
		return x;
	}

	public boolean update(College college) {
		int cid = college.getCid();
		String cname = college.getCname();
		String cleader = college.getCleader();
		int cmajorsum =college.getCmajorsum();
		int cbanjisum =college.getCbanjisum();
		String cinfo = college.getCinfo();
		String sql = "update college set cname='" + cname + "', cleader='" + cleader+"',cmajorsum="+cmajorsum+",cbanjisum="+cbanjisum
				+ ",cinfo='" + cinfo + "' where cid=" + cid;
		System.out.println(sql);
		int x = dc.noSelectInfo(sql);
		if (x <= 0) {
			return false;
		} else {
			return true;
		}
	}

	public Page search(int pageNow) {
		Page page = new Page();
		List<College> list = new ArrayList<College>();
		ResultSet rs = null;
		String sql = "select * from college limit " + (pageNow - 1)*page.getPageSize() + ", "
				+ page.getPageSize();
		String sql1 = "select count(*) from college";
		rs = dc.selectInfo(sql1);
		try {
			while (rs.next()) {
				page.setRowCount(rs.getInt(1));
				page.setPageCount(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("计算rowcount出错");
		}
		rs = dc.selectInfo(sql);
		try {
			while (rs.next()) {
				College college = new College();
				college.setCid(rs.getInt(1));
				college.setCname(rs.getString(2));
				college.setCleader(rs.getString(3));
				college.setCmajorsum(rs.getInt(4));
				college.setCbanjisum(rs.getInt(5));
				college.setCinfo(rs.getString(6));
				list.add(college);
			}
		} catch (SQLException e) {
			System.out.println("sql语句异常");
		}
		page.setPageNow(pageNow);
		page.setDatas(list);
		return page;
	}

	public Page search(String type, String key, int pageNow) {
		ResultSet rs = null;
		List<College> list = new ArrayList<College>();
		Page page = new Page();
		page.setPageNow(pageNow);
		String sql = "select * from college";
		if (key != null) {
			if (type.equals("cid") || type.equals("ctype")
					|| type.equals("status")) {
				sql = sql + " where " + type + "=" + key + " limit "
						+ (page.getPageNow() - 1)*page.getPageSize() + "," + page.getPageSize();
			} else {
				sql = sql + " where " + type + " like '%" + key + "%' limit "
						+ (page.getPageNow() - 1)*page.getPageSize() + "," + page.getPageSize();
			}
			System.out.println(sql);
		}
		int x = 0;// 存储我们共查出了多少条数据
		rs = dc.selectInfo(sql);
		try {
			while (rs.next()) {
				College college = new College();
				college.setCid(rs.getInt(1));
				college.setCname(rs.getString(2));
				college.setCleader(rs.getString(3));
				college.setCmajorsum(rs.getInt(4));
				college.setCbanjisum(rs.getInt(5));
				college.setCinfo(rs.getString(6));
				list.add(college);
				x++;
			}
		} catch (SQLException e) {

		}
		page.setRowCount(x);
		page.setPageCount(x);
		page.setDatas(list);
		return page;
	}

	public List<College> searchForStu(int status) {
		ResultSet rs = null;
		String sql = "select * from college where status=" + status;
		System.out.println(sql);
		List<College> list = new ArrayList<College>();
		rs = dc.selectInfo(sql);
		try {
			while (rs.next()) {
				College college = new College();
				college.setCid(rs.getInt(1));
				college.setCname(rs.getString(2));
				college.setCleader(rs.getString(3));
				college.setCmajorsum(rs.getInt(4));
				college.setCbanjisum(rs.getInt(5));
				college.setCinfo(rs.getString(6));
				list.add(college);
			}
		} catch (SQLException e) {
			System.out.println("sql异常");
		}
		return list;
	}



	public int collegesum() {
		
		String sql = "select count(*) from college";
        System.out.println(sql);
        ResultSet rs = null;
        int x=0 ;
        rs= dc.selectInfo(sql);
         try {
        	while (rs.next())
 			x=rs.getInt(1);
 			return x;
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			System.out.println("sql寮傚父");
 			e.printStackTrace();
 		}
 		return -1;
	}



	public int majorsum() {
		
		String sql = "select sum(cmajorsum) from college";
        System.out.println(sql);
        ResultSet rs = null;
        int x = 0;
        rs= dc.selectInfo(sql);
         try {
        	while (rs.next())
 			x=rs.getInt(1);
 			return x;
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			System.out.println("sql寮傚父");
 			e.printStackTrace();
 		}
 		return -1;
	}



	public int banjisum() {
		// TODO Auto-generated method stub
		
		String sql = "select sum(cbanjisum) from college";
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
 			System.out.println("sql寮傚父");
 			e.printStackTrace();
 		}
 		return -1;
	}



	@Override
	public List<College> findAllCollege() {
		String sql = "select *  from college";
        System.out.println(sql);
        ResultSet rs = null;
        int x =0;
        rs= dc.selectInfo(sql);
        List<College> ls  = new ArrayList<College>();
        try {
       	 while (rs.next()){
       		 College c  =new College();
			 c.setCid(rs.getInt(1));
			 c.setCname(rs.getString("cname"));
			 c.setCleader(rs.getString("cleader"));
			 c.setCmajorsum(rs.getInt(4));
			 c.setCbanjisum(rs.getInt(5));
			 c.setCinfo(rs.getString("cinfo"));
			 ls.add(c);
			
       	 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sql执行错误");
			e.printStackTrace();
		}
		
		return ls;
	}
	
	
}
