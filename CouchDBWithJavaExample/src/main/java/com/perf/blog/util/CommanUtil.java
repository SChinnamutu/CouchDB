package com.perf.blog.util;

import java.util.UUID;

public class CommanUtil {

	public static String generateUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
