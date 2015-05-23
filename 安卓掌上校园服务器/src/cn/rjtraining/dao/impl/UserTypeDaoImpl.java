package cn.rjtraining.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.rjtraining.dao.UserTypeDao;
import cn.rjtraining.jdbc.Dbconnect;
import cn.rjtraining.model.UserType;

public class UserTypeDaoImpl implements UserTypeDao{
	Dbconnect dc = new Dbconnect();
	@Override
	public List<UserType> findUserTypeAllType() {
		List<UserType> list = list = new ArrayList<UserType>();
		String sql = "select * from usertype";
		ResultSet rs = null;
		rs = dc.selectInfo(sql);
		try {
			while(rs.next()){
				UserType ut = new UserType();
				ut.setUsertypeid(rs.getInt(1));
				ut.setUsertypename(rs.getString("usertypename"));
				list.add(ut);
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("根据ID差城市错误");
			return null;
		}
		
		return list;
		
	}

}
