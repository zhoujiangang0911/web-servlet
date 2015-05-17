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
		// 1, 构造显示用渲染图
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		// 2,进行显示
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		// 2.1, 构建数据
		Random r = new Random();
		for (int i = 0; i < 2; i++) {
			XYSeries series = new XYSeries("test" + (i + 1));
			// 填充数据
			for (int k = 0; k < 10; k++) {
				// 填x,y值
				series.add(k, 20 + r.nextInt() % 100);
			}
			// 需要绘制的点放进dataset中
			dataset.addSeries(series);
		}
		// 3, 对点的绘制进行设置
		XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
		// 3.1设置颜色
		xyRenderer.setColor(Color.BLUE);
		// 3.2设置点的样式
		xyRenderer.setPointStyle(PointStyle.SQUARE);
		// 3.3, 将要绘制的点添加到坐标绘制中
		renderer.addSeriesRenderer(xyRenderer);
		// 3.4,重复 1~3的步骤绘制第二个系列点
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
//	    String[] titles = new String[] { "2008", "2007" };//图例  
//	    List<double[]> values = new ArrayList<double[]>();  
//	    values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200, 22030, 21200, 19500, 15500,  
//	        12600, 14000 });//第一种柱子的数值  
//	    values.add(new double[] { 5230, 7300, 9240, 10540, 7900, 9200, 12030, 11200, 9500, 10500,  
//	        11600, 13500 });//第二中柱子的数值  
//	    int[] colors = new int[] { Color.BLUE, Color.CYAN };//两种柱子的颜色  
//	    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();;//调用AbstractDemoChart中的方法构建renderer.  
////	    setChartSettings(renderer, "Monthly sales in the last 2 years", "Month", "Units sold", 0.5,  
////	        12.5, 0, 24000, Color.GRAY, Color.LTGRAY);//调用AbstractDemoChart中的方法设置renderer的一些属性.  
//	    renderer.getSeriesRendererAt(0).setDisplayChartValues(true);//设置柱子上是否显示数量值  
//	    renderer.getSeriesRendererAt(1).setDisplayChartValues(true);//设置柱子上是否显示数量值  
//	    renderer.setXLabels(12);//X轴的近似坐标数  
//	    renderer.setYLabels(5);//Y轴的近似坐标数  
//	    renderer.setXLabelsAlign(Align.LEFT);//刻度线与X轴坐标文字左侧对齐  
//	    renderer.setYLabelsAlign(Align.LEFT);//Y轴与Y轴坐标文字左对齐  
//	    renderer.setPanEnabled(true, false);//允许左右拖动,但不允许上下拖动.  
//	    // renderer.setZoomEnabled(false);  
//	    renderer.setZoomRate(1.1f);//放大的倍率  
//	    renderer.setBarSpacing(0.5f);//柱子间宽度  
//	    return ChartFactory.getBarChartIntent(context, buildBarDataset(titles, values), renderer,  
//	        Type.STACKED);//构建Intent, buildBarDataset是调用AbstractDemochart中的方法.  
//	  }  
	
}
