package com.vili.sorsfinance.framework.enums;

import java.util.Arrays;
import java.util.regex.Pattern;

import com.vili.sorsfinance.framework.exceptions.EnumValueNotFoundException;

public enum QueryOperator {

	EQUALS(1, null),
	NOT_EQUALS(2, Pattern.compile("^!")),
	GREATER_THAN(3, Pattern.compile("^>[^=]")),
	LESS_THAN(4, Pattern.compile("^<[^=]")),
	GREATER_OR_EQUAL(5, Pattern.compile("^>=")),
	LESS_OR_EQUAL(6, Pattern.compile("^<=")),
	BETWEEN(7, Pattern.compile("(^\\d{4}-\\d{2}-\\d{2}:\\d{4}-\\d{2}-\\d{2}$)|(^\\d+(.\\d+)?:\\d(.\\d+)?+$)")),
	LIKE(8, Pattern.compile("^\\*\\w+\\*$")),
	IN(9, Pattern.compile("^_[^,]+(,[^,]+)+_$"));
	
	private int code;
	private Pattern pattern;
	
	private QueryOperator(int code, Pattern pattern) {
		this.code = code;
		this.pattern = pattern;
	}
	
	public int getCode() {
		return code;
	}
	
	public Pattern getPattern() {
		return pattern;
	}
	
	public static QueryOperator toEnum(int code) {
		for (QueryOperator x : QueryOperator.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", QueryOperator.class);
	}
	
	public static QueryOperator from(String value) {
		for (QueryOperator x : QueryOperator.values()) {
			if (x.getPattern() == null)
				continue;
			
			if (x.getPattern().matcher(value).find())
				return x;
		}
		
		return EQUALS;
	}
	
	public static Object treat(String value) {
		if (NOT_EQUALS.getPattern().matcher(value).find() || GREATER_THAN.getPattern().matcher(value).find() || LESS_THAN.getPattern().matcher(value).find())
			return value.substring(1);

		if (GREATER_OR_EQUAL.getPattern().matcher(value).find() || LESS_OR_EQUAL.getPattern().matcher(value).find())
			return value.substring(2);

		if (IN.getPattern().matcher(value).find())
			return Arrays.asList(value.substring(1, value.length() - 1).split(","));

		if (LIKE.getPattern().matcher(value).find())
			return value.substring(1, value.length() - 1);
		
		if (BETWEEN.getPattern().matcher(value).find()) {
			return Arrays.asList(value.split(":")).subList(0, 2);
		}

		return value;
	}
}
