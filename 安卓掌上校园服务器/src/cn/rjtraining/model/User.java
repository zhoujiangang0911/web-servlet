package cn.rjtraining.model;
import java.io.Serializable;
public class User implements Serializable {
	private int uid; // 用户账号
	private String uname; // 用户名称
	private String password; // 用户密码
//	private String role; // 用户角色
	private int districtid;
	
	private int usertypeid;
	
	public int getUsertypeid() {
		return usertypeid;
	}
	public void setUsertypeid(int usertypeid) {
		this.usertypeid = usertypeid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getDistrictid() {
		return districtid;
	}
	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
/*	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	*/
}
