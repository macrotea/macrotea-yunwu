package com.mtea.yunwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtea.yunwu.dao.PositionDao;
import com.mtea.yunwu.model.core.Position;
import com.mtea.yunwu.service.PositionService;
import com.mtea.yunwu.utils.Pager;

/**
 * 部门服务接口实现类
 * @author macrotea@qq.com
 * @date 2012-12-1 下午9:08:44
 * @version 1.0
 * @note
 */
@Service
public class PositionServiceImpl implements PositionService{
	
	@Autowired
	private PositionDao positionDao;

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.PositionService#addPosition(com.mtea.yunwu.model.core.Position)
	 */
	public Position addPosition(Position position) {
		return positionDao.save(position);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.PositionService#deletePosition(long)
	 */
	public long deletePosition(long id) {
		return positionDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.PositionService#deleleAllPosition()
	 */
	public long deleleAllPosition() {
		return positionDao.deleleAll();
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.PositionService#getAllPosition()
	 */
	public List<Position> getAllPosition() {
		return positionDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.PositionService#countAllPosition()
	 */
	public Long countAllPosition() {
		return positionDao.countAll();
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.PositionService#getPositionById(long)
	 */
	public Position getPositionById(long id) {
		return positionDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.PositionService#updatePositionById(com.mtea.yunwu.model.core.Position)
	 */
	public long updatePositionById(Position position) {
		Position oriPosition = getPositionById(position.getId());
		position.setAddTime(oriPosition.getAddTime());
		return positionDao.updateById(position);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.PositionService#searchPage(int, com.mtea.yunwu.model.core.Position)
	 */
	@Override
	public Pager<Position> searchPage(int page, Position position) {
		return positionDao.findPage(page,position);
	}

}
