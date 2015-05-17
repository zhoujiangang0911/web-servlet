package com.exchange;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.map.R;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.exchange.impl.JsonParse;
import com.exchange.impl.MyOrientationListener;
import com.exchange.impl.MyOrientationListener.OnOrientationListener;
import com.exchange.impl.UserServiceImpl;
import com.exchange.model.Place;

public class MapActivity extends Activity {

//	public static   ArrayList<Place>  places = new ArrayList<Place>();
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	
	//��λ���
	private LocationClient mLocationClient;
	public MyLocationListener mLocationListener;
	private boolean isFirst=true;
	private static Context context;
	private double mLatitude;
	private double mLongtitude;
	
	//ģʽ�л�
	private LocationMode mLocationMode;
	//�Զ��嶨λͼ��
	private BitmapDescriptor mIconLocation;
	private MyOrientationListener myOrientationListener;
	private float mCurrentX;
	
	//���������
	private BitmapDescriptor mMarker;
    private RelativeLayout mMarkerLy;
    
    private static List<Place> places;
	private static String str="�ص㣺";
	private static final int COMPLETED = 0; 
	
	public static double latitude;
	public static double longtitude;
	public static String current_position;
	//������Ϣ���
	public static boolean info_load=false;
	public static List<Place> PLACES=new ArrayList<Place>();
	
	
	private static Handler handler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            if (msg.what == COMPLETED) {  
                Log.i("main", str);  
            }  
        }  
    };  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.map);
		this.context=this;
		initiView();
		//��ʼ����λ
		initLocation();
		initMarker();
		
	
		
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				// TODO Auto-generated method stub
				Bundle extraInfo=marker.getExtraInfo();
				Place place=(Place) extraInfo.getSerializable("place");
				ImageView iv=(ImageView) mMarkerLy.findViewById(R.id.id_info_img);
				TextView infomation=(TextView) mMarkerLy.findViewById(R.id.id_info_distance);
				TextView name=(TextView) findViewById(R.id.id_info_name);
				//��ǩ�ı���ʾ���
				iv.setImageResource(R.drawable.a01);
           		infomation.setText(place.getPinfo());
				name.setText(place.getPname());

				
				mMarkerLy.setVisibility(View.VISIBLE);
				return true;
			}
		});
		mBaiduMap.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void onMapClick(LatLng arg0) {
				// TODO Auto-generated method stub
				mMarkerLy.setVisibility(View.GONE);
			}
		});
	}
	
	private void initMarker() {
		// TODO Auto-generated method stub
		mMarker=BitmapDescriptorFactory.fromResource(R.drawable.maker);
		mMarkerLy=(RelativeLayout) findViewById(R.id.id_marker_ly);
		
	}

	private void initLocation() {
		// TODO Auto-generated method stub
		mLocationMode=LocationMode.NORMAL;
		mLocationClient=new LocationClient(this);
		mLocationListener=new MyLocationListener();
		mLocationClient.registerLocationListener(mLocationListener);
		
		LocationClientOption option=new LocationClientOption();
		option.setCoorType("bd09ll");
		option.setIsNeedAddress(true);
		option.setOpenGps(true);
		option.setScanSpan(1000);
		
		mLocationClient.setLocOption(option);
		//��ʼ��ͼ��
		mIconLocation=BitmapDescriptorFactory.fromResource(R.drawable.navi_map_gps_locked);
		
		myOrientationListener=new MyOrientationListener(context);
		
		myOrientationListener.setOnOrientationListener(new OnOrientationListener() {
			
			@Override
			public void onOrientationChanged(float x) {
				// TODO Auto-generated method stub
				mCurrentX=x;
			}
		});
	}

	private void initiView() {
		// TODO Auto-generated method stub
		mMapView=(MapView) findViewById(R.id.id_bmapView);
		mBaiduMap=mMapView.getMap();
		//��ʼ�����ű���
		MapStatusUpdate msu=MapStatusUpdateFactory.zoomTo(17.0f);
		mBaiduMap.setMapStatus(msu);
	}
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mMapView.onResume();
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//��ʼ��λ
		mBaiduMap.setMyLocationEnabled(true);
		if(!mLocationClient.isStarted())
		   mLocationClient.start();
		//�������򴫸���
		myOrientationListener.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mMapView.onPause();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//ֹͣ��λ
		mBaiduMap.setMyLocationEnabled(false);
		mLocationClient.stop();
		//ֹͣ���򴫸���
		myOrientationListener.stop();
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mMapView.onDestroy();
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	
		switch (item.getItemId()) {
		case R.id.id_map_commom:
			mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
			break;
        case R.id.id_map_site:
			mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
			break;
        case R.id.id_map_traffic:
        	ifTraffic(item);
	        break;
        case R.id.id_map_location:
        	centerToMyLocation();
			break;
        case R.id.id_map_normal:
        	mLocationMode=LocationMode.NORMAL;
			break;
        case R.id.id_map_following:
        	mLocationMode=LocationMode.FOLLOWING;
			break;
        case R.id.id_map_compass:
        	mLocationMode=LocationMode.COMPASS;
			break;
        case R.id.id_map_exit:
        	MapActivity.this.finish();
			break;
		case R.id.id_map_overlay:
            dealWith();
			break;
		case R.id.id_map_navi:
            navi();
			break;
        default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void navi() {
		// TODO Auto-generated method stub
		 Intent intent = new Intent();
		 Bundle bundle=new Bundle();
		 bundle.putDouble("CURRENT_LATITUDE", MapActivity.latitude);
		 bundle.putDouble("CURRENT_LONGTITUDE", MapActivity.longtitude);
		 bundle.putString("CURRENT_POSITION", MapActivity.current_position);

		 intent.putExtras(bundle);
		 intent.setClass(MapActivity.this, DemoMainActivity.class);
		 startActivity(intent);
	}

	private void dealWith() {
		getInfos();
		
	}

	private void getInfos() 
	{

		try 
		{
			if(!MapActivity.info_load) //�ص���Ϣδ����ʱ������infoload()����������Ϣ
			{
		        infoLoad( );
			}
		addOverlays(MapActivity.PLACES);
		} catch (Exception e) {
			Log.i("main","overload  failed");
			e.printStackTrace();
		}

	}
	

	
	public static void infoLoad( ) throws Exception {
		
			 new Thread() {
        	    	@Override
        	    	public void run() 
        	    	{
         	   try {
                            // �õ�Json�����ɹ�֮������
                            places = JsonParse.getListPerson(UserServiceImpl.PATH);
                            List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
                            List<Place> allplaces=new ArrayList<Place>();
                            Place p;
                            for (int i = 0; i < places.size(); i++) 
                            {
                                    HashMap<String, Object> map = new HashMap<String, Object>();
                                    map.put("pid", places.get(i).getPid());
                                    map.put("pname", places.get(i).getPname());
                                    map.put("plongtitude", places.get(i).getPlongtitude());
                                    map.put("platitude", places.get(i).getPlatitude());
                                    map.put("pinfo", places.get(i).getPinfo());
                                    data.add(map);
                                    p=places.get(i);
                                    allplaces.add(new Place(p.getPid(),p.getPname(),p.getPlongtitude(),p.getPlatitude(),p.getPinfo()));
                                    str+=places.get(i).getPname()+";";
                                    
                            }
                           
                            Message msg = new Message();  
                            msg.what = COMPLETED;  
                            handler.sendMessage(msg); 
                            
                            MapActivity.PLACES=allplaces;//����ȡ���ĵص㱣��
                            MapActivity.info_load=true;
                            
                    } catch (Exception e) {
                            Log.i("main", e.toString());
                    }
            };
        }.start();			
	}
	
	
	//��Ӹ�����
	private void addOverlays(List<Place> places) {
		
		mBaiduMap.clear();
		LatLng latLng=null;
		Marker marker=null;
		OverlayOptions options;
		Place place;
		for(int i = 0; i < places.size(); i++)
		{
			place=places.get(i);
			//��γ��
			latLng=new LatLng(place.getPlatitude(), place.getPlongtitude());
		    //ͼ��
			options=new MarkerOptions().position(latLng).icon(mMarker).zIndex(5);
		    marker=(Marker) mBaiduMap.addOverlay(options);
		    Bundle arg0=new Bundle();
		    //ʵ����Info
		    arg0.putSerializable("place", place);
		    marker.setExtraInfo(arg0);
		}
		MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latLng);
		mBaiduMap.setMapStatus(msu);
	}
	
	private void ifTraffic(MenuItem item) {
		if(mBaiduMap.isTrafficEnabled())
		{
			mBaiduMap.setTrafficEnabled(false);
		    item.setTitle("ʵʱ��ͨ(off)");
		}
		else
		{
			mBaiduMap.setTrafficEnabled(true);
			item.setTitle("ʵʱ��ͨ(on)");
		}
	}

	//��λ���ҵ�λ��
	private void centerToMyLocation() {
		LatLng latLng=new LatLng(mLatitude,mLongtitude);
		MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latLng);
		mBaiduMap.animateMapStatus(msu);
	}

	private class MyLocationListener implements BDLocationListener
	{

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			
			MyLocationData data=new MyLocationData.Builder()//
			.direction(mCurrentX)//
			.accuracy(location.getRadius())//
			.latitude(location.getLatitude())//
			.longitude(location.getLongitude())//
			.build();
			
			mBaiduMap.setMyLocationData(data);
			//�����Զ���ͼ��
			MyLocationConfiguration config=new MyLocationConfiguration(mLocationMode, true,mIconLocation);
			mBaiduMap.setMyLocationConfigeration(config);
			//���¾�γ��
			mLatitude=location.getLatitude();
			mLongtitude=location.getLongitude();
			
			//��¼��ǰλ�õľ�γ�ȡ��ص���
			MapActivity.latitude=mLatitude;
			MapActivity.longtitude=mLongtitude;
			MapActivity.current_position=location.getAddrStr();
			
			if(isFirst)
			{
				LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
				MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latLng);
				mBaiduMap.animateMapStatus(msu);
				isFirst=false;
				
				Toast.makeText(context, "����λ�ã�"+location.getAddrStr(), Toast.LENGTH_LONG).show();
			}
		}
		
	}
	
	/**
	 * ������ת��Ϊ�ַ�����
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inputStream) throws Exception
	{
		ByteArrayOutputStream bOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		while((inputStream.read(buffer)) != -1)
		{
			bOutputStream.write(buffer);
		}
		bOutputStream.close();
		inputStream.close();
		return bOutputStream.toByteArray();
	}
	
}
