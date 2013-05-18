package com.lishijian.hackathon2013.deliver;

import java.util.Map;
import java.util.TreeMap;

public class Configure {

	private static final Map<String, Object> config = new TreeMap<String, Object>();
	
	public static void setUserId(int id) {
		config.put("user.id", id);
	}
	
	public static int getUserId() {
		return (Integer)config.get("user.id");
	}
	
}
