package com.exchange;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.map.R;
import com.exchange.impl.UserServiceImpl;
import com.exchange.service.ServiceRulesException;
import com.exchange.service.UserService;


public class RegisterActivity  extends  Activity{

	private EditText mid;
	private EditText mname;
	private EditText password;
//	private EditText mcollege;
	private EditText mage;
//	private EditText msex;
	private EditText maddress;
	private EditText mphone;
	private EditText mobile;
	private Button btnRegisterSubmit;
	private Button btnRegisterCancel;
	private static ProgressDialog dialog;
//	private RadioButton btnRadio_schoolin_person;
//	private RadioButton btnRadio_schoolout_person;
	public static int UserTypeState=0;
	private Spinner Spinner_sex;
	private Spinner Spinner_coll;
	private Spinner Spinner_state;
	private Spinner Spinner_districtid;
	private TextView temp_textview;
	private LinearLayout mobileLinear, userNumberLinear, nameLinear, pswLinear, phoneLinear, sexLinear, ageLinear, addressLinear, collLinear;
	
	private LinearLayout mLayout;
	String coll;
	String sex;
	String sheng;
	String usertype;
	
	
	private String[] msgID_sex={"男","女"};
	
	private String[] college={"能源学院","管理学院","机械工程学院","建筑与土木工程学院","通信与信息工程学院",
			"电气与控制工程学院","测绘科学与技术学院","计算机科学与技术","人文与外国语学院","地质与环境学院",
			"艺术学院","化学与化工学院","材料科学与工程学院","理学院","继续教育学院"};
	private String[] districts={"北京市","天津市","河北省","山西省","内蒙古自治区",
								"辽宁省","吉林省","黑龙江省","上海市","江苏省",
								"浙江省","安徽省","福建省","江西省","山东省",
								"河南省","湖北省","湖南省","广东省","广西壮族自治区",
								"海南省","重庆市","四川省","云南省","西藏自治区",
								"陕西省","甘肃省","青海省","宁夏回族自治区","新疆维吾尔自治区",
								"台湾省","香港特别行政区","澳门特别行政区"};
	
	
	private UserService userService=new UserServiceImpl();
	private static final int FLAG_LOGIN_SUCCESS=1;
	private static final String MSG_LOGIN_ERROE="注册出错，";
	private static final String MSG_LOGIN_SUCCESS="注册成功,请重新登陆";
	public static final String  MSG_LOGIN_FAILED="注册出错！";
	public static final String  MSG_SERVER_ERROR="服务器响应出错！";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		init();
		addListener();
		
	}
	
	public String getDistrict(String s){
		
		for(int i =1;i<=college.length;i++){
			if(s.equals(college[i-1])){
				return i+"";
			}
		}
		return 1+"";
	}
	
	public String getCollege(String s){
		
		for(int i =1;i<=districts.length;i++){
			if(s.equals(districts[i-1])){
				return i+"";
			}
		}
		return 1+"";
	}
	
	private void addListener() {
		// TODO Auto-generated method stub
		this.btnRegisterCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RegisterActivity.this.finish();
			}
		});
		
		this.btnRegisterSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final	String  registerMid=mid.getText().toString();
				final	String  registerMname=mname.getText().toString();
				final	String  registerPassword=password.getText().toString();
