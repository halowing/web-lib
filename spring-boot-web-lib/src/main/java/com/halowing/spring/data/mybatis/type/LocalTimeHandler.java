package com.halowing.spring.data.mybatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

/**
 * LocalDateTime 를 CHAR(14)로 변환
 * yyyyMMddHHmmss
 * @author sgkim <sgkim@thecnp.co.kr>
 *
 */
@MappedJdbcTypes(JdbcType.CHAR)
public class LocalTimeHandler extends BaseTypeHandler<LocalTime> {
	
	private static final String PATTERN = "HHmm";

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalTime ldt, JdbcType jdbcType)
			throws SQLException {
		
		ps.setString(i,ldt.format(DateTimeFormatter.ofPattern(PATTERN)) );
	}

	@Override
	public LocalTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String str = rs.getString(columnName);
		return parse(str);
	}

	@Override
	public LocalTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String str = rs.getString(columnIndex);
		return parse(str);
	}

	@Override
	public LocalTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String str = cs.getString(columnIndex);
		return parse(str);
	}
	
	private LocalTime parse(String str) {
		if(str == null) return null;
		
		return LocalTime.parse(str, DateTimeFormatter.ofPattern(PATTERN));
	}

}