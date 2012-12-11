package com.mtea.yunwu.gen;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * 表模型生成器
 * 通过Hibernate生成实体的表结构
 * @author macrotea@qq.com
 * @date 2012-11-29 下午9:57:07
 * @version 1.0
 * @note
 */
public class ModelGenerator {
	
	public static void main(String[] args) {
		Configuration cfg =new Configuration().configure();
		
		SchemaExport exporter = new SchemaExport(cfg);
		exporter.create(true, true);
	}
	
}
