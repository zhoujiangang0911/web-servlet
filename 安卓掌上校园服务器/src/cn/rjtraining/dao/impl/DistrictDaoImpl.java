package cn.rjtraining.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.rjtraining.dao.DistrictDao;
import cn.rjtraining.jdbc.Dbconnect;
import cn.rjtraining.model.District;

public class DistrictDaoImpl implements DistrictDao{
	Dbconnect dc = new Dbconnect();
	@Override
	public District findDistrictById(int id) {
		District district = new District();
		String sql = "select * from district where id=" + id;
		System.out.println(sql);
		ResultSet rs = null;
		rs = dc.selectInfo(sql);
		try {
			while(rs.next()){
				district.setId(rs.getInt(1));
				district.setPid(rs.getInt(2));
				district.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("根据ID差城市错误");
			return null;
		}
		
		return district;
	}

	@Override
	public District findDistrictByPid(int Pid) {
		return null;
	}

	@Override
	public List<District> findDistrictByChild(int prentid) {
		return null;
	}

	@Override
	public List<District> findDistrictByAllChild(int prentid) {
		return null;
	}

	@Override
	public List<District> findDistrictByAllSheng() {
		
		
		List<District> list = list = new ArrayList<>();
		String sql = "SELECT * FROM district WHERE id < 35 " ;
		ResultSet rs = null;
		int a = 0;
		rs = dc.selectInfo(sql);
		try {
			while(rs.next()){
				District district = new District();
				district.setId(rs.getInt(1));
				district.setPid(rs.getInt(2));
				district.setName(rs.getString("name"));
				list.add(district);
			}
			System.out.println(list.get(3).getName());
			System.out.println(list.get(4).getName());
			System.out.println(list.get(5).getName());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
