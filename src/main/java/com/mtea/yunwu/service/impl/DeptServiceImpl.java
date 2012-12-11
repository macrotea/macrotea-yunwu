package com.mtea.yunwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtea.yunwu.dao.DeptDao;
import com.mtea.yunwu.dao.exception.DaoException;
import com.mtea.yunwu.model.core.Dept;
import com.mtea.yunwu.service.DeptService;

/**
 * 部门服务接口实现类
 * @author macrotea@qq.com
 * @date 2012-12-1 下午9:08:44
 * @version 1.0
 * @note
 */
@Service
public class DeptServiceImpl implements DeptService{
	
	@Autowired
	private DeptDao deptDao;

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.DeptService#addDept(com.mtea.yunwu.model.core.Dept)
	 */
	public Dept addDept(Dept dept) {
		return deptDao.save(dept);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.DeptService#deleteDept(long)
	 */
	public long deleteDept(long id) {
		return deptDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.DeptService#deleleAllDept()
	 */
	public long deleleAllDept() {
		return deptDao.deleleAll();
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.DeptService#getAllDept()
	 */
	public List<Dept> getAllDept() {
		return deptDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.DeptService#countAllDept()
	 */
	public Long countAllDept() {
		return deptDao.countAll();
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.DeptService#getDeptById(long)
	 */
	public Dept getDeptById(long id) {
		return deptDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.DeptService#updateDeptById(com.mtea.yunwu.model.core.Dept)
	 */
	public long updateDeptById(Dept dept) throws DaoException {
		return deptDao.updateById(dept);
	}

}
