package com.mtea.yunwu.dao;

import com.mtea.yunwu.model.core.Dept;
import com.mtea.yunwu.utils.Pager;

/**
 * 部门数据库访问接口
 * @author macrotea@qq.com
 * @version v1.0
 * @date 2012-11-29 下午5:14:55
 * @note
 */
public interface DeptDao extends BaseDao<Dept>{

	/**
	 * 根据页码,条件实例查找数据,返回分页器
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:52:52
	 * @param page
	 * @param criteriaDept
	 * @return
	 */
	Pager<Dept> findPage(int page, Dept criteriaDept);
	
	/**
	 * 根据页码,页大小,条件实例查找数据,返回分页器
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:52:59
	 * @param page
	 * @param pageSize
	 * @param criteriaDept
	 * @return
	 */
	Pager<Dept> findPage(int page,int pageSize, Dept criteriaDept);
	
}
