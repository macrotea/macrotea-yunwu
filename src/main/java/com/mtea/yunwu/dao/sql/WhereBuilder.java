/**
 * Copyright (C) 2012 macrotea@qq.com Inc., All Rights Reserved.
 */
package com.mtea.yunwu.dao.sql;

import com.mtea.yunwu.model.ext.SqlParamBean;

/**
 * Where条件Sql及其参数构建器
 * @author 	liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2012-12-12 上午10:19:59	
 */
public abstract class WhereBuilder {

	public String and(String condition) {
		return condition;
	}

	public abstract SqlParamBean buildSqlParamBean();

}
