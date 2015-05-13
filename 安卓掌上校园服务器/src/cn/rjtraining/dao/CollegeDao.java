package cn.rjtraining.dao;

import java.util.List;

import cn.rjtraining.model.College;
import cn.rjtraining.model.Page;


public interface CollegeDao {

	Page search(int x);

	College find(int parseInt);

	boolean deleteById(int id);

	int insert(College ban);

	Page search(String type, String key, int x);

	boolean update(College banji);
	
	int collegesum();
	
	int majorsum();
	
	int banjisum();

	List<College> searchForStu(int i);
	
}
