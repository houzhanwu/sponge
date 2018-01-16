package com.apr7.sponge.utils.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JSONObjectTypeHandler extends BaseTypeHandler<JSONObject> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, JSONObject parameter, JdbcType jdbcType) throws SQLException {
		if (parameter.isEmpty()) {
			ps.setObject(i, "");
		} else {
			ps.setObject(i, JSON.toJSONString(parameter));
		}
	}

	@Override
	public JSONObject getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return create(rs.getString(columnName));
	}

	@Override
	public JSONObject getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return create(rs.getString(columnIndex));
	}

	@Override
	public JSONObject getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return create(cs.getString(columnIndex));
	}

	private JSONObject create(String s) {
		if (s == null) {
			return null;
		} else if (StringUtils.isBlank(s)) {
			return new JSONObject();
		} else {
			return JSON.parseObject(s);
		}
	}
}