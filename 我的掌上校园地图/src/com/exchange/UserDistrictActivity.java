package com.exchange;

import com.baidu.map.R;
import com.exchange.utils.Configuration;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class UserDistrictActivity extends Activity{
	private WebView web;
	private String url = Configuration.PATH;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userdistrict);
		
		web = (WebView) findViewById(R.id.web_userdistrict);
		web.loadUrl(url);
        //����WebViewĬ��ʹ�õ�������ϵͳĬ�����������ҳ����Ϊ��ʹ��ҳ��WebView��
		web.setWebViewClient(new WebViewClient(){
           @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
             view.loadUrl(url);
            return true;
        }
       });
		
		web.setVerticalScrollbarOverlay(true); //ָ���Ĵ�ֱ�������е�����ʽ

		WebSettings settings = web.getSettings();

		settings.setUseWideViewPort(true);//�趨֧��viewport

		settings.setLoadWithOverviewMode(true);

		settings.setBuiltInZoomControls(true);

		settings.setSupportZoom(true);//�趨֧������   
		
	}
	

}
