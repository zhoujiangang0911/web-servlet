package cn.rjtraining.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbconnect {
//����jdbc��mysql���ӵ�������com.mysql.jdbc.DriverΪ�̶���ʽ������ͨ��������������
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
//URL�ĸ�ʽ�ǹ̶��ģ�jdbc:mysql://���ݿ����ڵ��Ե�ip��ַ��mysql�˿ں�/����ͨ��NavicateΪ�ó��򴴽������ݿ������
	private static final String URL = "jdbc:mysql://localhost:3306/myserver";
//���ݿ���û���
	private static final String USER = "root";
//�ڴ������ݿ�ʱ�����Ǹ���������
	private static final String PASSWORD = "java";

	private   Connection con;
	private  Statement st;
	private  ResultSet rs;

	/**
	 * �õ����Ӷ���
	 */
	public void getConn() {
		try {
			Class.forName(CLASSNAME);
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				System.out.println("MySQL�����쳣");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("���������쳣");
		}
	}
/**
 * ִ��DQL���
 * @param ��select��ͷ��sql���
 * @return �����
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
	 * ִ��DML���
	 * @param DML��sql���
	 * @return �����͵����֣������ɹ����ش���0�����򷵻�0��
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
	 * �ر����еĶ���
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
			System.out.println("�����쳣");
		}
	}
}
