package cn.rjtraining.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import cn.rjtraining.dao.UserDao;
import cn.rjtraining.jdbc.Dbconnect;
import cn.rjtraining.model.User;
public class UserDaoImpl implements UserDao {
	Dbconnect dc=new Dbconnect();
	
	public User find(int id) {
		String sql = "select * from user where uid="+id;
		ResultSet rs= null;
		rs = dc.selectInfo(sql);
		User user = new User();
		try {
			while(rs.next()){
				user.setUid(rs.getInt(1));
				user.setUname(rs.getString(2));
				user.setPassword(rs.getString(3));			
			}
		} catch (SQLException e) {
			return null;
		}
		if(user.getUid()!=0){
			return user;
		}else{
			return null;
		}
	}
	public boolean update(User user) {
		int id=user.getUid();
		String uname=user.getUname();
		String psw=user.getPassword();
		String sql ="update user set uname='"+uname+"', password='"+psw+"' where uid="+id;
		System.out.println(sql);
		int x=dc.noSelectInfo(sql);
		if(x<=0){
			return false;
		}else{
			return true;
		}
	}
	public int insert(User user) {
		String sql="insert into user values("+user.getUid()+",'"+user.getUname()+"','"+user.getPassword()+"')";	
		int x=dc.noSelectInfo(sql);
		return x;
	}
	public boolean deleteById(int id) {
		String sql="delete from user where uid="+id;
		System.out.println(sql);
		int x = dc.noSelectInfo(sql);
		if(x>0){
			return true;
		}else{
		return false;
		}
	}
	public int usersum() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from user";
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
}
