package cn.rjtraining.model;

import java.io.Serializable;

public class District implements  Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8244502650497725159L;

	private int id;
	
	private int pid;
	private String name;
	
	private double dlongtitude;
	private double dlatitude;
	
	public double getDlongtitude() {
		return dlongtitude;
	}
	public void setDlongtitude(double dlongtitude) {
		this.dlongtitude = dlongtitude;
	}
	public double getDlatitude() {
		return dlatitude;
	}
	public void setDlatitude(double dlatitude) {
		this.dlatitude = dlatitude;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
