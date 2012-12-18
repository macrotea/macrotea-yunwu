package com.mtea.yunwu.dao;

import com.mtea.yunwu.model.core.Position;
import com.mtea.yunwu.utils.Pager;

/**
 * 职位数据库访问接口
 * @author macrotea@qq.com
 * @version v1.0
 * @date 2012-11-29 下午5:14:55
 * @note
 */
public interface PositionDao extends BaseDao<Position>{

	/**
	 * 根据页码,条件实例查找数据,返回分页器
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:52:52
	 * @param page
	 * @param criteriaPosition
	 * @return
	 */
	Pager<Position> findPage(int page, Position criteriaPosition);
	
	/**
	 * 根据页码,页大小,条件实例查找数据,返回分页器
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:52:59
	 * @param page
	 * @param pageSize
	 * @param criteriaPosition
	 * @return
	 */
	Pager<Position> findPage(int page,int pageSize, Position criteriaPosition);
	
}
