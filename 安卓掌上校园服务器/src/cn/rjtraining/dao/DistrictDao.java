package cn.rjtraining.dao;

import java.util.List;

import cn.rjtraining.model.District;

public interface DistrictDao {
	
	
	
	District findDistrictById(int id);
	
	District findDistrictByPid(int Pid);
	
	List<District> findDistrictByChild(int prentid);
	
	
	List<District> findDistrictByAllChild(int prentid);
	
	List<District> findDistrictByAllSheng();
	
	
}
