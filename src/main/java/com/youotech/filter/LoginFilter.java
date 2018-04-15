package com.youotech.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{
	
    private static List<String> pattenURL = new ArrayList<String>();  
    
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
        pattenURL.add("statics");
        pattenURL.add("#");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;  
        HttpServletResponse httpResponse = (HttpServletResponse) response;  
        HttpSession session = httpRequest.getSession();  
        // 登陆url  
        String loginUrl = httpRequest.getContextPath()+"/";  
        String url = httpRequest.getRequestURI().toString();  
        
        /* 
         * 注：在pattenURL中的静态资源不拦截 
         * url.indexOf(urlStr) > -1  表示urlStr在url中出现过，出现就不拦截 
         * */   
        for (String urlStr : pattenURL) {  
            if(url.indexOf(urlStr) > -1){  
                chain.doFilter(request, response);  
                return;  
            }  
        }  
          
        //从session里取用户信息
        String userName = (String) session.getAttribute("userName");
         
        //登录请求不拦截,已登录不拦截
        if(url.endsWith(loginUrl) || (userName != null && !"".equals(userName))){ 
        	if(userName != null && !"".equals(userName)){
            	session.setMaxInactiveInterval(30 * 60);
        	}
            chain.doFilter(request, response);  
            return;
        } 
        /* 
         * 超时处理，ajax请求超时设置超时状态，页面请求超时则返回提示并重定向 
         * session.getAttribute("")是获取到登录人的session信息 
         * */
        if (userName == null || "".equals(userName)) {  
            // 判断是否为ajax请求  
            if (httpRequest.getHeader("x-requested-with") != null   
                    && httpRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {  
                httpResponse.addHeader("sessionstatus", "timeOut"); //返回超时标识  
                httpResponse.addHeader("loginPath", loginUrl);// 返回url  
                chain.doFilter(request, response);// 不可少，否则请求会出错  
            } else { 
                //会话过期 or 未登录，重新登录
            	//httpResponse.sendRedirect(loginUrl);
            	java.io.PrintWriter out = response.getWriter();    
                out.println("<html>");    
                out.println("<script>");    
                out.println("window.open ("+loginUrl+",'_top')");    
                out.println("</script>");    
                out.println("</html>");    
                return;  
            }  
        } else {  
            chain.doFilter(request, response);  
        } 
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
