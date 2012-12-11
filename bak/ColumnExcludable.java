package com.mtea.yunwu.dao.sql;

/**
 * 列可不包含接口
 * 
 * @author macrotea@qq.com
 * @date 2012-12-4 上午12:28:05
 * @version 1.0
 * @note
 */
public interface ColumnExcludable {
	
	/**
	 * 是否可以不包含
	 * @author macrotea@qq.com
	 * @date 2012-12-4 上午12:29:36
	 * @param currColumn
	 * @return
	 */
	public boolean isExclude(String currColumn);
	
}