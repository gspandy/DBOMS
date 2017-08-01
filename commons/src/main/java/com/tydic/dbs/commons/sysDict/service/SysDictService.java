/**
 * 
 */
package com.tydic.dbs.commons.sysDict.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tydic.dbs.commons.sysDict.mapper.SysDict;
import com.tydic.dbs.commons.utils.DbCommon;

/**
 * <p>Title: </p>
 * <p>Description:   </p>
 * <p>Company: 从兴技术有限公司 </p>
 * @author: yuchanghong
 * @version 1.0
 * @date: 2014-3-21 上午10:29:57
 */

public class SysDictService {

	public List<SysDict> getSysDicts() {
		List<SysDict> sysDicts = new ArrayList<SysDict>();
		SysDict sysDict = null;
		Connection conn = DbCommon.getConnection();
		Statement sts = null;
		ResultSet rs = null;
		try {
			sts = conn.createStatement();
			rs = sts.executeQuery("SELECT * FROM SYS_DICT ORDER BY DICT_CODE,REORDER");
			while (rs.next()) {
				sysDict = new SysDict();
				sysDict.setDictCode(rs.getString("DICT_CODE"));
				sysDict.setDictName(rs.getString("DICT_NAME"));
				sysDict.setDictKey(rs.getString("DICT_KEY"));
				sysDict.setDictValue(rs.getString("DICT_VALUE"));
				sysDicts.add(sysDict);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbCommon.closeConnection(conn);
		}
		return sysDicts;
	}
}
