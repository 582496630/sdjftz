package com.youotech.aop;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
    * @ClassName: TimeFilter
    * @Description: 利用springmvc拦截器统一记录日志功能
    * @author lfx
    * @date 2017年6月27日
    *
 */
public class TimeFilter implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(TimeFilter.class);
	public static final NumberFormat FORMAT = new DecimalFormat("0.000");
	long begin = 0;
	long end = 0;

	/**
	 * 在请求之前进行处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		begin = System.currentTimeMillis();
		return true;
	}

	/**
	 * 请求进行处理之后调用
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		end = System.currentTimeMillis();
	}

	/**
	 * 整个请求结束之后（渲染了对应的视图之后）调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) {
		BigDecimal processed = new BigDecimal(end - begin).divide(new BigDecimal(1000));
		String uri = ((HttpServletRequest) request).getRequestURI();
			logger.debug("Processed in {} second(s). URI={}", FORMAT.format(processed), uri);
	}

}
