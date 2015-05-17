package com.exchange.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Place implements Serializable{


	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public static  List<Place>  places = new ArrayList<Place>();
	private int pid;                           //地点id
	private String pname;                      //地点名
	private double plongtitude;                //经度
	private double platitude;                  //纬度
	private String pinfo;                      //地点信息
	
	public Place()
	{}
	
	public Place(int pid,String name,double longitude, double latitude, String info)
	{
//		this.imgId = imgId;          int imgId,
		this.pid=pid;
		this.pname = name;
		this.plongtitude = longitude;
		this.platitude = latitude;
		this.pinfo=info;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPlongtitude() {
		return plongtitude;
	}
	public void setPlongtitude(double plongtitude) {
		this.plongtitude = plongtitude;
	}
	public double getPlatitude() {
		return platitude;
	}
	public void setPlatitude(double platitude) {
		this.platitude = platitude;
	}
	public String getPinfo() {
		return pinfo;
	}
	public void setPinfo(String pinfo) {
		this.pinfo = pinfo;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Place [pid=" + pid + ",pname=" + pname + ",plongtitude=" + plongtitude + ",platitude=" + platitude + ",pinfo=" + pinfo + "]";
	}
	
}
