package com.qf.common.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

/**
 * 获取结果集
 * @author yup
 * 2019年6月5日
 */
public class Result {
	
	private Map<String, Object> map;
	
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public Map<String, Object> getMap() {
		return this.map;
	}
	
	public int getInt(String column) {
		column = column.toUpperCase();
		if(map.get(column) == null) {
			return 0;
		}
		return Integer.parseInt(map.get(column).toString());
	}
	
	public double getDouble(String column) {
		column = column.toUpperCase();
		if(map.get(column) == null) {
			return 0;
		}
		return Double.parseDouble(map.get(column).toString());
	}
	
	public Date getDate(String column) {
		column = column.toUpperCase();
		if(map.get(column) == null) {
			return null;
		}else if(map.get(column).getClass() == Date.class) {
			return (Date)map.get(column);
		}
		return new Date(((Timestamp)map.get(column)).getTime());
	}
	
	public Timestamp getTimestamp(String column) {
		column = column.toUpperCase();
		if(map.get(column) == null) {
			return null;
		}
		return (Timestamp)map.get(column);
	}
	
	public String getString(String column) {
		column = column.toUpperCase();
		if(map.get(column) == null) {
			return null;
		}
		return map.get(column).toString();
	}
}