//				final	String  registerMcollege=mcollege.getText().toString();
				final	String  registerMcollege=coll;
				final	String  registerMage=mage.getText().toString();
				final	String  registerMsex=sex; // parent.getItemAtPosition(position).toString().trim();
				final	String  registerMaddress=maddress.getText().toString();
				final	String  registerMphone=mphone.getText().toString();
				final	String  registerMobile=mobile.getText().toString();
			    final   String  registerSheng = sheng;
			    final   String  registerUsertype = usertype;
				
				if(dialog==null)
				{
					dialog = new ProgressDialog(RegisterActivity.this);
				}
				dialog.setTitle("请等待");
				dialog.setMessage("注册中...");
				dialog.setCancelable(false);
				dialog.show();
				
                 Thread  thread=new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try{
							userService.userRegister(registerMid, registerMname, registerPassword, registerSheng,registerUsertype,registerMcollege ,registerMage, registerMsex, registerMphone,registerMaddress);
							//handler.sendEmptyMessage(FLAG_LOGIN_SUCCESS);
						     RegisterActivity.this.finish();
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
		
		
		
		
//		btnRadio_schoolin_person.setOnCheckedChangeListener(new OnCheckedChangeListener(){
//			@Override
//			public void onCheckedChanged(CompoundButton view, boolean ischecked) {
//				UserTypedState(ischecked);
//			}
//
//			private void UserTypedState(boolean ischecked) {
//				//隐藏掉校外人员的一些注册信息
//				showUserState(true);
//			}
//			
//		});
//		
//		
//		
//		btnRadio_schoolout_person.setOnCheckedChangeListener(new OnCheckedChangeListener(){
//			@Override
//			public void onCheckedChanged(CompoundButton view, boolean ischecked) {
//				UserTypedState(ischecked);
//			}
//
//			private void UserTypedState(boolean ischecked) {
//				UserTypeState=1;
//				showUserState(false);
//			}
//		});
		
		
		BaseAdapter ba=new BaseAdapter(){

			@Override
			public int getCount() {
				return 3;
			}

			@Override
			public Object getItem(int arg0) {
				return null;
			}

			@Override
			public long getItemId(int arg0) {
				return 0;
			}

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				return null;
			}
			
			//LinearLayout ii=new LinearLayout(RegisterActivity.this);
			//ii.setOrientation(LinearLayout.HORIZONTAL);
			TextView TV=new TextView(RegisterActivity.this);
			//TV.setText(""+getResourses().getText(msgID_SEX));
		};
		
		
	}
