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
 * GPS导航Demo
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
		if(!MapActivity.info_load)  //地点信息未载入时，调用函数载入信息
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
	    start_position.setText("起点："+bundle.getString("CURRENT_POSITION"));
		
		destination_position=(TextView) findViewById(R.id.end_node);
		if(guideActivity.selected)
		{
			selected_position=bundle.getInt("SELECTED_PLACE");
			destination_position.setText("终点："+places.get(selected_position).getPname());
		}
		else
		{
			destination_position.setText("终点："+"（未选择）");
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
            	                
            	if(guideActivity.selected)    //终点已选，可以导航
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
            	Toast.makeText(guideActivity.this, "未选择导航目的地，请选择！！", 2000).show();	
               }	
            }
        });
	}
	

  

    /**
     * 指定导航起终点启动GPS导航.起终点可为多种类型坐标系的地理坐标。
     * 前置条件：导航引擎初始化成功
     */
    private void launchNavigator2(String current_position,double current_longtitude,double current_latitude,String end_position,double end_longtitude,double end_latitude){
        //这里给出一个起终点示例，实际应用中可以通过POI检索、外部POI来源等方式获取起终点坐标
        BNaviPoint startPoint = new BNaviPoint(current_longtitude,current_latitude,
        		current_position, BNaviPoint.CoordinateType.BD09_MC);
        
        BNaviPoint endPoint = new BNaviPoint(end_longtitude,end_latitude,
        		end_position, BNaviPoint.CoordinateType.BD09_MC);
        BaiduNaviManager.getInstance().launchNavigator(this,
                startPoint,                                      //起点（可指定坐标系）
                endPoint,                                        //终点（可指定坐标系）
                NE_RoutePlan_Mode.ROUTE_PLAN_MOD_MIN_TIME,       //算路方式
                true,                                            //真实导航
                BaiduNaviManager.STRATEGY_FORCE_ONLINE_PRIORITY, //在离线策略
                new OnStartNavigationListener() {                //跳转监听
                    
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
     * 增加一个或多个途经点，启动GPS导航. 
     * 前置条件：导航引擎初始化成功
     */

