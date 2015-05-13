package cn.rjtraining.model;

import java.io.Serializable;

public class College implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cid;   //学院编号
	private String cname; 	//学院名称
	private String cleader; //院长
	private int cmajorsum;	//专业数目
	private int cbanjisum; //班级数目
	private String cinfo;  //学院信息
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCleader() {
		return cleader;
	}
	public void setCleader(String cleader) {
		this.cleader = cleader;
	}
	public int getCmajorsum() {
		return cmajorsum;
	}
	public void setCmajorsum(int cmajorsum) {
		this.cmajorsum = cmajorsum;
	}
	public int getCbanjisum() {
		return cbanjisum;
	}
	public void setCbanjisum(int cbanjisum) {
		this.cbanjisum = cbanjisum;
	}
	public String getCinfo() {
		return cinfo;
	}
	public void setCinfo(String cinfo) {
		this.cinfo = cinfo;
	}
	
	
}
