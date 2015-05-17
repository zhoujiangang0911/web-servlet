package com.exchange.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.exchange.SelectActivity;
import com.exchange.model.Manager;
import com.exchange.model.Place;
import com.exchange.model.Status;

public class JsonParse {

    /**
     * 解析Json数据
     *
     * @param urlPath
     * @return mlists
     * @throws Exception
     */
	/**
     * 解析Json数据
     *
     * @param urlPath
     * @return mlists
     * @throws Exception
     */
    public static List<Place> getListPerson(String urlPath) throws Exception {
                    List<Place> mlists = new ArrayList<Place>();
                    String action="loadinfo";
                    byte[] data = readParse(urlPath,action);
                    JSONArray array = new JSONArray(new String(data));
            for (int i = 0; i < array.length(); i++) {
                    JSONObject item = array.getJSONObject(i);
                    int pid = item.getInt("pid");
                    String pname = item.getString("pname");
                    double plongtitude = item.getDouble("plongtitude");
                    double platitude = item.getDouble("platitude");
                    String pinfo = item.getString("pinfo");
                    
                    mlists.add(new Place(pid, pname, plongtitude, platitude, pinfo));
            }
            return mlists;
    }
    
    
    
    public static List<Manager> getListManager(String urlPath) throws Exception {

    	
        List<Manager> mlists=new ArrayList<Manager>(); 
        String action="userrequire";//"userupdate";
    	byte[] data = readParse(urlPath,action);
    	
        JSONArray array = new JSONArray(new String(data));
      
     for (int i = 0; i < array.length(); i++) {
    	 
        JSONObject item = array.getJSONObject(i);
        long mid = item.getInt("mid");
        String mname = item.getString("mname");
        String password = item.getString("password");
        int mcollege = item.getInt("mcollege");
        int mage = item.getInt("mage");
        String msex = item.getString("msex");
        String maddress = item.getString("maddress");
        String mphone = item.getString("mphone");
        
        mlists.add(new Manager(mid, mname, password, mcollege, mage, msex, maddress, mphone));
        }
        return mlists;
}
    
    
 public static List<Status> getStatusInfo(String urlPath) throws Exception {

    	
        List<Status> mlists=new ArrayList<Status>(); 
        String action="status";//"userupdate";
    	byte[] data = readParse(urlPath,action);
    	
        JSONArray array = new JSONArray(new String(data));
      
     for (int i = 0; i < array.length(); i++) {
    	 
        JSONObject item = array.getJSONObject(i);
        String status = item.getString("status");
       
        
        mlists.add(new Status(status));
        }
        return mlists;
}
    
    
    
    
    
    
    /**
     * 从指定的url中获取字节数组
     *
     * @param urlPath
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] readParse(String urlPath,String action) throws Exception {
     
    	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = 0;
            URL url ;
          if(urlPath.equals(SelectActivity.uri))
       {
        	  url = new URL(urlPath+"&action="+action);
        	 
       }else
       {
              url = new URL(urlPath+"?action="+action);
       }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    
            InputStream inStream = conn.getInputStream();
            while ((len = inStream.read(data)) != -1) {
                    outStream.write(data, 0, len);
            }
            inStream.close();
            return outStream.toByteArray();
    }
}

