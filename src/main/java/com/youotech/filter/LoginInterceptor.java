package com.youotech.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session= request.getSession();
		String loginUrl = request.getContextPath()+"/";  
		String url = request.getRequestURI();
		if(url.endsWith("login.do")){
			return true;
		}
		if(session==null){
			response.sendRedirect("/huangshan");
			return false;
		}
		Object object = session.getAttribute("userName");
		if(object==null){
//			response.sendRedirect("/huangshan");
			java.io.PrintWriter out = response.getWriter();    
	        out.println("<html>");    
	        out.println("<script>");    
	        out.println("window.open ("+loginUrl+",'_top')");    
	        out.println("</script>");    
	        out.println("</html>");    
			return false;
		}
		String userName = object.toString();
		if (userName == null || userName == "") {
			response.sendRedirect("/huangshan");
			return false;
		}else if(userName.equals("userName")){
			response.sendRedirect("/huangshan");
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
