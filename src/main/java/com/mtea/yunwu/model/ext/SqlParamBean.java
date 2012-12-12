/**
 * Copyright (C) 2012 macrotea@qq.com Inc., All Rights Reserved.
 */
package com.mtea.yunwu.model.ext;

import java.util.List;

/**
 * Sql及其参数的Bean
 * @author 	liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2012-12-12 上午10:24:01	
 */
public class SqlParamBean {
	
	private String sql;
	
	private List<Object> paramList;

	public SqlParamBean() {
		super();
	}

	public SqlParamBean(String sql, List<Object> paramList) {
		super();
		this.sql = sql;
		this.paramList = paramList;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getParamList() {
		return paramList;
	}

	public void setParamList(List<Object> paramList) {
		this.paramList = paramList;
	}

	@Override
	public String toString() {
		return "ParamSqlBean [sql=" + sql + ", paramList=" + paramList + "]";
	}
	
}
