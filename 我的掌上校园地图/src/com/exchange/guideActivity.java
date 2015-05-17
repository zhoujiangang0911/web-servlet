package com.exchange;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.map.R;
import com.baidu.navisdk.BNaviPoint;
import com.baidu.navisdk.BaiduNaviManager;
import com.baidu.navisdk.BaiduNaviManager.OnStartNavigationListener;
import com.baidu.navisdk.comapi.routeplan.RoutePlanParams.NE_RoutePlan_Mode;
import com.exchange.model.Place;

/**
 * GPS����Demo
 */
public class guideActivity extends Activity{
	
	
    public static Bundle bundle;
    private TextView start_position;
    private Button btn_choose_destination;
    private TextView destination_position;
    public static List<Place> places;
    int selected_position;
    public static boolean selected=false;
    public static double current_longtitude;
    public static double current_latitude;
    public static String current_position;
    
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_routeguide);
		guideActivity.bundle=this.getIntent().getExtras();
		init();

	}




	private void init() {
		if(!MapActivity.info_load)  //�ص���Ϣδ����ʱ�����ú���������Ϣ
		{
			try {
				MapActivity.infoLoad();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.i("main","overload  failed");
			}
		}
		places=MapActivity.PLACES;
		MapActivity.info_load=true;
		
		current_position=bundle.getString("CURRENT_POSITION");
		current_latitude=bundle.getDouble("CURRENT_LATITUDE");
		current_longtitude=bundle.getDouble("CURRENT_LONGTITUDE");
		 
		
		start_position=(TextView) findViewById(R.id.start_node);
	    start_position.setText("��㣺"+bundle.getString("CURRENT_POSITION"));
		
		destination_position=(TextView) findViewById(R.id.end_node);
		if(guideActivity.selected)
		{
			selected_position=bundle.getInt("SELECTED_PLACE");
			destination_position.setText("�յ㣺"+places.get(selected_position).getPname());
		}
		else
		{
			destination_position.setText("�յ㣺"+"��δѡ��");
		}
		
	    btn_choose_destination=(Button) findViewById(R.id.choose_destination);
		btn_choose_destination.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(guideActivity.this, DestinationActiviyty.class);
                startActivity(intent);
                guideActivity.this.finish();
			}
		});
		
		
		findViewById(R.id.start_nav2_btn).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
            	                
            	if(guideActivity.selected)    //�յ���ѡ�����Ե���
            	{
                double end_longtitude;
                double end_latitude;
                String end_position;
                
                Place place;
        		place=MapActivity.PLACES.get(bundle.getInt("SELECTED_PLACE"));
        		
        		 end_longtitude=place.getPlongtitude();
                 end_latitude=place.getPlatitude();
                 end_position=place.getPname();
                 
                launchNavigator2(guideActivity.current_position,guideActivity.current_longtitude,guideActivity.current_latitude,end_position,end_longtitude,end_latitude);
            	}
            	else
            	{
            	Toast.makeText(guideActivity.this, "δѡ�񵼺�Ŀ�ĵأ���ѡ�񣡣�", 2000).show();	
               }	
            }
        });
	}
	

  

    /**
     * ָ���������յ�����GPS����.���յ��Ϊ������������ϵ�ĵ������ꡣ
     * ǰ�����������������ʼ���ɹ�
     */
    private void launchNavigator2(String current_position,double current_longtitude,double current_latitude,String end_position,double end_longtitude,double end_latitude){
        //�������һ�����յ�ʾ����ʵ��Ӧ���п���ͨ��POI�������ⲿPOI��Դ�ȷ�ʽ��ȡ���յ�����
        BNaviPoint startPoint = new BNaviPoint(current_longtitude,current_latitude,
        		current_position, BNaviPoint.CoordinateType.BD09_MC);
        
        BNaviPoint endPoint = new BNaviPoint(end_longtitude,end_latitude,
        		end_position, BNaviPoint.CoordinateType.BD09_MC);
        BaiduNaviManager.getInstance().launchNavigator(this,
                startPoint,                                      //��㣨��ָ������ϵ��
                endPoint,                                        //�յ㣨��ָ������ϵ��
                NE_RoutePlan_Mode.ROUTE_PLAN_MOD_MIN_TIME,       //��·��ʽ
                true,                                            //��ʵ����
                BaiduNaviManager.STRATEGY_FORCE_ONLINE_PRIORITY, //�����߲���
                new OnStartNavigationListener() {                //��ת����
                    
                    @Override
                    public void onJumpToNavigator(Bundle configParams) {
                        Intent intent = new Intent(guideActivity.this, BNavigatorActivity.class);
                        intent.putExtras(configParams);
                        startActivity(intent);
                    }
                    
                    @Override
                    public void onJumpToDownloader() {
                    }
                });
    }
}


    /**
     * ����һ������;���㣬����GPS����. 
     * ǰ�����������������ʼ���ɹ�
     */

