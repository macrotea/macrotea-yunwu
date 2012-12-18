/**
 * Copyright (C) 2012 macrotea@qq.com Inc., All Rights Reserved.
 */
package com.mtea.yunwu.gen.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mtea.yunwu.model.core.BaseModel;

/**
 * 职位
 * @author liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2012-12-17 下午4:01:37
 */
@Entity
@Table(name="tb_position")
public class Position extends BaseModel {
	private static final long serialVersionUID = 1L;

	private String name;
	
	@ManyToOne(targetEntity=Dept.class)
	@JoinColumn(name="deptId")
	private Dept dept;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	

}
