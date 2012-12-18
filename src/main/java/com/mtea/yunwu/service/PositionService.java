package com.mtea.yunwu.service;

import java.util.List;

import com.mtea.yunwu.model.core.Position;
import com.mtea.yunwu.utils.Pager;

/**
 * 职位服务接口
 * @author macrotea@qq.com
 * @date 2012-12-1 下午9:09:02
 * @version 1.0
 * @note
 */
public interface PositionService {
	
	/**
	 * 添加职位
	 * @return
	 * macrotea / 2012-11-29 下午5:14:10
	 */
	Position addPosition(Position position);
	
	/**
	 * 根据Id删除职位且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:26
	 */
	long deletePosition(long id);
	
	/**
	 * 删除所有职位且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午6:39:33
	 */
	long deleleAllPosition();
	
	/**
	 * 获得所有的职位
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	List<Position> getAllPosition();
	
	/**
	 * 计算总行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	Long countAllPosition();
	
	/**
	 * 根据Id获得职位
	 * @param id
	 * @return
	 * macrotea / 2012-11-29 下午6:06:18
	 */
	Position getPositionById(long id);
	
	/**
	 * 根据Id更新职位且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:46
	 * @throws DaoException 
	 */
	long updatePositionById(Position position);
	
	/**
	 * 搜索分页数据
	 * @author macrotea@qq.com
	 * @date 2012-12-10 下午11:38:19
	 * @param i
	 * @param positionCriteria
	 * @return
	 */
	Pager<Position> searchPage(int page, Position positionCriteria);

}
