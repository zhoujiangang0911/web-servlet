package cn.rjtraining.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbconnect {
//定义jdbc和mysql连接的驱动，com.mysql.jdbc.Driver为固定格式，可以通过网络搜索到。
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
//URL的格式是固定的，jdbc:mysql://数据库所在电脑的ip地址：mysql端口号/我们通过Navicate为该程序创建的数据库的名称
	private static final String URL = "jdbc:mysql://localhost:3306/myserver";
//数据库的用户名
	private static final String USER = "root";
//在创建数据库时，我们给定的密码
	private static final String PASSWORD = "java";

	private   Connection con;
	private  Statement st;
	private  ResultSet rs;

	/**
	 * 得到链接对象
	 */
	public void getConn() {
		try {
			Class.forName(CLASSNAME);
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				System.out.println("MySQL加载异常");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载异常");
		}
	}
/**
 * 执行DQL语句
 * @param 以select开头的sql语句
 * @return 结果集
 */
	public ResultSet selectInfo(String sql) {
		getConn();	
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
		} catch (Exception e) {
			close();
		}
		return rs;
	}

	/**
	 * 执行DML语句
	 * @param DML的sql语句
	 * @return 整数型的数字，操作成功返回大于0，否则返回0；
	 	 */
	public int noSelectInfo(String sql) {
		getConn();
		int result = 0;
		try {
			st = con.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			close();
		}
		return result;
	}
	
	/**
	 * 关闭所有的对象
	 */
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			System.out.println("出现异常");
		}
	}
}
