package com.exchange.model;
import java.io.Serializable;
public class Manager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long mid; // ѧ��
	private String mname; // �û�����
	private String password; // �û�����
	private int mcollege; // ѧԺ
	private int  mage; // �û�����
	private String  msex; // �û�����
	private String maddress; // �û�סַ
	private String mphone; // �绰����
	
	public Manager()
	{
		
	}
	public Manager(long mid, String mname, String password, int mcollege,
			int mage, String msex, String maddress, String mphone) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.password = password;
		this.mcollege = mcollege;
		this.mage = mage;
		this.msex = msex;
		this.maddress = maddress;
		this.mphone = mphone;
	}
	
	
	public String getMsex() {
		return msex;
	}
	public void setMsex(String msex) {
		this.msex = msex;
	}

	
	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMcollege() {
		return mcollege;
	}
	public void setMcollege(int mcollege) {
		this.mcollege = mcollege;
	}
	public int getMage() {
		return mage;
	}
	public void setMage(int mage) {
		this.mage = mage;
	}
	public String getMaddress() {
		return maddress;
	}
	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	
	

	
}
