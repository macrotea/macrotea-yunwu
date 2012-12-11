package com.mtea.yunwu.dao.exception;

/**
 * Dao层异常类
 * @author macrotea@qq.com
 * @date 2012-11-30 下午8:43:48
 * @version 1.0
 * @note
 */
public class DaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 一参构造
	 * @param message
	 */
	public DaoException(String message) {
		super(message);
	}

}
