package com.halowing.spring.data.mybatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * Locale.Class 를 VARCHAR로 변환
 * ko_KR, ko 형태로 입력
 * @author sgkim <sgkim@thecnp.co.kr>
 *
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Locale.class)
public class LocaleTypeHandler extends BaseTypeHandler<Locale> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Locale locale, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i,locale.toString() );
	}

	@Override
	public Locale getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String str = rs.getString(columnName);
		return getLocale(str);
	}

	@Override
	public Locale getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String str = rs.getString(columnIndex);
		return getLocale(str);
	}

	@Override
	public Locale getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String str = cs.getString(columnIndex);
		return getLocale(str);
	}
	
	private Locale getLocale(String str) {
		if(str == null) return null;
		String[] strs = str.split("_");
		if(strs.length > 1) return new Locale(strs[0], strs[1]);
		else return new Locale(strs[0]);
	}

}
