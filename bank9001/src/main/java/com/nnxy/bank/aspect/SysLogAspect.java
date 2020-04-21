/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.nnxy.bank.aspect;

import com.google.gson.Gson;
import com.nnxy.bank.service.SyslogService;
import com.nnxy.syslog.annotation.Syslog;
import com.nnxy.syslog.entity.SyslogEntity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;


/**
 * 系统日志，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
public class SysLogAspect {
	@Autowired
	private SyslogService syslogService;
	
	@Pointcut("@annotation(com.nnxy.syslog.annotation.Syslog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Class<?>[] parameterTypes = method.getParameterTypes();
		SyslogEntity sysLog = new SyslogEntity();
		Syslog syslog = method.getAnnotation(Syslog.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = new Gson().toJson(args);
			sysLog.setPrams(params);
		}catch (Exception e){

		}


//		//用户名，前端获取，后端默认存储admin
		String username = "admin";
		sysLog.setUsername(username);

		sysLog.setOperationTime(time);

		//保存系统日志
		syslogService.save(sysLog);
	}
}
