package com.tydic.commons.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

public class DbHelperDao {
	private Logger log = Logger.getLogger(DbHelperDao.class);

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Map<String, Object>> queryByJdbc(String sql) throws Exception{

		return jdbcTemplate.queryForList(sql);
	}

	//取数合并时，多个sql 直接返回结果集再处理....
	public List<Map<String, Object>> queryByJdbcRs(String sql) throws Exception{
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		long c1=System.currentTimeMillis();
		SqlRowSet sRs = jdbcTemplate.queryForRowSet(sql);
		log.info("执行sql获取结果集..."+(System.currentTimeMillis()-c1)+"ms");
		SqlRowSetMetaData rsmd = sRs.getMetaData();
		long c2=System.currentTimeMillis();
		while (sRs.next()) {
			Map<String, Object> row = new HashMap<String, Object>();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				row.put(rsmd.getColumnName(i), sRs.getObject(i));
			}
			result.add(row);
		}
		log.info("数据组装..."+(System.currentTimeMillis()-c2)+"ms");
		return result;
	}


}
