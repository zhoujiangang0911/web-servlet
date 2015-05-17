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
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		web.setWebViewClient(new WebViewClient(){
           @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
             view.loadUrl(url);
            return true;
        }
       });
		
		web.setVerticalScrollbarOverlay(true); //指定的垂直滚动条有叠加样式

		WebSettings settings = web.getSettings();

		settings.setUseWideViewPort(true);//设定支持viewport

		settings.setLoadWithOverviewMode(true);

		settings.setBuiltInZoomControls(true);

		settings.setSupportZoom(true);//设定支持缩放   
		
	}
	

}
