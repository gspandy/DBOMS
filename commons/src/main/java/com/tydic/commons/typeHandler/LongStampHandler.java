package com.tydic.commons.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.LongTypeHandler;

public class LongStampHandler extends LongTypeHandler {
	  @Override  
	    public void setParameter(PreparedStatement ps, int i, Long parameter,  
	            JdbcType jdbcType) throws SQLException {  
	        ps.setLong(i, parameter);  
	    }  
	  
	    @Override  
	    public Long getResult(ResultSet rs, String columnName)  
	            throws SQLException {  
	        return Long.valueOf(rs.getLong(columnName));  
	    }  
	  
	    @Override  
	    public Long getResult(ResultSet rs, int columnIndex) throws SQLException {  
	    	return Long.valueOf(rs.getLong(columnIndex));
	    }  
	  
	    @Override  
	    public Long getResult(CallableStatement cs, int columnIndex)  
	            throws SQLException {  
	        return Long.valueOf(cs.getLong(columnIndex));  
	    }  
	  
}
