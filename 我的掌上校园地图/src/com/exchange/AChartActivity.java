package com.exchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.renderscript.Type;

public class AChartActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 1, ������ʾ����Ⱦͼ
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		// 2,������ʾ
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		// 2.1, ��������
		Random r = new Random();
		for (int i = 0; i < 2; i++) {
			XYSeries series = new XYSeries("test" + (i + 1));
			// �������
			for (int k = 0; k < 10; k++) {
				// ��x,yֵ
				series.add(k, 20 + r.nextInt() % 100);
			}
			// ��Ҫ���Ƶĵ�Ž�dataset��
			dataset.addSeries(series);
		}
		// 3, �Ե�Ļ��ƽ�������
		XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
		// 3.1������ɫ
		xyRenderer.setColor(Color.BLUE);
		// 3.2���õ����ʽ
		xyRenderer.setPointStyle(PointStyle.SQUARE);
		// 3.3, ��Ҫ���Ƶĵ���ӵ����������
		renderer.addSeriesRenderer(xyRenderer);
		// 3.4,�ظ� 1~3�Ĳ�����Ƶڶ���ϵ�е�
		xyRenderer = new XYSeriesRenderer();
		xyRenderer.setColor(Color.RED);
		xyRenderer.setPointStyle(PointStyle.CIRCLE);
		renderer.addSeriesRenderer(xyRenderer);

		// Intent intent = new LinChart().execute(this);
		Intent intent = ChartFactory
				.getLineChartIntent(this, dataset, renderer);
		startActivity(intent);

	}
	
//	public Intent execute(Context context) {  
//	    String[] titles = new String[] { "2008", "2007" };//ͼ��  
//	    List<double[]> values = new ArrayList<double[]>();  
//	    values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200, 22030, 21200, 19500, 15500,  
//	        12600, 14000 });//��һ�����ӵ���ֵ  
//	    values.add(new double[] { 5230, 7300, 9240, 10540, 7900, 9200, 12030, 11200, 9500, 10500,  
//	        11600, 13500 });//�ڶ������ӵ���ֵ  
//	    int[] colors = new int[] { Color.BLUE, Color.CYAN };//�������ӵ���ɫ  
//	    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();;//����AbstractDemoChart�еķ�������renderer.  
////	    setChartSettings(renderer, "Monthly sales in the last 2 years", "Month", "Units sold", 0.5,  
////	        12.5, 0, 24000, Color.GRAY, Color.LTGRAY);//����AbstractDemoChart�еķ�������renderer��һЩ����.  
//	    renderer.getSeriesRendererAt(0).setDisplayChartValues(true);//�����������Ƿ���ʾ����ֵ  
//	    renderer.getSeriesRendererAt(1).setDisplayChartValues(true);//�����������Ƿ���ʾ����ֵ  
//	    renderer.setXLabels(12);//X��Ľ���������  
//	    renderer.setYLabels(5);//Y��Ľ���������  
//	    renderer.setXLabelsAlign(Align.LEFT);//�̶�����X����������������  
//	    renderer.setYLabelsAlign(Align.LEFT);//Y����Y���������������  
//	    renderer.setPanEnabled(true, false);//���������϶�,�������������϶�.  
//	    // renderer.setZoomEnabled(false);  
//	    renderer.setZoomRate(1.1f);//�Ŵ�ı���  
//	    renderer.setBarSpacing(0.5f);//���Ӽ���  
//	    return ChartFactory.getBarChartIntent(context, buildBarDataset(titles, values), renderer,  
//	        Type.STACKED);//����Intent, buildBarDataset�ǵ���AbstractDemochart�еķ���.  
//	  }  
	
}
