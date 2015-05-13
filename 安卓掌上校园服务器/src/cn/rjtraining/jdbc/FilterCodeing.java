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

public class FilterCodeing implements Filter {
	public void destroy() {
	}
	public void doFilter(ServletRequest req1, ServletResponse res1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) req1;
		HttpServletResponse res = (HttpServletResponse) res1;
		
			req.setCharacterEncoding("utf-8");
			res.setContentType("text/html;charset=utf-8");
			chain.doFilter(req, res);
	}
	public void init(FilterConfig arg0) throws ServletException {
	}
}
