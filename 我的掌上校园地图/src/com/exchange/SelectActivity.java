package com.exchange;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.map.R;
import com.exchange.impl.JsonParse;
import com.exchange.impl.UserServiceImpl;
import com.exchange.model.Manager;
import com.exchange.model.Status;
import com.exchange.service.ServiceRulesException;

public class SelectActivity extends Activity {

	private Button btn_my_info;
	private Button btn_using_status;
	private Button btn_start_map;
	private Button btn_exit_map;
	private Button btn_user_district;
	private static ProgressDialog dialog;
	private static final int FLAG_LOGIN_SUCCESS=1;
	private static final String MSG_LOGIN_ERROE="查询出错";
	private static final String MSG_LOGIN_SUCCESS="查询成功";
	public static final String  MSG_LOGIN_FAILED="连接出错！";
	public static final String  MSG_SERVER_ERROR="服务器响应出错！";
	public static String uri;
	static final int REQUEST_CODE=0;
	private static long id;
	public static List<Manager> managers = new ArrayList<Manager>();
	public static List<Status> statuses = new ArrayList<Status>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_activity);
		init();
		addListener();
		
	}
	private void addListener() {
		btn_user_district.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SelectActivity.this, UserDistrictActivity.class);
				startActivity(intent);
			}
		});
		
		btn_using_status.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Thread  thread=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try{
							Intent intent = new Intent();
							 statuses = JsonParse.getStatusInfo(UserServiceImpl.PATH);
							 Status m;

                             m=statuses.get(0);
                                    
                       		    intent.setClass(SelectActivity.this, UsingStatusActivity.class);
                       			
                       			Bundle bundle=new Bundle();
                       			bundle.putString("STATUS", m.getStatus());
                       			intent.putExtras(bundle);	
                       		    startActivity(intent);

						}catch(ServiceRulesException e){
							e.printStackTrace();
							Message msg=new Message();
							Bundle data=new Bundle();
							data.putSerializable("ErrorMsg",e.getMessage());
							msg.setData(data);
						    handler.sendMessage(msg);
						}catch(Exception e)
						{
							e.printStackTrace();
							Message msg=new Message();
							Bundle data=new Bundle();
							data.putSerializable("ErrorMsg",MSG_LOGIN_ERROE);
							msg.setData(data);
						    handler.sendMessage(msg);
						}
					}
				});
				thread.start();
				
				
			}
		});
		btn_start_map.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				     Intent intent = new Intent();
					 intent.setClass(SelectActivity.this, MapActivity.class);
					 startActivity(intent);
			}
		});
			
		btn_exit_map.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SelectActivity.this.finish();
			}
		});
		btn_my_info.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle=SelectActivity.this.getIntent().getExtras();
				
				String current_user_id=bundle.getString("CURRENT_USER_ID");
				id=Integer.parseInt(current_user_id);
				 
				if(dialog==null)
					{
						dialog = new ProgressDialog(SelectActivity.this);
					}
					dialog.setTitle("请等待");
					dialog.setMessage("查询中...");
					dialog.setCancelable(false);
					dialog.show();
					
					Thread  thread=new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try{
								uri=UserServiceImpl.PATH+"?userId="+id;
								Intent intent = new Intent();
    							 managers = JsonParse.getListManager(uri);
    							 Manager m;

                                 m=managers.get(0);
                                        
                           		    intent.setClass(SelectActivity.this, MyInfoActivity.class);
                           			
                           			Bundle bundle=new Bundle();
                           			bundle.putLong("MY_ID", m.getMid());
                           			bundle.putString("MY_NAME", m.getMname());
                           			bundle.putString("MY_PASSWORD", m.getPassword());
                           			bundle.putInt("MY_COLLEGE", m.getMcollege());
                           			bundle.putInt("MY_AGE", m.getMage());
                           			bundle.putString("MY_SEX", m.getMsex());
                           			bundle.putString("MY_ADDRESS", m.getMaddress());
                           			bundle.putString("MY_PHONE", m.getMphone());
                           			
                           			intent.putExtras(bundle);	
                           			dialog.cancel();
                           		    startActivity(intent);
							}catch(ServiceRulesException e){
								e.printStackTrace();
								Message msg=new Message();
								Bundle data=new Bundle();
								data.putSerializable("ErrorMsg",e.getMessage());
								msg.setData(data);
							    handler.sendMessage(msg);
							}catch(Exception e)
							{
								e.printStackTrace();
								Message msg=new Message();
								Bundle data=new Bundle();
								data.putSerializable("ErrorMsg",MSG_LOGIN_ERROE);
								msg.setData(data);
							    handler.sendMessage(msg);
							}
						}
					});
					thread.start();
				
				
				
			}
		});
	}
	private void init() {
		btn_using_status=(Button) findViewById(R.id.app_using_status);
		btn_start_map=(Button) findViewById(R.id.school_map);
		btn_exit_map=(Button) findViewById(R.id.exit_app);
		btn_my_info=(Button) findViewById(R.id.my_info);
		btn_user_district = (Button) findViewById(R.id.btn_userdistrict);
	}
	
	void showTip(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
	
	private static class IHandler extends Handler{

		private final WeakReference<Activity> mActivity;
		public IHandler(SelectActivity activity)
		{
			mActivity=new WeakReference<Activity>(activity);
		}
		@Override
		public void handleMessage(Message msg) {
			if(dialog!=null)
			{
				dialog.dismiss();
			}
			int flag=msg.what;
			switch (flag) {
			case 0:
				String errorMsg=(String) msg.getData().getSerializable("ErrorMsg");
				((SelectActivity)mActivity.get()).showTip(errorMsg);
				break;

			case FLAG_LOGIN_SUCCESS:
				((SelectActivity)mActivity.get()).showTip(MSG_LOGIN_SUCCESS);
				break;
				
			default:
				break;
			}
			
			
		}
		
	}
	
	private IHandler handler=new IHandler(this);
	

}
