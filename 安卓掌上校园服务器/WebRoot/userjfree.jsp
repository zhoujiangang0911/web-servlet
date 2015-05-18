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
StandardChartTheme standardChartTheme = new StandardChartTheme("asdf");//�����"name"��������ʲô��˼��Ҳ��֪������������������
standardChartTheme.setLargeFont(new Font("����",Font.BOLD, 36));//���Ըı����������
standardChartTheme.setRegularFont(new Font("����",Font.BOLD, 8));//���Ըı�ͼ��������
standardChartTheme.setExtraLargeFont(new Font("����",Font.BOLD, 32));//���Ըı�ͼ��ı�������
ChartFactory.setChartTheme(standardChartTheme);//��������


DefaultCategoryDataset dataset = new DefaultCategoryDataset();
List<District> ls = new ArrayList<District>();
 ls = (List<District>)request.getAttribute("districtlist");
 List<User> userls = new ArrayList<User>();
 userls = (List<User>)request.getAttribute("userlist");
 int district[] = (int [])request.getAttribute("districteuser");
String xueyuan = "ѧԺ";
for (int i = 0;i<ls.size();i++){
	int a = 0;
	dataset.addValue(district[i], xueyuan, ls.get(i).getName());	
} 

JFreeChart chart = ChartFactory.createBarChart3D("����ֲ�ͼ", 
                  "ʡ��",
                  "����",
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
<TITLE>����Աѧ����������-By Alpha</TITLE> 
</HEAD> 
<BODY> 
	
 <P ALIGN="CENTER"> 
<img src="<%= graphURL %>" width=%100 height=%100 border=0 usemap="#map0"> 
</P>  
</BODY> 
</HTML>