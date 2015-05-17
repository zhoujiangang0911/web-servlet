package com.exchange;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.baidu.map.R;
import com.exchange.impl.UserServiceImpl;
import com.exchange.service.ServiceRulesException;
import com.exchange.service.UserService;

public class LoginActivity extends Activity {
	public String CurrentName;                 //记录当前登陆者用户号
	public String CurrentPassword;             //记录当前登陆者密码
	
	private EditText txtLoginName;
	private EditText txtLoginPassword;
	private Button btnLogin;
	private Button btnReset;
	private Button btnRegister;
	private RadioButton btnRadio_schoolin_person;
	private RadioButton btnRadio_schoolout_person;
	//默认选中校内师生
	public static int UserTypeFlag=0;
	
	private UserService userService=new UserServiceImpl();
	private static final int FLAG_LOGIN_SUCCESS=1;
	private static final String MSG_LOGIN_ERROE="登录出错";
	private static final String MSG_LOGIN_SUCCESS="登录成功";
	public static final String  MSG_LOGIN_FAILED="登录名/登录密码出错！";
	public static final String  MSG_SERVER_ERROR="服务器响应出错！";
	private static ProgressDialog dialog;
	static final int REQUEST_CODE=0;
	
	public void init()
	{
		this.txtLoginName=(EditText) findViewById(R.id.edit_name);
		this.txtLoginPassword=(EditText) findViewById(R.id.edit_password);
		this.btnLogin=(Button) findViewById(R.id.btnOk);
		this.btnReset=(Button) findViewById(R.id.btnReset);
		this.btnRegister=(Button) findViewById(R.id.btnRegister);
		this.btnRadio_schoolin_person=(RadioButton)findViewById(R.id.shcoolin_person);
		this.btnRadio_schoolout_person=(RadioButton)findViewById(R.id.shcoolout_person);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		this.init();
		
		this.btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		
				final	String  loginName=txtLoginName.getText().toString().trim();
				final	String  loginPassword=txtLoginPassword.getText().toString().trim();			
				if(TextUtils.isEmpty(loginName) || TextUtils.isEmpty(loginPassword)){
					Toast.makeText(getApplicationContext(), "用户名不能为空！", Toast.LENGTH_SHORT).show();
					return;
				}
				
				if (dialog == null) {
					dialog = new ProgressDialog(LoginActivity.this);
				}
				dialog.setTitle("请等待");
				dialog.setMessage("登录中...");
				dialog.setCancelable(false);
				dialog.show();
//				new AsyncTask<Void, Void, Void>() {
//					@Override
//					protected void onPreExecute() {
//						super.onPreExecute();
//					}
//					@Override
//					protected Void doInBackground(Void... arg0) {
//						return null;
//					}
//					@Override
//					protected void onPostExecute(Void result) {
//						super.onPostExecute(result);
//						if (dialog != null) {
//							dialog.cancel();
//						}
//					}
//				};
				Thread  thread=new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try{
							//验证用户名和密码的正确性
							userService.userLogin(loginName, loginPassword);
							handler.sendEmptyMessage(FLAG_LOGIN_SUCCESS);
							CurrentName=loginName;
							CurrentPassword=loginPassword;
							 Intent intent = new Intent();
							 Bundle bundle=new Bundle();
							 bundle.putString("CURRENT_USER_ID", loginName);
							 
							 intent.putExtras(bundle);	
							 intent.setClass(LoginActivity.this, SelectActivity.class);
							 startActivity(intent);
						     finish();
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
		
		this.btnReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtLoginName.setText("");
				txtLoginPassword.setText("");
				btnRadio_schoolin_person.setChecked(true);
				
				startActivity(new Intent(LoginActivity.this, AChartActivity.class));
			}
		});
		
		this.btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getBaseContext(),RegisterActivity.class);			
				LoginActivity.this.startActivityForResult(intent,REQUEST_CODE);
			}
		});
		
		
		btnRadio_schoolin_person.setChecked(true);
		//单选按钮的响应事件
		btnRadio_schoolin_person.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				setSchoolinUserTypeState(isChecked);
				
			}
			private void setSchoolinUserTypeState(boolean state) {
				UserTypeFlag=0;
			}			
		});
		
		
		btnRadio_schoolout_person.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				setSchooloutUserTypeState(isChecked);
			}
			private void setSchooloutUserTypeState(boolean state) {
				//默认的是用户登录是校内人员0，如果点击的是校外人员，就将表示为赋值为1.
				UserTypeFlag=1;
			}
		});
	
	}
	
	void showTip(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
	
	private static class IHandler extends Handler{

		private final WeakReference<Activity> mActivity;
		public IHandler(LoginActivity activity)
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
				((LoginActivity)mActivity.get()).showTip(errorMsg);
				break;

			case FLAG_LOGIN_SUCCESS:
				((LoginActivity)mActivity.get()).showTip(MSG_LOGIN_SUCCESS);
				break;
				
			default:
				break;
			}
			
			
		}
		
	}
	
	private IHandler handler=new IHandler(this);
	

	

}