//	private void showUserState(boolean isSchoolIn){
//		if(isSchoolIn){
//			mobileLinear.setVisibility(View.GONE);
//			
//			temp_textview.setVisibility(View.VISIBLE);
//			userNumberLinear.setVisibility(View.VISIBLE);
//			nameLinear.setVisibility(View.VISIBLE);
//			pswLinear.setVisibility(View.VISIBLE);
//			phoneLinear.setVisibility(View.VISIBLE);
//			sexLinear.setVisibility(View.VISIBLE);
//			ageLinear.setVisibility(View.VISIBLE);
//			addressLinear.setVisibility(View.VISIBLE);
//			collLinear.setVisibility(View.VISIBLE);
//			
//			mid.requestFocus();
//			mid.setFocusable(true);
//		}else{
//			userNumberLinear.setVisibility(View.GONE);
//			nameLinear.setVisibility(View.GONE);
//			pswLinear.setVisibility(View.GONE);
//			phoneLinear.setVisibility(View.GONE);
//			sexLinear.setVisibility(View.GONE);
//			ageLinear.setVisibility(View.GONE);
//			addressLinear.setVisibility(View.GONE);
//			collLinear.setVisibility(View.GONE);
//			temp_textview.setVisibility(View.GONE);
//			
//			mobileLinear.setVisibility(View.VISIBLE);
//			nameLinear.setVisibility(View.VISIBLE);
//			pswLinear.setVisibility(View.VISIBLE);
//			
//			mobile.requestFocus();
//			mobile.setFocusable(true);
//		}
//	}
	private void init() {
		mobileLinear = (LinearLayout) findViewById(R.id.user_mobile_linear);
		userNumberLinear = (LinearLayout) findViewById(R.id.user_number_linear);
		nameLinear = (LinearLayout) findViewById(R.id.user_name_linear);
		pswLinear = (LinearLayout) findViewById(R.id.user_password_linear);
		phoneLinear = (LinearLayout) findViewById(R.id.user_phone_linear);
		sexLinear = (LinearLayout) findViewById(R.id.user_sex_linear);
		ageLinear = (LinearLayout) findViewById(R.id.user_age_linear);
		addressLinear = (LinearLayout) findViewById(R.id.user_address_linear);
		collLinear = (LinearLayout) findViewById(R.id.user_coll_linear);
		mLayout = (LinearLayout) findViewById(R.id.user_coll_linear22);
		
	//	temp_textview = (TextView) findViewById(R.id.temp_textview);
		
		this.mid=(EditText) findViewById(R.id.new_user_number);
		this.mname=(EditText) findViewById(R.id.new_user_name);
		this.password=(EditText) findViewById(R.id.new_user_password);
//		this.mcollege=(EditText) findViewById(R.id.new_user_college);
		this.mage=(EditText) findViewById(R.id.new_user_age);
		//this.msex=(EditText) findViewById(R.id.new_user_sex);
		this.maddress=(EditText) findViewById(R.id.new_user_address);
		this.mphone=(EditText) findViewById(R.id.new_user_phone);
		this.mobile=(EditText) findViewById(R.id.new_user_mobile);
		
		this.btnRegisterSubmit=(Button) findViewById(R.id.btn_register);
		this.btnRegisterCancel=(Button) findViewById(R.id.btn_quexiao);
//		this.btnRadio_schoolin_person=(RadioButton)findViewById(R.id.shcoolin_person);
//		this.btnRadio_schoolout_person=(RadioButton)findViewById(R.id.shcoolout_person);
		
		this.Spinner_sex=(Spinner)findViewById(R.id.Spinner_sex);
		this.Spinner_coll=(Spinner) findViewById(R.id.Spinner_coll);
		this.Spinner_state=(Spinner) findViewById(R.id.Spinner_state);
		Spinner_districtid = (Spinner) findViewById(R.id.sp_district);
		
		final ArrayAdapter<String> stateAdapters=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, districts);
		Spinner_districtid.setAdapter(stateAdapters);
		Spinner_districtid.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
					 sheng =	getDistrict(parent.getItemAtPosition(position).toString().trim());
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		
		final String[] states =  getResources().getStringArray(R.array.state_val);
		final int len = states.length - 1;
		// 建立Adapter并且绑定数据源
		final ArrayAdapter<String> stateAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, states);
		//绑定 Adapter到控件
		
		Spinner_state.setAdapter(stateAdapter);
		Spinner_state.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				//Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
				
				if(position == 0){
					mLayout.setVisibility(View.VISIBLE);
				}else if(position == 1){
					mLayout.setVisibility(View.GONE);
				}else if(position == 2){
					mLayout.setVisibility(View.GONE);
				}else if(position == 3){
					mLayout.setVisibility(View.GONE);
				}
				final int[] stateIndexes =  getResources().getIntArray(R.array.state_val);
				usertype=stateIndexes[position]+"";
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
//		Spinner_state.setOn
//		
//		Spinner_sex.setSelection(len);
		final String[] sexes =  getResources().getStringArray(R.array.sex_val);
		// 建立Adapter并且绑定数据源
		final ArrayAdapter<String> sexAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, sexes);
		//绑定 Adapter到控件
		Spinner_sex.setAdapter(sexAdapter);
		Spinner_sex.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				sex=parent.getItemAtPosition(position).toString().trim();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		Spinner_sex.setSelection(0);
		//final String[] colles =  getResources().getStringArray(R.array.coll_val);
		// 建立Adapter并且绑定数据源
		final ArrayAdapter<String> collAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, college);
		//绑定 Adapter到控件
		Spinner_coll.setAdapter(collAdapter);
		Spinner_coll.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				coll=getCollege(parent.getItemAtPosition(position).toString().trim());
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		Spinner_coll.setSelection(0);
		
	};

	//private IHandler handler=new IHandler(this);
	private void showTip(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
	
	private static class IHandler extends Handler{

		private final WeakReference<Activity> mActivity;
		public IHandler(RegisterActivity activity)
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
				((RegisterActivity)mActivity.get()).showTip(errorMsg);
				break;

			case FLAG_LOGIN_SUCCESS:
				((RegisterActivity)mActivity.get()).showTip(MSG_LOGIN_SUCCESS);
				break;
				
			default:
				break;
			}
			
			
		}
		
	}
	private IHandler handler=new IHandler(this);
	
}

