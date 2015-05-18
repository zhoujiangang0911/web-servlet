package com.exchange.impl;

import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.exchange.LoginActivity;
import com.exchange.RegisterActivity;
import com.exchange.service.ServiceRulesException;
import com.exchange.service.UserService;


public class UserServiceImpl implements UserService{
	private static final String TAG="UserServiceImpl";
	private String action=null;
    public	static String PATH="http://192.168.1.111:8080/xscj/servlet/Client";
    public  static String REGISTPAHT ="http://192.168.1.111:8080/xscj/";
	@Override
	public void userLogin(String loginName, String longinPassword)
			throws Exception {
		// TODO Auto-generated method stub
		Log.d(TAG, loginName);
		Log.d(TAG, longinPassword);
		action="login";
		
		HttpClient client=new DefaultHttpClient();
		int UserTypeState=LoginActivity.UserTypeFlag;
		//http://10.100.70.255:8080/xscj/servlet/Client?LoginName=tom&LoginPassword=123
		//"UserTypeState="+UserTypeState
		
		//用户名和密码 用户类型一起传给服务器。
		String uri=PATH+"?LoginName="+loginName+"&LoginPassword="+longinPassword+"&action="+action;
		HttpGet get=new HttpGet(uri);	
		HttpResponse response=client.execute(get);
		
		int ststusCode=response.getStatusLine().getStatusCode();
		if(ststusCode!=HttpStatus.SC_OK)
		{
			throw new ServiceRulesException(LoginActivity.MSG_SERVER_ERROR);
		}
		String result= EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
		Log.d(TAG, result);
		if(result.equals("success"))
		{
			
		}else{
			throw new ServiceRulesException(LoginActivity.MSG_LOGIN_FAILED);
		}
		
	}
	
	
	
	//@Override
//	public void userRegister(String registerMid, String registerMname,
//			String registerPassword, String registerMcollege,
//			String registerMage, String registerMsex, String registerMaddress,
//			String registerMphone) throws Exception {
//		// TODO Auto-generated method stub
//		Log.d(TAG, registerMname);
//	
//		action="register";
//		HttpClient client=new DefaultHttpClient();
//		//http://10.100.70.255:8080/xscj/servlet/Client?LoginName=tom&LoginPassword=123
//		String uri=PATH+"?registerMid="+registerMid+"&registerMname="+registerMname+"&registerPassword="//
//		           +registerPassword+"&registerMcollege="+registerMcollege+"&registerMage="+registerMage//
//		           +"&registerMsex="+registerMsex+"&registerMaddress="+registerMaddress+"&registerMphone="//
//		           +registerMphone+"&action="+action;//
//		HttpGet get=new HttpGet(uri);	
//		HttpResponse response=client.execute(get);
//		
//		int ststusCode=response.getStatusLine().getStatusCode();
//		if(ststusCode!=HttpStatus.SC_OK)
//		{
//			throw new ServiceRulesException(RegisterActivity.MSG_SERVER_ERROR);
//		}
//		String result= EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
//		Log.d(TAG, result);
//		if(result.equals("success"))
//		{
//			
//		}else{
//			throw new ServiceRulesException(RegisterActivity.MSG_LOGIN_FAILED);
//		}
//		
//		
//		
//		
//	}


	@Override
	public void userUpdate(String updateMid, String updateMname,
			String updatePassword, String updateMcollege, String updateMage,
			String updateMsex, String updateMaddress, String updateMphone)
			throws Exception {
		// TODO Auto-generated method stub
		Log.d(TAG, updateMname);
		
		action="update";
		HttpClient client=new DefaultHttpClient();
		//http://10.100.70.255:8080/xscj/servlet/Client?LoginName=tom&LoginPassword=123
		String uri=PATH+"?updateMid="+updateMid+"&updateMname="+updateMname+"&updatePassword="//
		           +updatePassword+"&updateMcollege="+updateMcollege+"&updateMage="+updateMage//
		           +"&updateMsex="+updateMsex+"&updateMaddress="+updateMaddress+"&updateMphone="//
		           +updateMphone+"&action="+action;//
		String s = URLEncoder.encode(uri, "utf-8");
		HttpGet get=new HttpGet(s);	
		HttpResponse response=client.execute(get);
		
		int ststusCode=response.getStatusLine().getStatusCode();
		if(ststusCode!=HttpStatus.SC_OK)
		{
			throw new ServiceRulesException(RegisterActivity.MSG_SERVER_ERROR);
		}
		String result= EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
		Log.d(TAG, result);
		if(result.equals("success"))
		{
			
		}else{
			throw new ServiceRulesException(RegisterActivity.MSG_LOGIN_FAILED);
		}
	
	}



	@Override
	public void userRegister(String registerMid, String registerMname,
			String registerPassword, String registerMcollege,
			String registerMage, String registerMsex, String registerMaddress,
			String registerMphone, String registerMphone2,
			String registerMaddress2) throws Exception {
		action="register?action=register";
		HttpClient client=new DefaultHttpClient();
		//http://10.100.70.255:8080/xscj/servlet/Client?LoginName=tom&LoginPassword=123
		String uri=REGISTPAHT+action+
					"&uid="+registerMid +
					"&uname="+registerMname +
					"&password="+registerPassword +
					"&sheng="+registerMcollege +
					"&usertype="+registerMage +
					"&college="+registerMsex +
					"&age="+registerMaddress +
					"&sex="+registerMphone +
					"&phone="+registerMphone2 +
					"&address="+registerMaddress2;
		HttpGet get=new HttpGet(uri);	
		HttpResponse response=client.execute(get);
		
		int ststusCode=response.getStatusLine().getStatusCode();
		System.out.println(ststusCode);
		Log.i("--zhou", "---"+ststusCode);
//		if(ststusCode!=HttpStatus.SC_OK)
//		{
//			throw new ServiceRulesException(RegisterActivity.MSG_SERVER_ERROR);
//		}
//		//String result= EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
//	//	Log.d(TAG, result);
//		//if(result.equals("success"))
//		//{
//			
//		//}else{
//			throw new ServiceRulesException(RegisterActivity.MSG_LOGIN_FAILED);
//		//}
		
		
		
	}



	
}
