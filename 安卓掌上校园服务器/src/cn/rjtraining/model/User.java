package cn.rjtraining.model;
import java.io.Serializable;
public class User implements Serializable {
	private long uid; // 用户账号
	private String uname; // 用户名称
	private String password; // 用户密码
	private int districtid;
	private int usertypeid;
	private int collegeid;
	private int age;
	private String sex;
	private String phone;
	private String address;
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
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
	public int getDistrictid() {
		return districtid;
	}
	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}
	public int getUsertypeid() {
		return usertypeid;
	}
	public void setUsertypeid(int usertypeid) {
		this.usertypeid = usertypeid;
	}
	public int getCollegeid() {
		return collegeid;
	}
	public void setCollegeid(int collegeid) {
		this.collegeid = collegeid;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User(long uid, String uname, String password, int districtid,
			int usertypeid, int collegeid, int age, String sex, String phone,
			String address) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.password = password;
		this.districtid = districtid;
		this.usertypeid = usertypeid;
		this.collegeid = collegeid;
		this.age = age;
		this.sex = sex;
		this.phone = phone;
		this.address = address;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
