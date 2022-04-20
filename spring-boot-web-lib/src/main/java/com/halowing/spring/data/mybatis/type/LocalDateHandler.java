package com.halowing.spring.data.mybatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

/**
 * LocalDateTime 를 CHAR(8)로 변환
 * yyyyMMdd
 * @author sgkim <sgkim@thecnp.co.kr>
 *
 */
@MappedJdbcTypes(JdbcType.CHAR)
public class LocalDateHandler extends BaseTypeHandler<LocalDate> {
	
	private static final String DATE_PATTERN = "yyyyMMdd";

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalDate ld, JdbcType jdbcType)
			throws SQLException {
		
		ps.setString(i,ld.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) );
	}

	@Override
	public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String str = rs.getString(columnName);
		return parse(str);
	}

	@Override
	public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String str = rs.getString(columnIndex);
		return parse(str);
	}

	@Override
	public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String str = cs.getString(columnIndex);
		return parse(str);
	}
	
	private LocalDate parse(String str) {
		if(str == null) return null;
		
		return LocalDate.parse(str, DateTimeFormatter.ofPattern(DATE_PATTERN));
	}

}
