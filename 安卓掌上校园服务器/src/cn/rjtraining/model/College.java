package cn.rjtraining.model;

import java.io.Serializable;

public class College implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cid;   //ѧԺ���
	private String cname; 	//ѧԺ����
	private String cleader; //Ժ��
	private int cmajorsum;	//רҵ��Ŀ
	private int cbanjisum; //�༶��Ŀ
	private String cinfo;  //ѧԺ��Ϣ
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
