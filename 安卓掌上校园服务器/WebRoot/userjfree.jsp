<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page contentType="text/html;charset=GBK"%>
<%@ page 
	
			import="java.awt.Font,
				org.jfree.chart.ChartFactory,
               org.jfree.chart.JFreeChart,  
               org.jfree.chart.StandardChartTheme,         
               org.jfree.chart.plot.PlotOrientation,          
               org.jfree.chart.servlet.ServletUtilities,
               org.jfree.data.category.DefaultCategoryDataset,
               org.jfree.chart.labels.StandardCategoryItemLabelGenerator,
               org.jfree.chart.renderer.category.BarRenderer,
               cn.rjtraining.model.User,
               cn.rjtraining.model.District,
               java.awt.Color,
               cn.rjtraining.model.College,
               org.jfree.chart.plot.CategoryPlot,
               java.util.*
               "
               
              %>
 <%
StandardChartTheme standardChartTheme = new StandardChartTheme("asdf");//这里的"name"参数；是什么意思我也不知道，反正这样可以用
standardChartTheme.setLargeFont(new Font("楷体",Font.BOLD, 36));//可以改变轴向的字体
standardChartTheme.setRegularFont(new Font("宋体",Font.BOLD, 8));//可以改变图例的字体
standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD, 32));//可以改变图标的标题字体
ChartFactory.setChartTheme(standardChartTheme);//设置主题


DefaultCategoryDataset dataset = new DefaultCategoryDataset();
List<District> ls = new ArrayList<District>();
 ls = (List<District>)request.getAttribute("districtlist");
 List<User> userls = new ArrayList<User>();
 userls = (List<User>)request.getAttribute("userlist");
 int district[] = (int [])request.getAttribute("districteuser");
String xueyuan = "学院";
for (int i = 0;i<ls.size();i++){
	int a = 0;
	dataset.addValue(district[i], xueyuan, ls.get(i).getName());	
} 

JFreeChart chart = ChartFactory.createBarChart3D("老乡分布图", 
                  "省市",
                  "人数",
                  dataset,
                  PlotOrientation.VERTICAL,
                  false,
                  false,
                  false);
  
  
  CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();                
       	 BarRenderer barrenderer = new BarRenderer();
	barrenderer.setMaximumBarWidth(0.5);
	barrenderer.setMinimumBarLength(0.5);
	categoryPlot.setRenderer(barrenderer);         
                  
                  
String filename = ServletUtilities.saveChartAsPNG(chart, 900, 500, null, session);
String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;
%> 

<HTML> 
<HEAD> 
<META http-equiv=Content-Type content="text/html; charset=GBK"> 
<META NAME="Author" CONTENT="Alpha">
<TITLE>程序员学历情况调查表-By Alpha</TITLE> 
</HEAD> 
<BODY> 
	
 <P ALIGN="CENTER"> 
<img src="<%= graphURL %>" width=%100 height=%100 border=0 usemap="#map0"> 
</P>  
</BODY> 
</HTML>