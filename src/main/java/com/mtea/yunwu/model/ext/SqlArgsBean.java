package com.mtea.yunwu.model.ext;

import java.io.Serializable;
import java.util.List;

/**
 * SQL语句及其参数Bean
 * @author macrotea@qq.com
 * @date 2012-11-30 下午8:39:41
 * @version 1.0
 * @note
 */
public class SqlArgsBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private String sql;
	
	private List<Object> args;

	public SqlArgsBean(String sql, List<Object> args) {
		super();
		this.sql = sql;
		this.args = args;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getArgs() {
		return args;
	}

	public void setArgs(List<Object> args) {
		this.args = args;
	}

}
