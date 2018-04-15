package com.youotech.filter;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.youotech.entity.OperationLogInfo;
import com.youotech.entity.UserInfo;
import com.youotech.service.LogInfoService;
import com.youotech.shiro.ShiroSessionUtils;
import com.youotech.util.LogAnnotation;

@Aspect
@Component
public class LogAopAction {
	@Autowired
	private LogInfoService logInfoService;

	// 配置接入点,如果不知道怎么配置,可以百度一下规则
	@Pointcut("execution(* com.youotech.service.*.*(..)) && @annotation(com.youotech.util.LogAnnotation)")
	private void controllerAspect() {
	}// 定义一个切入点

	@Around("controllerAspect()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		// 常见日志实体对象
		OperationLogInfo log = new OperationLogInfo();
		// 获取登录用户账户
		UserInfo user = ShiroSessionUtils.getLoginAccount();
		if (user != null) {
			log.setUserId(user.getId());
			log.setUserName(user.getUserName());
		}
		// 获取系统时间
		String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
		log.setLogDate(time);

		// 方法通知前获取时间,为什么要记录这个时间呢？当然是用来计算模块执行时间的
		long start = System.currentTimeMillis();
		// 拦截的实体类，就是当前正在执行的controller
		Object target = pjp.getTarget();
		// 拦截的方法名称。当前正在执行的方法
		
		MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method methodAnno = ms.getMethod();
        LogAnnotation anno = methodAnno.getAnnotation(LogAnnotation.class);
		String methodName = pjp.getSignature().getName();
		log.setLogCrud(anno.crud());
		// 拦截的方法参数
		Object[] args = pjp.getArgs();
		String aa = args.toString();
		String methodArgs = "";
		for (Object arg : args) {
			if(arg!=""&&arg!=null){
				methodArgs += arg + ",";
			}
		}
		log.setLogData(anno.data()+methodArgs);
		logInfoService.saveLogInfo(log);
		
		// 拦截的放参数类型
		Signature sig = pjp.getSignature();
		MethodSignature msig = null;
		if (!(sig instanceof MethodSignature)) {
			throw new IllegalArgumentException("该注解只能用于方法");
		}
		msig = (MethodSignature) sig;
		Class[] parameterTypes = msig.getMethod().getParameterTypes();

		Object object = null;
		// 获得被拦截的方法
		Method method = null;
		try {
			method = target.getClass().getMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (null != method) {
			// 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
			if (method.isAnnotationPresent(LogAnnotation.class)) {
				LogAnnotation systemlog = method.getAnnotation(LogAnnotation.class);

				try {
					object = pjp.proceed();
					long end = System.currentTimeMillis();
					// 将计算好的时间保存在实体中

					// 保存进数据库
					// logservice.saveLog(log);
					System.out.println("执行了一次:" + log);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					long end = System.currentTimeMillis();

					// logservice.saveLog(log);
					System.out.println("执行了一次:" + log);
				}
			} else {// 没有包含注解
				object = pjp.proceed();
			}
		} else { // 不需要拦截直接执行
			object = pjp.proceed();
		}
		return object;
	}
}
