/**
 * 
 */
package com.mtea.yunwu.utils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.mtea.yunwu.model.core.BaseModel;

/**
 * 模型内省器
 * 
 * @author macrotea@qq.com
 * @date 2012-11-29 下午10:50:17
 * @version 1.0
 * @note
 */
public class ModelInspector{
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 反射忽略的属性名集合
	 */
	private static List<String> ignorePropNameList = Lists.newArrayList("class");

	/**
	 * 表名单词分隔符
	 */
	private static String TB_NAME_SEPERATOR = "_";

	/**
	 * 表名前缀
	 */
	private static String TB_NAME_PREFIX = "tb" + TB_NAME_SEPERATOR;
	
	/**
	 * 模型类
	 */
	private Class<?> modelClazz;
	
	/**
	 * 表名
	 */
	private String tableName;
	
	/**
	 * 根据模型类构造
	 * @param modelClazz
	 */
	private ModelInspector(Class<?> modelClazz) {
		super();
		this.modelClazz = modelClazz;
		init();
	}

	/**
	 * 初始化
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午9:53:57
	 */
	private void init() {
		createTableName();
	}

	/**
	 * 获得特定模型类的模型内省器
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午9:55:33
	 * @param modelClazz
	 * @return
	 */
	public static ModelInspector forClazz(Class<?> modelClazz) {
		return new ModelInspector(modelClazz);
	}

	/**
	 * 获得常规列名
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午7:47:28
	 * @return
	 */
	public String[] getCommonColumns() {
		List<String> retVal = new ArrayList<String>();
		PropertyDescriptor[] propDescriptors = ModelDescriptorCache.getInstance().getDescriptors(modelClazz);
		try {
			for (PropertyDescriptor propertyDescriptor : propDescriptors) {
				// 属性名==列名
				String propertyName = propertyDescriptor.getName();
				// 排除主键列
				if (!propertyName.equals(getPKColumn()) && !ignorePropNameList.contains(propertyName)) {
					retVal.add(propertyName);
				}
			}
		} catch (Exception e) {
			logger.error("获得常规列名出错",e);
		}
		return retVal.toArray(new String[retVal.size()]);
	}

	/**
	 * 获得主键列名
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午7:47:38
	 * @return
	 */
	public String getPKColumn() {
		return BaseModel.PK;
	}

	/**
	 * 创建表名
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午7:48:39
	 * @return
	 */
	private void createTableName() {
		// TODO lqy/2012-12-12 使用策略模式
		String clazzName = modelClazz.getSimpleName();
		String[] c = StringUtils.splitByCharacterTypeCamelCase(clazzName);
		String r = StringUtils.join(c, TB_NAME_SEPERATOR);
		setTableName(TB_NAME_PREFIX + r.toLowerCase());
	}

	public Class<?> getModelClazz() {
		return modelClazz;
	}

	public void setModelClazz(Class<?> modelClazz) {
		this.modelClazz = modelClazz;
	}

	public String getTableName() {
		return tableName;
	}

	/**
	 * 防止外部影响
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:09:47
	 * @param tableName
	 */
	private void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
}
