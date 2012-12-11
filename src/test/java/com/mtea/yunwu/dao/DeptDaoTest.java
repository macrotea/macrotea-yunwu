package com.mtea.yunwu.dao;

import java.util.Date;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mtea.yunwu.model.core.Dept;
import com.mtea.yunwu.test.AbstractTestCase;

/**
 * DeptDao测试类
 * 
 * @author macrotea@qq.com
 * @date 2012-11-30 下午9:25:15
 * @version 1.0
 * @note
 */
public class DeptDaoTest extends AbstractTestCase{

	@Autowired
	private DeptDao deptDao;

	@Test
	public void save() {
		Dept u = mockDept();
		deptDao.save(u);
		Assert.assertTrue(u.getId() != null);
	}

	@Test
	public void delete() {
		Dept u = mockDept();
		deptDao.save(u);
		long rowsAffected = deptDao.delete(u.getId());
		Assert.assertTrue(rowsAffected == 1);
	}

	@Test
	public void deleleAll() {
		deptDao.deleleAll();
		Assert.assertTrue(deptDao.countAll() == 0);
	}

	@Test
	public void findAll() {
		deptDao.deleleAll();
		Dept u = mockDept();
		deptDao.save(u);
		List<Dept> list = deptDao.findAll();
		Assert.assertTrue(list.size() == 1);

		u = mockDept();
		deptDao.save(u);
		list = deptDao.findAll();
		Assert.assertTrue(list.size() == 2);
	}
	
	@Test
	public void countAll() {
		deptDao.deleleAll();
		Dept u = mockDept();
		deptDao.save(u);
		Assert.assertTrue(deptDao.countAll() == 1);
		
		u = mockDept();
		deptDao.save(u);
		Assert.assertTrue(deptDao.countAll() == 2);
	}
	
	@Test
	public void findById() {
		Dept u = mockDept();
		deptDao.save(u);
		Dept result = deptDao.findById(u.getId());
		Assert.assertTrue(result.getId().longValue()==u.getId().longValue());
	}
	
	@Test
	public void updateById() {
		Dept d = mockDept();
		deptDao.save(d);
		
		Dept result = deptDao.findById(d.getId());
		result.setDeptName("XX");
		deptDao.updateById(result);
		
		Dept result2 = deptDao.findById(d.getId());
		Assert.assertTrue(result2.getDeptName().equals("XX"));
	}

	/**
	 * 模拟部门
	 * 
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午9:33:14
	 * @return
	 */
	private Dept mockDept() {
		int r =new Random().nextInt(10);
		Dept d =new Dept();
		d.setDeptName("研发部" + r);
		d.setRemark("这是备注" + r);
		d.setAddTime(new Date());
		d.setEditTime(new Date());
		d.setOrderValue(new Random().nextDouble()*100);
		return d;
	}

}
