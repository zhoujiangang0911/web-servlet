package com.exchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import com.baidu.map.R;
import com.baidu.navisdk.BNaviEngineManager.NaviEngineInitListener;
import com.baidu.navisdk.BaiduNaviManager;
import com.baidu.navisdk.util.verify.BNKeyVerifyListener;

public class DemoMainActivity extends ListActivity {
	/** SDK��֤��Կ */
    private final static String ACCESS_KEY = "IE5uTd7x9yYQtkYlmIvzole7";//"LVUOeu2yWl5uHwG6zewGwN0m";
	private static final String CATEGORY_SDK_DEMO = "android.intent.category.BAIDUNAVISDK_DEMO";
    private Bundle bundle;
	@SuppressWarnings("unused")
	private boolean mIsEngineInitSuccess = false;
	
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle saveInstance) {
		super.onCreate(saveInstance);
	
		setContentView(R.layout.activity_main);
		
		init();
		
		//��ʼ����������
		
		
	          BaiduNaviManager.getInstance().initEngine(this, getSdcardDir(),
		        mNaviEngineInitListener, ACCESS_KEY, mKeyVerifyListener);
        BaiduNaviManager.getInstance().initEngine(this, getSdcardDir(),
                mNaviEngineInitListener, new LBSAuthManagerListener() {
                    @Override
                    public void onAuthResult(int status, String msg) {
                        String str = null;
                        if (0 == status) {
                            str = "keyУ��ɹ�!";
                        } else {
                            str = "keyУ��ʧ��, " + msg;
                        }
                        Toast.makeText(DemoMainActivity.this, str,
                                Toast.LENGTH_LONG).show();
                    }
                });
		//����Demo��ͼ
		initViews();
	}

	private void init() {
		this.bundle=this.getIntent().getExtras();	
	}

	private String getSdcardDir() {
		if (Environment.getExternalStorageState().equalsIgnoreCase(
				Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().toString();
		}
		return null;
	}

	private NaviEngineInitListener mNaviEngineInitListener = new NaviEngineInitListener() {
		public void engineInitSuccess() {
			mIsEngineInitSuccess = true;
		}

		public void engineInitStart() {
		}

		public void engineInitFail() {
		}
	};
	
    private BNKeyVerifyListener mKeyVerifyListener = new BNKeyVerifyListener() {
		
		@Override
		public void onVerifySucc() {
			// TODO Auto-generated method stub
			Toast.makeText(DemoMainActivity.this, "keyУ��ɹ�", Toast.LENGTH_LONG).show();
		}
		
		@Override
		public void onVerifyFailed(int arg0, String arg1) {
			// TODO Auto-generated method stub
			Toast.makeText(DemoMainActivity.this, "keyУ��ʧ��", Toast.LENGTH_LONG).show();
		}
	};

	private void initViews() {
		setListAdapter(new SimpleAdapter(this, getListData(),
				android.R.layout.simple_list_item_1, new String[] { "title" },
				new int[] { android.R.id.text1 }));
		getListView().setTextFilterEnabled(true);
	}

	private ArrayList<Map<String, Object>> getListData() {
		ArrayList<Map<String, Object>> maps = new ArrayList<Map<String, Object>>(
				0);
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(CATEGORY_SDK_DEMO);
		PackageManager pm = getPackageManager();
		List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
		int size = resolveInfos.size();
		for (int i = 0; i < size; i++) {
			ResolveInfo info = resolveInfos.get(i);
			Map<String, Object> item = new HashMap<String, Object>(1);
			CharSequence labelSeq = info.loadLabel(pm);
			item.put("title", labelSeq != null ? labelSeq.toString()
					: info.activityInfo.name);
			item.put(
					"intent",
					activityIntent(
							info.activityInfo.applicationInfo.packageName,
							info.activityInfo.name));
			maps.add(item);
		}
		return maps;
	}

	private Intent activityIntent(String pkg, String componentName) {
		Intent result = new Intent();
		result.setClassName(pkg, componentName);
		return result;
	}

	@SuppressLint("ShowToast")
	@SuppressWarnings("unchecked")
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		boolean isInitSuccess = BaiduNaviManager.getInstance().checkEngineStatus(getApplicationContext());
		if(!isInitSuccess){
			return ;
		}
		Map<String, Object> map = (Map<String, Object>) l
				.getItemAtPosition(position);
		Intent intent = (Intent) map.get("intent");
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		System.exit(0);
		android.os.Process.killProcess(android.os.Process.myUid());
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	
	

}
