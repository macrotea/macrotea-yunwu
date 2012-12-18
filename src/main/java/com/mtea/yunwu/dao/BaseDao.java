package com.mtea.yunwu.dao;

import java.util.List;

/**
 * 父级数据访问接口
 * @author macrotea@qq.com
 * @date 2012-12-17 上午12:47:55
 * @version 1.0
 * @note
 */
public interface BaseDao<T> {

	/**
	 * 保存
	 * @author macrotea@qq.com
	 * @date 2012-12-17 上午12:48:27
	 * @param modelInstance
	 * @return
	 */
	T save(T modelInstance);

	/**
	 * 删除
	 * @author macrotea@qq.com
	 * @date 2012-12-17 上午12:48:48
	 * @param id
	 * @return
	 */
	long delete(long id);

	/**
	 * 删除所有
	 * @author macrotea@qq.com
	 * @date 2012-12-17 上午12:48:52
	 * @return
	 */
	long deleleAll();

	/**
	 * 获得所有
	 * @author macrotea@qq.com
	 * @date 2012-12-17 上午12:49:01
	 * @return
	 */
	List<T> findAll();

	/**
	 * 根据Id查找
	 * @author macrotea@qq.com
	 * @date 2012-12-17 上午12:49:17
	 * @param id
	 * @return
	 */
	T findById(long id);

	/**
	 * 计算总行数
	 * @author macrotea@qq.com
	 * @date 2012-12-17 上午12:49:32
	 * @return
	 */
	Long countAll();
	
	/**
	 * 根据SQL和数据Bean更新
	 * @author macrotea@qq.com
	 * @date 2012-12-17 上午1:06:11
	 * @param user
	 * @return
	 * @throws DaoException
	 */
	long updateById(String sql, T databean);

	/**
	 * 根据数据Bean更新
	 * (内置更新SQL的构建)
	 * @author macrotea@qq.com
	 * @date 2012-12-17 上午1:12:28
	 * @param databean
	 * @return
	 * @throws DaoException
	 */
	long updateById(T databean);

	/**
	 * 根据命名参数更新
	 * @author macrotea@qq.com
	 * @date 2012-12-17 上午1:14:50
	 * @param namedSql
	 * @param databean
	 * @return
	 */
	long updateByNamedParams(String namedSql, Object databean);

}
