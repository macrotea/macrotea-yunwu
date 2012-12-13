/**
 * Copyright (C) 2012 macrotea@qq.com Inc., All Rights Reserved.
 */
package com.mtea.yunwu.dao.sql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mtea.yunwu.model.ext.SqlParamBean;

/**
 * Where条件Sql及其参数构建器
 * @author 	liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2012-12-12 上午10:19:59	
 */
public abstract class WhereBuilder {
	
	private StringBuilder conditionBuilder;
	
	private List<Object> paramList;

	public WhereBuilder() {
		super();
		this.conditionBuilder = new StringBuilder();
		this.paramList = new ArrayList<Object>();
	}

	/**
	 * Where条件的SQL及其参数的构建过程
	 * (子类覆盖)
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:14:00
	 * @return
	 */
	public abstract SqlParamBean buildSqlParamBean();
	
	/*
	 * SQL 语句处理
	 *--------------------------------*/

	/**
	 * 处理条件与(AND)
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:13:38
	 * @param condition
	 * @return
	 */
	public String and(String condition) {
		return condition;
	}

	/**
	 * 非空
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:13:34
	 * @param value
	 * @return
	 */
	public boolean notBlank(String value){
		return StringUtils.isNotBlank(value);
	}

	/**
	 * AND XX = ?
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:25:26
	 * @param propName
	 * @return
	 */
	public StringBuilder sqlAndEquals(String propName) {
		conditionBuilder.append(String.format(" AND %s = ?", propName));
		return conditionBuilder;
	}

	/**
	 * AND XX LIKE ?
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:25:26
	 * @param propName
	 * @return
	 */
	public StringBuilder sqlAndLike(String propName) {
		conditionBuilder.append(String.format(" AND %s LIKE ?", propName));
		return conditionBuilder;
	}
	
	/**
	 * 返回SQL语句
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:25:52
	 * @return
	 */
	public String sql(){
		return conditionBuilder.toString();
	}

	
	/*
	 * SQL 参数处理
	 *--------------------------------*/
	
	public void addParamLike(Object paramValue) {
		this.paramList.add("%" + paramValue + "%");
	}
	
	public void addParamLeftLike(Object paramValue) {
		this.paramList.add("%" + paramValue);
	}
	
	public void addParamRightLike(Object paramValue) {
		this.paramList.add(paramValue + "%");
	}
	
	public void addParam(Object paramValue) {
		this.paramList.add(paramValue);
	}
	
	/**
	 * 子类获取且进行相关操作
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:26:01
	 * @return
	 */
	public StringBuilder getConditionBuilder() {
		return conditionBuilder;
	}

	/**
	 * 子类获取且进行相关操作
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:26:01
	 * @return
	 */
	public List<Object> getParamList() {
		return paramList;
	}
	
	
	
}
