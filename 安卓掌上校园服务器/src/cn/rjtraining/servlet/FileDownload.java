package cn.rjtraining.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.rjtraining.test.WebRootPath;

import com.sun.xml.internal.fastinfoset.stax.events.Util;

public class FileDownload extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8799754655999187755L;
	private int bufferSize = 2048;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String bsize = config.getInitParameter("bufferSize");
		if (Util.isEmptyString(bsize)) {
			bufferSize = Integer.parseInt(bsize);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = "";
		try {
			
			String path = request.getServletPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
		//	String s = WebRootPath.getWebRootPath();
//			System.out.println(path);
//			System.out.println("文件的绝对路径"+request.getSession().getServletContext().getRealPath(request.getRequestURI()));
//			System.out.println("内容所在路径:"+request.getContextPath()); 
//			System.out.println("绝对路径:"+request.getRequestURL());
//			System.out.println(	this.getClass().getClassLoader().getResource("/").getPath());
//			File   files=new   File(".");   
//			System.out.println(WebRootPath.getWebRootPath());
			String pathsss=WebRootPath.getWebRootPath();
		//	System.out.println(pathsss+"\nE:\tomcat\apache-tomcat-7.0.55-windows-x64\apache-tomcat-7.0.55\webapps\xscj\WEB-INF");
			File file = new File(pathsss,"我的掌上校园地图.apk");
			//File file = new File(request.getRequestURL()+"//我的掌上校园地图.apk");
			
			//File file = new File("test.txt");
			final String filename = file.getName();
			
			if (!file.exists()) {
				request
						.setAttribute("msg",
								"<script type=\"text/javascript\">alert(\"指定的文件不存在！\");</script>");
				request.getRequestDispatcher(action).forward(request, response);
			} else {
				response.reset();
				// 将 文件流写入到前端浏览器
				response.setHeader("Content-disposition",
						"attachment;filename=" + convertFileName(filename));
				ServletOutputStream sops = response.getOutputStream();
				FileInputStream fis = new FileInputStream(file);
				copyStream(fis, sops, true);
				fis.close();
				sops.close();
				fis = null;
				sops = null;
				file = null;
			}

			// }
			// else{
			// //response.sendRedirect("index.jsp");（如果是重定向：index.jsp
			// 中需要通过js控制跳到top.location.href="login.jsp"）
			// //下面实现了 top.location.href="login.jsp" 的效果
			// response.getWriter().print("<script type=\"text/javascript\">alert(\"请先登录后再下载！\");top.location.href='/bjproduct/';</script>");
			// }
		} catch (Exception e) {
			request
					.setAttribute("msg",
							"<script type=\"text/javascript\">alert(\"文件下载失败,请稍后再试...\");</script>");
			request.getRequestDispatcher(action).forward(request, response);
			e.printStackTrace();
		}
	}

	/**
	 * 复制流 到 前端浏览器
	 * 
	 * @param source
	 *            源文件输入流
	 * @param dest
	 *            输出流
	 * @param flush
	 * @return
	 */
	private final long copyStream(InputStream source, OutputStream dest,
			boolean flush) {
		int bytes;
		long total = 0l;
		byte[] buffer = new byte[bufferSize];
		try {
			while ((bytes = source.read(buffer)) != -1) {
				if (bytes == 0) {
					bytes = source.read();
					if (bytes < 0)
						break;
					dest.write(bytes);
					if (flush)
						dest.flush();
					total += bytes;
				}
				dest.write(buffer, 0, bytes);
				if (flush)
					dest.flush();
				total += bytes;
			}

		} catch (IOException e) {
			throw new RuntimeException("IOException caught while copying.", e);
		}
		return total;
	}

	// 转换文件名的方法 在strust.xml中会用到
	public String convertFileName(String cfilename) {
		try {
			cfilename = new String(cfilename.getBytes(), "ISO-8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cfilename;
	}
	

	
	
}
