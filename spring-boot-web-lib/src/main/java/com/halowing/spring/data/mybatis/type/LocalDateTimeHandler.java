package com.halowing.spring.data.mybatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.halowing.util.TimeUtility;

/**
 * LocalDateTime 를 CHAR(14)로 변환
 * yyyyMMddHHmmss
 * @author sgkim <sgkim@thecnp.co.kr>
 *
 */
@MappedJdbcTypes(JdbcType.CHAR)
@MappedTypes(LocalDateTime.class)
public class LocalDateTimeHandler extends BaseTypeHandler<LocalDateTime> {
	
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime ldt, JdbcType jdbcType)
			throws SQLException {
		
		ps.setString(i,ldt.format(TimeUtility.DB_DATE_TIME_FORMATTER) );
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String str = rs.getString(columnName);
		return parse(str);
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String str = rs.getString(columnIndex);
		return parse(str);
	}

	@Override
	public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String str = cs.getString(columnIndex);
		return parse(str);
	}
	
	private LocalDateTime parse(String str) {
		if(str == null) return null;
		
		return LocalDateTime.parse(str, TimeUtility.DB_DATE_TIME_FORMATTER);
	}

}
