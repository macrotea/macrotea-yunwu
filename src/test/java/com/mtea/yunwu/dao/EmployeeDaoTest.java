package com.mtea.yunwu.dao;

import java.util.Date;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mtea.yunwu.model.core.Employee;
import com.mtea.yunwu.test.AbstractTestCase;

/**
 * EmployeeDao测试类
 * 
 * @author macrotea@qq.com
 * @date 2012-11-30 下午9:25:15
 * @version 1.0
 * @note
 */
public class EmployeeDaoTest extends AbstractTestCase{

	@Autowired
	private EmployeeDao employeeDao;

	@Test
	public void save() {
		Employee e = mockEmployee();
		employeeDao.save(e);
		Assert.assertTrue(e.getId() != null);
	}

	@Test
	public void delete() {
		Employee e = mockEmployee();
		employeeDao.save(e);
		long rowsAffected = employeeDao.delete(e.getId());
		Assert.assertTrue(rowsAffected == 1);
	}

	@Test
	public void deleleAll() {
		employeeDao.deleleAll();
		Assert.assertTrue(employeeDao.countAll() == 0);
	}

	@Test
	public void findAll() {
		employeeDao.deleleAll();
		Employee e = mockEmployee();
		employeeDao.save(e);
		List<Employee> list = employeeDao.findAll();
		Assert.assertTrue(list.size() == 1);

		e = mockEmployee();
		employeeDao.save(e);
		list = employeeDao.findAll();
		Assert.assertTrue(list.size() == 2);
	}
	
	@Test
	public void countAll() {
		employeeDao.deleleAll();
		Employee e = mockEmployee();
		employeeDao.save(e);
		Assert.assertTrue(employeeDao.countAll() == 1);
		
		e = mockEmployee();
		employeeDao.save(e);
		Assert.assertTrue(employeeDao.countAll() == 2);
	}
	
	@Test
	public void findById() {
		Employee e = mockEmployee();
		employeeDao.save(e);
		Employee result = employeeDao.findById(e.getId());
		Assert.assertTrue(result.getId().longValue()==e.getId().longValue());
	}
	
	@Test
	public void updateById() {
		Employee e = mockEmployee();
		employeeDao.save(e);
		
		Employee result = employeeDao.findById(e.getId());
		result.setRealName("XX");
		employeeDao.updateById(result);
		
		Employee result2 = employeeDao.findById(e.getId());
		Assert.assertTrue(result2.getRealName().equals("XX"));
	}

	/**
	 * 模拟用户
	 * 
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午9:33:14
	 * @return
	 */
	private Employee mockEmployee() {
		int r =new Random().nextInt(10);
		Employee e =new Employee();
		e.setRealName("张三" + r);
		e.setAge(23);
		e.setAliasName("张总");
		e.setEmail("zs@qq.com");
		e.setGender(1);
		e.setPhoneNumber("1382342342");
		e.setPinyinShort("zs");
		
		e.setRemark("备注人生" + r);
		e.setAddTime(new Date());
		e.setEditTime(new Date());
		return e;
	}

}
