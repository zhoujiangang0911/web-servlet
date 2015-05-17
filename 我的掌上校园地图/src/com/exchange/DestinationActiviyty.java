package com.exchange;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.map.R;
import com.exchange.model.GeneralBean;
import com.exchange.model.Place;

public class DestinationActiviyty extends Activity {

	ListView mlvGeneral;
	List <GeneralBean> mGenerals;
	GeneralAdapter mAdapter;
	public static List<Place> places;
	
	int resid=R.drawable.aa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_destination);
		initDate();
		initView();
		setListener();
	}
	

	
	private void setListener() {
		// TODO Auto-generated method stub
		setOnItemClickListener();
		
	}



	



	private void setOnItemClickListener() {
		mlvGeneral.setOnItemClickListener(new OnItemClickListener() {
        
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(DestinationActiviyty.this, mGenerals.get(position).getName()+"±»Ñ¡ÖÐ", 2000).show();
				Intent intent = new Intent();
				 Bundle bundle=guideActivity.bundle;
				 bundle.putInt("SELECTED_PLACE", position);
				 guideActivity.selected=true;
				 intent.putExtras(bundle);	
				 
				 intent.setClass(DestinationActiviyty.this, guideActivity.class);
				 startActivity(intent);
				 DestinationActiviyty.this.finish();
				
			}
		});
	}



	private void initView() {
		// TODO Auto-generated method stub
		mlvGeneral=(ListView) findViewById(R.id.lvGeneral);
		mAdapter=new GeneralAdapter();
		mlvGeneral.setAdapter(mAdapter);
	}



	private void initDate() {
		
	 places=MapActivity.PLACES;
		mGenerals=new ArrayList<GeneralBean>();

		for(int i=0;i<places.size();i++)
		{
			GeneralBean bean=new GeneralBean(resid,places.get(i).getPname());
			mGenerals.add(bean);
		}
	}

	class GeneralAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mGenerals.size();
		}

		@Override
		public GeneralBean getItem(int position) {
			// TODO Auto-generated method stub
			return mGenerals.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View layout=View.inflate(DestinationActiviyty.this, R.layout.item_gen,null);
			ImageView ivThumb=(ImageView) layout.findViewById(R.id.tp);
			TextView tvName=(TextView) layout.findViewById(R.id.tn);
			
			GeneralBean bean=mGenerals.get(position);
			ivThumb.setImageResource(bean.getResid());
			
			tvName.setText(bean.getName());
			return layout;
		}
		
	}

}

