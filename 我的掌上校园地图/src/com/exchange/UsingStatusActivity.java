package com.exchange;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.map.R;

public class UsingStatusActivity extends Activity{

	private TextView status_show;
	private Button btn_return;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.using_status);
		init();	
	}
	
	private void init() {
		Bundle bundle=this.getIntent().getExtras();
		String  status=bundle.getString("STATUS");
		this.status_show=(TextView) findViewById(R.id.status_text);
		this.btn_return=(Button) findViewById(R.id.return_to_selectActivity);
		this.btn_return.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UsingStatusActivity.this.finish();
			}
		});
		
		this.status_show.setText(status);
	}

}
