package com.mtea.yunwu.utils;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;

/**
 * 模型描述者缓存类
 * @author macrotea@qq.com
 * @date 2012-11-30 下午9:02:25
 * @version 1.0
 * @note
 */
public class ModelDescriptorCache {
	private Map<Class<?>, PropertyDescriptor[]> cache = new HashMap<Class<?>, PropertyDescriptor[]>();

	private static ModelDescriptorCache instance;

	public static ModelDescriptorCache getInstance() {
		if (instance == null) {
			synchronized (ModelDescriptorCache.class) {
				if (instance == null) {
					instance = new ModelDescriptorCache();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 根据modelClazz从缓存中获得
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午9:08:12
	 * @param modelClazz
	 * @return
	 */
	public synchronized PropertyDescriptor[] getDescriptors(Class<?> modelClazz) {
		PropertyDescriptor[] propDescriptors = cache.get(modelClazz);
		if (propDescriptors == null) {
			propDescriptors = BeanUtils.getPropertyDescriptors(modelClazz);
			setDescriptors(modelClazz, propDescriptors);
		}
		return propDescriptors;
	}
	
	/**
	 * 根据modelClazz和descriptors设置进缓存
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午9:08:26
	 * @param modelClazz
	 * @param descriptors
	 */
	public synchronized void setDescriptors(Class<?> modelClazz,PropertyDescriptor[] descriptors) {
		cache.remove(modelClazz);
		cache.put(modelClazz, descriptors);
	}

}