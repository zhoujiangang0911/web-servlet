package cn.rjtraining.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	
	public static void main(String[] args) {
		//定义jdbc和mysql连接的驱动，com.mysql.jdbc.Driver为固定格式，可以通过网络搜索到。
		final String CLASSNAME = "com.mysql.jdbc.Driver";
	//URL的格式是固定的，jdbc:mysql://数据库所在电脑的ip地址：mysql端口号/我们通过Navicate为该程序创建的数据库的名称
		final String URL = "jdbc:mysql://localhost:3306/myserver";
	//数据库的用户名
		final String USER = "root";
	//在创建数据库时，我们给定的密码
		final String PASSWORD = "123456";

		Connection con;
		Statement st;
		ResultSet rs;

		/**
		 * 得到链接对象
		 */
		
			try {
				Class.forName(CLASSNAME);
				try {
					con = DriverManager.getConnection(URL, USER, PASSWORD);
					st = con.createStatement();
					String sql = "select * from district where cid=" + 1;
					rs = st.executeQuery(sql);
					
					System.out.println(rs.getString(2));
				} catch (SQLException e) {
					System.out.println("MySQL加载异常");
				}
			} catch (ClassNotFoundException e) {
				System.out.println("驱动加载异常");
			}
			
			
			
			
			
		
		
	}
}
