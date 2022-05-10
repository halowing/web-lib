package com.halowing.spring.data.mybatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(String[].class)
@MappedJdbcTypes(value = JdbcType.VARCHAR)
public class StringArrayTypeHandler extends BaseTypeHandler<String[]> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
		
		if(parameter == null || parameter.length == 0) {
			ps.setString(i, null);
			return;
		}
		
		int len = parameter.length;
		String str = "";
		for(int idx=0; idx<len;idx++) {
			str += parameter[idx].trim() + ",";
		}
		
		ps.setString(i, str.substring(0,str.length()-1));
	}

	@Override
	public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return convert(rs.getString(columnName));
	}

	@Override
	public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return convert(rs.getString(columnIndex));
	}

	@Override
	public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return convert(cs.getString(columnIndex));
	}
	
	private String[] convert(String str) {
		if(str == null) return null;
		
		return str.split(",");
	}

}
