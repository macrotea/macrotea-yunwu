/**
 * Copyright (C) 2012 macrotea@qq.com Inc., All Rights Reserved.
 */
package com.mtea.yunwu;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 	liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2012-12-12 上午11:23:08	
 */
public class LoggerTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void trace(){
		if(logger.isTraceEnabled()){
			logger.trace("isTraceEnabled : " + logger.isTraceEnabled());
			logger.trace("this is trace !");
		}else{
			System.out.println(">> isTraceEnabled : " + logger.isTraceEnabled());
			logger.trace("this is trace !");
		}
		
	} 
	@Test
	public void debug(){
		logger.debug("this is debug !");
	} 
	@Test
	public void info(){
		logger.info("this is info !");
	} 
	@Test
	public void warn(){
		logger.warn("this is warn !");
	} 
	@Test
	public void error(){
		logger.error("this is error !",new RuntimeException("this is throwing RuntimeException "));
	} 

}
