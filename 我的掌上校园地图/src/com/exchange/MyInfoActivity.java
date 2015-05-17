package com.exchange;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.map.R;
import com.exchange.impl.UserServiceImpl;
import com.exchange.service.UserService;

public class MyInfoActivity extends Activity{
	private EditText edit_mid;
	private EditText edit_mname;
	private EditText edit_password;
	private EditText edit_mcollege;
	private EditText edit_mage;
	private EditText edit_msex;
	private EditText edit_maddress;
	private EditText edit_mphone;
	private Button btn_submit;
	private Button btn_cancel;
	private UserService userService=new UserServiceImpl();
	
	String[]  College={"��ԴѧԺ","����ѧԺ","��е����ѧԺ","��������ľ����ѧԺ","ͨ������Ϣ����ѧԺ",
			"��������ƹ���ѧԺ","����ѧ�뼼��ѧԺ","�������ѧ�뼼��","�����������ѧԺ","�����뻷��ѧԺ",
			"����ѧԺ","��ѧ�뻯��ѧԺ","���Ͽ�ѧ�빤��ѧԺ","��ѧԺ","��������ѧԺ"};
	
	
	
	private static final int FLAG_LOGIN_SUCCESS=1;
	private static final String MSG_LOGIN_ERROE="��Ϣ���³���";
	private static final String MSG_LOGIN_SUCCESS="��Ϣ���³ɹ�";
	public static final String  MSG_LOGIN_FAILED="��Ϣ���³���";
	public static final String  MSG_SERVER_ERROR="��������Ӧ����";
	private static ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_info);
		init();
	}

	private void init() {
		
		
		Bundle bundle=this.getIntent().getExtras();
		long  mid=bundle.getLong("MY_ID");
		String mname=bundle.getString("MY_NAME");
		String password=bundle.getString("MY_PASSWORD");
		//�����Ӧ��ѧԺ���
				int mcollege=bundle.getInt("MY_COLLEGE");
				//���ݱ�ŵõ��ַ�����������Ӧ��ѧԺ����
				String mColl=College[mcollege];
		int mage=bundle.getInt("MY_AGE");
		String msex=bundle.getString("MY_SEX");
		String maddress=bundle.getString("MY_ADDRESS");
		String mphone=bundle.getString("MY_PHONE");
		
		this.edit_mid=(EditText) findViewById(R.id.my_number);
		this.edit_mname=(EditText) findViewById(R.id.my_name);
		this.edit_password=(EditText) findViewById(R.id.my_password);
		this.edit_mcollege=(EditText) findViewById(R.id.my_college);
		this.edit_mage=(EditText) findViewById(R.id.my_age);
		this.edit_msex=(EditText) findViewById(R.id.my_sex);
		this.edit_maddress=(EditText) findViewById(R.id.my_address);
		this.edit_mphone=(EditText) findViewById(R.id.my_phone);
		
		this.btn_submit=(Button) findViewById(R.id.btn_change_submit);
		this.btn_cancel=(Button) findViewById(R.id.btn_change_cancel);
		
		
		this.edit_mid.setText(String.valueOf(mid));
		this.edit_mname.setText(mname);
		this.edit_password.setText(password);
		this.edit_mcollege.setText(String.valueOf(mColl));
		this.edit_mage.setText(String.valueOf(mage));
		this.edit_msex.setText(msex);
		this.edit_maddress.setText(maddress);
		this.edit_mphone.setText(mphone);
		
		this.btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyInfoActivity.this.finish();
			}
		});
		
		this.btn_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			final	String updateMid = edit_mid.getText().toString(); 
			final	String updateMname = edit_mname.getText().toString();
			final	String updatePassword = edit_password.getText().toString();
			final	String updateMcollege = edit_mcollege.getText().toString();
			final	String updateMage = edit_mage.getText().toString();
			final	String updateMsex = edit_msex.getText().toString();
			final	String updateMaddress = edit_maddress.getText().toString(); 
			final	String updateMphone =edit_mphone.getText().toString();
				 
			Thread  thread=new Thread(new Runnable() {
						
						@Override
						public void run() {
				try {
					userService.userUpdate(updateMid, updateMname, updatePassword, updateMcollege, updateMage, updateMsex, updateMaddress, updateMphone);
					handler.sendEmptyMessage(FLAG_LOGIN_SUCCESS);
                    MyInfoActivity.this.finish();
	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						}
				  });
				  thread.start();
				
			}
		});
		
		
		
	}
	
	
	void showTip(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
	
	private static class IHandler extends Handler{

		private final WeakReference<Activity> mActivity;
		public IHandler(MyInfoActivity myInfoActivity)
		{
			mActivity=new WeakReference<Activity>(myInfoActivity);
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
				((MyInfoActivity)mActivity.get()).showTip(errorMsg);
				break;

			case FLAG_LOGIN_SUCCESS:
				((MyInfoActivity)mActivity.get()).showTip(MSG_LOGIN_SUCCESS);
				break;
				
			default:
				break;
			}
			
			
		}
		
	}
	
	private IHandler handler=new IHandler(this);
	

	

	
	
	
	
	
}
