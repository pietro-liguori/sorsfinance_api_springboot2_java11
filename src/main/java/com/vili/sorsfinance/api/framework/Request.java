package com.vili.sorsfinance.api.framework;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.vili.sorsfinance.api.exceptions.InvalidRequestParamException;

public class Request {

	public static final int DEFAULT_PAGE = 0;
	public static final int DEFAULT_SIZE = 10;
	public static final String[] DEFAULT_ORDER_BY = { "id" };
	public static final Direction DEFAULT_DIRECTION = Direction.ASC;
	public static final List<String> PAGE_REQUEST_PARAMS = List.of("page", "size", "direction", "orderBy");
	public static final List<String> INTEGER_PARAM_NAMES = List.of("type", "status", "page", "size", "profile",
			"preferredContact");
	public static final List<String> STRING_PARAM_NAMES = List.of("direction", "orderBy", "fields", "date", "dateStart",
			"dateEnd");
	public static final List<String> LONG_PARAM_NAMES = List.of("holder", "owner", "bank", "account", "card",
			"responsible", "recipient", "city", "state", "country", "category", "transaction", "branch");
	public static final List<String> BOOLEAN_PARAM_NAMES = List.of("preferred", "active");

	private Map<String, ?> filter;
	private Pageable pageRequest;
	private Set<String> fields;
	private String method;
	private String endPoint;
	
	private Request() {
	}

	private Request(HttpServletRequest request) {
		this.filter = setFilter(request);
		this.pageRequest = setPageRequest(request);
		this.fields = setFields(request);
		this.method = request.getMethod();
		this.endPoint = request.getServletPath().split("/")[1];
	}

	private Request(String method, String endPoint, Map<String, ?> filter, Pageable pageRequest, Set<String> fields) {
		this.filter = filter;
		this.pageRequest = pageRequest;
		this.fields = fields;
		this.method = method;
		this.endPoint = endPoint;
	}

	public Map<String, ?> getFilter() {
		return filter;
	}

	public Pageable getPageRequest() {
		return pageRequest;
	}

	public void setPageRequest(Pageable pageRequest) {
		this.pageRequest = pageRequest;
	}

	public Set<String> getFields() {
		return fields;
	}

	public String getMethod() {
		return method;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public static Request from(HttpServletRequest request) {
		return new Request(request);
	}

	public static Request from(String method, String endPoint, Map<String, ?> filter, Pageable pageRequest, Set<String> fields) {
		return new Request(method, endPoint,filter, pageRequest, fields);
	}

	private static <T extends Object> Map<String, T> setFilter(HttpServletRequest request) {
		if (request == null)
			return null;
		
		Map<String, T> parameterMap = new HashMap<>();

		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			String key = entry.getKey();
			if (!PAGE_REQUEST_PARAMS.contains(key) && !key.equals("fields")) {
				String paramValue = entry.getValue()[0];

				if (paramValue.isBlank()) {
					throw new InvalidRequestParamException("Request parameter '" + key
							+ "' must not be blank. Ommit parameter from path or provide a valid value");
				}

				T value = getValueOf(key, paramValue, getParamDataType(key));
				
				if (value != null) {
					parameterMap.putIfAbsent(key, value);
				}
			}
		}

		return parameterMap.isEmpty() ? null : (Map<String, T>) parameterMap;
	}

	private static <T extends Object> Set<String> setFields(HttpServletRequest request) {
		if (request == null)
			return null;

		Set<String> fieldList = new HashSet<>();
		Map<String, T> parameterMap = new HashMap<>();

		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			String key = entry.getKey();

			if (key.equals("fields")) {
				String paramValue = entry.getValue()[0];

				if (paramValue.isBlank()) {
					throw new InvalidRequestParamException("Request parameter '" + key
							+ "' must not be blank. Ommit parameter from path or provide a valid value");
				}
				
				T value = getValueOf(key, paramValue, getParamDataType(key));
				
				if (value != null)
					parameterMap.putIfAbsent(key, value);
			}
		}

		if (parameterMap.get("fields") != null) {
			fieldList = Set.copyOf(Arrays.asList(((String) parameterMap.get("fields")).split(",")));
		}
		return fieldList.isEmpty() ? null : fieldList;
	}

	private static <T extends Object> Pageable setPageRequest(HttpServletRequest request) {
		if (request == null)
			return null;
		
		Map<String, T> parameterMap = new HashMap<>();

		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			String key = entry.getKey();

			if (PAGE_REQUEST_PARAMS.contains(key)) {
				String paramValue = entry.getValue()[0];

				if (paramValue.isBlank()) {
					throw new InvalidRequestParamException("Request parameter '" + key
							+ "' must not be blank. Ommit parameter from path or provide a valid value");
				}
				
				T value = getValueOf(key, paramValue, getParamDataType(key));
				
				if (value != null)
					parameterMap.putIfAbsent(key, value);
			}
		}

		int page = parameterMap.get("page") == null ? DEFAULT_PAGE : (int) parameterMap.get("page");
		int size = parameterMap.get("size") == null ? DEFAULT_SIZE : (int) parameterMap.get("size");
		String[] orderBy = parameterMap.get("orderBy") == null ? DEFAULT_ORDER_BY
				: ((String) parameterMap.get("orderBy")).split(",");
		Direction direction = parameterMap.get("direction") == null ? DEFAULT_DIRECTION
				: Direction.valueOf(((String) parameterMap.get("direction")).toUpperCase());

		return PageRequest.of(page, size, direction, orderBy);
	}

	@SuppressWarnings("unchecked")
	private static <T extends Object> T getValueOf(String parameter, String value, Class<?> dataType) {
		if (dataType == null)
			return null;

		try {
			if (dataType.equals(Boolean.class)) {
				Boolean ret = Boolean.parseBoolean(value);
				return (T) ret;
			}

			if (dataType.equals(Long.class)) {
				Long ret = Long.parseLong(value);
				return (T) ret;
			}
			
			if (dataType.equals(Integer.class)) {
				Integer ret = Integer.parseInt(value);
				return (T) ret;
			}
			
			if (dataType.equals(String.class)) {
				String ret = value;
				return (T) ret;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new InvalidRequestParamException("Cannot parse to " + dataType.getSimpleName() + ": " + parameter + "=" + value);
		}

		return null;
	}

	private static Class<?> getParamDataType(String param) {
		if (INTEGER_PARAM_NAMES.contains(param)) {
			return Integer.class;
		} else if (STRING_PARAM_NAMES.contains(param)) {
			return String.class;
		} else if (LONG_PARAM_NAMES.contains(param)) {
			return Long.class;
		} else if (BOOLEAN_PARAM_NAMES.contains(param)) {
			return Boolean.class;
		} else {
			return null;
		}
	}

}
