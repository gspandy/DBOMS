/**
 * com.tydic.dbs.system.permission.helper.PermissionHelper.java
 */
package com.tydic.dbs.system.permission.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.danga.MemCached.MemCachedClient;
import com.tydic.dbs.system.role.dao.SysPermissionDao;
import com.tydic.dbs.system.role.mapper.SysPermission;


/**
 * @file  PermissionHelper.java
 * @author caipeimin
 * @version 0.1
 * @Permission工具类
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-06-23 13:14:44
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class PermissionHelper {
	private static final Logger logger = Logger.getLogger(PermissionHelper.class);
	private static PermissionHelper permissionHelper;
	private static final ClassPathXmlApplicationContext context = 
			new ClassPathXmlApplicationContext(new String[] {"classpath*:memcached-datasource.xml", "classpath*:system-dao.xml"});
	private static Integer timeout = 600;	//默认600秒
	private static Boolean enable = true;
	
	private PermissionHelper() {
	};
	
	public static PermissionHelper getInstance() {
		if (permissionHelper == null) {
			permissionHelper = new PermissionHelper();
			
			InputStream is = PermissionHelper.class.getClassLoader().getResourceAsStream("memcached.properties");
			String[] servers = null;
			try {
				Properties properties = new Properties();
				properties.load(is);
				servers = ((String) properties.get("servers")).split(",");
				timeout = Integer.valueOf((String) properties.get("timeout"));
				enable = Boolean.valueOf((String) properties.get("enable"));
			} catch (IOException e) {
				logger.error(e.getMessage());
			} finally {
				if (is != null)
					try {
						is.close();
					} catch (IOException e) {
						logger.error(e.getMessage());
					}
			}
			
			/*if (enable) {
		        //初始化SockIOPool，管理连接池
		        SockIOPool pool = SockIOPool.getInstance();
		        pool.setServers(servers);
		        pool.setFailover(true);
		        pool.setInitConn(10);
		        pool.setMinConn(5);
		        pool.setMaxConn(250);
		        pool.setMaintSleep(30);
		        pool.setNagle(false);
		        pool.setSocketTO(3000);
		        pool.setAliveCheck(true);
		        pool.initialize();
			}*/
		}
		return permissionHelper;
	}
	
	public SysPermission getPermissionByOperatorId(String operatorId) throws Exception {
		if (enable) {
			return getPermissionWithCacheMechanism(operatorId);
		} else {
			return getPermissionWithoutCacheMechanism(operatorId);
		}
	}
	
	private SysPermission getPermissionWithCacheMechanism(String operatorId) throws Exception {
		SysPermission sysPermission = null;
		
		//Find the permission object by operatorId from cache
		MemCachedClient memCachedClient = new MemCachedClient();
		memCachedClient.setPrimitiveAsString(true);
		//sysPermission = getPermissionFromCache(memCachedClient, operatorId);
		sysPermission = getPermissionFromCache(operatorId);
		
		//If the permission does not exist then load it from database and put to cache
		if (sysPermission == null) {
			SysPermissionDao sysPermissionDao = (SysPermissionDao) context.getBean("sysPermissionDao");
			sysPermission = sysPermissionDao.selectPermissionByOperatorId(operatorId);
			//putPermissionToCache(memCachedClient, sysPermission);
			putPermissionToCache(sysPermission);
		}
        
		return sysPermission;
	}
	
	private SysPermission getPermissionWithoutCacheMechanism(String operatorId) throws Exception {
		SysPermissionDao sysPermissionDao = (SysPermissionDao) context.getBean("sysPermissionDao");
		return sysPermissionDao.selectPermissionByOperatorId(operatorId);
	}
	
	private SysPermission getPermissionFromCache(String operatorId) throws Exception {
		StringRedisTemplate redisTemplate = (StringRedisTemplate)context.getBean("redisTemplate");
		String permission = redisTemplate.opsForValue().get(operatorId);
		if (StringUtils.isEmpty(permission)) {
			return null;
		} else {
			return (SysPermission) JSONObject.toBean(JSONObject.fromObject(permission), SysPermission.class);
		}
	}
	
	private SysPermission getPermissionFromCache(MemCachedClient memCachedClient, String operatorId) throws Exception {
		String permission = (String) memCachedClient.get(operatorId);
		if (StringUtils.isEmpty(permission)) {
			return null;
		} else {
			return (SysPermission) JSONObject.toBean(JSONObject.fromObject(permission), SysPermission.class);
		}
	}
	
	private void putPermissionToCache(SysPermission permission) {
		if (permission != null) {
			StringRedisTemplate redisTemplate = (StringRedisTemplate)context.getBean("redisTemplate");
			redisTemplate.opsForValue().set(permission.getOperId(), JSONObject.fromObject(permission).toString(), timeout, TimeUnit.SECONDS);
			//memCachedClient.set(permission.getOperId(), JSONObject.fromObject(permission).toString(), calendar.getTime());
		}
    }
	
    private void putPermissionToCache(MemCachedClient memCachedClient, SysPermission permission) {
		if (permission != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.SECOND, timeout);
			memCachedClient.set(permission.getOperId(), JSONObject.fromObject(permission).toString(), calendar.getTime());
		}
    }
    
    public static void main(String[] args) {
    	PermissionHelper permissionHelper = PermissionHelper.getInstance();
    	try {
			SysPermission permission = permissionHelper.getPermissionByOperatorId("super");
			System.out.println(permission.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
