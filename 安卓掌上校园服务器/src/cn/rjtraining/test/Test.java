package cn.rjtraining.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	
	public static void main(String[] args) {
		//����jdbc��mysql���ӵ�������com.mysql.jdbc.DriverΪ�̶���ʽ������ͨ��������������
		final String CLASSNAME = "com.mysql.jdbc.Driver";
	//URL�ĸ�ʽ�ǹ̶��ģ�jdbc:mysql://���ݿ����ڵ��Ե�ip��ַ��mysql�˿ں�/����ͨ��NavicateΪ�ó��򴴽������ݿ������
		final String URL = "jdbc:mysql://localhost:3306/myserver";
	//���ݿ���û���
		final String USER = "root";
	//�ڴ������ݿ�ʱ�����Ǹ���������
		final String PASSWORD = "123456";

		Connection con;
		Statement st;
		ResultSet rs;

		/**
		 * �õ����Ӷ���
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
					System.out.println("MySQL�����쳣");
				}
			} catch (ClassNotFoundException e) {
				System.out.println("���������쳣");
			}
			
			
			
			
			
		
		
	}
}
