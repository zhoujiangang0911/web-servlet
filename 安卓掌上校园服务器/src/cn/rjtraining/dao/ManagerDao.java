package cn.rjtraining.dao;
import java.util.ArrayList;

import cn.rjtraining.model.Manager;
import cn.rjtraining.model.Page;
import cn.rjtraining.model.Place;
public interface ManagerDao {
/*
 * 在user表中有些什么操作？
 * 1、登陆的时候，要访问user表
 * 2、修改用户密码的时候要访问user 表
 * 3、每次添加学生或者老师的时候，都要同时在user表中添加数据。
 * 4、删除user 表的信息
*/
/**
 * 查询user表中是否存在某用户
 * @param id 用户的id，如果是学生，id就表示学号，如果是老师，id 就表示工号。
 * @return 如果存在此用户，返回此用户，否则返回空值
 */
	Manager find(long id);
/**
 * 修改用户的信息，包括登陆密码、用户名、角色。
 * @param user 一个实体
 * @return 如果修改成功返回true，反之，返回false。
 */
//	ArrayList<Place> findall();
	boolean update(Manager manager);
/**
 * 向用户表中添加数据
 * @param user 一个完整的实体。
 * @return 添加成功返回1，否则返回0
*/
	Page search(int pageNow);
	/**
	 * 执行信息的查询功能
	 * @param type 告诉服务器，以什么作为查询的条件
	 * @param key  告诉服务器，查询的关键词
	 * @return  返回一个page，显示在我们的窗口中
	 */
	Page search(String type,String key,int pageNow);
	
	int insert(Manager manager);
/**
 * 根据Id删除用户表中的信息
 * @param id 用户的账号信息
 * @return  删除成功返回true，否则false
*/
	boolean deleteById(int id);
	
	int managersum();
	
	int sex_sum(String sex);
}
//该接口规定了和用户有关的方法。是我们程序条理化的重要组成部分，接口的书写是能够体现一个程序员对整个程序把握性的能力。
