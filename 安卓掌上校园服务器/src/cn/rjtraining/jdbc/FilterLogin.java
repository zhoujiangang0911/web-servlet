package cn.rjtraining.jdbc;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FilterLogin implements Filter {

	public void destroy() {
	}
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest req  = (HttpServletRequest) arg0;
			HttpServletResponse res = (HttpServletResponse) arg1;
			HttpSession session = req.getSession();
			try{
			long uid = (Long) session.getAttribute("uid");
			String uname=(String)session.getAttribute("uname");
			if(uid>0&&uname!=null){
			chain.doFilter(req, res);
			}
			}catch(Exception e){
				req.setAttribute("msg", "<font color='red'>请登陆以后在操作!</font>");
				req.getRequestDispatcher("/login.jsp").forward(req, res);
			}
	}
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
