package com.vili.sorsfinance.framework;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.servlet.HandlerMapping;

import com.vili.sorsfinance.framework.annotations.FilterSetting;
import com.vili.sorsfinance.framework.enums.QueryOperator;
import com.vili.sorsfinance.framework.exceptions.InvalidRequestParamException;
import com.vili.sorsfinance.framework.interfaces.IEntity;
import com.vili.sorsfinance.framework.utils.CustomCaster;

public class Request {

	public static final int DEFAULT_PAGE = 0;
	public static final int DEFAULT_SIZE = 10;
	public static final String[] DEFAULT_ORDER_BY = { "id" };
	public static final Direction DEFAULT_DIRECTION = Direction.ASC;

	public static final String PAGE_ATTRIBUTE = "page";
	public static final String DIRECTION_PARAM = "direction";
	public static final String ORDER_BY_PARAM = "orderBy";
	public static final String SIZE_PARAM = "size";
	public static final String FIELDS_PARAM = "fields";

	public static final Map<String, Class<?>> PAGE_REQUEST_PARAMS = Map.of(DIRECTION_PARAM, String.class,
			ORDER_BY_PARAM, String.class, SIZE_PARAM, Integer.class);

	private HttpServletRequest payload;
	private Pageable pageRequest;
	private Set<String> fields = new HashSet<>();
	private Class<?> entityClass;
	private List<Filter> filters = new ArrayList<>();

	public Request() {
		
	}

	public Request(HttpServletRequest request, Class<?> entityClass) {
		this.entityClass = entityClass;
		setPayload(request);;
		setPageRequest();
		setFields();
		setFilters();
	}

	public Request(Pageable pageRequest) {
		this.pageRequest = pageRequest;
	}

	public HttpServletRequest getPayload() {
		return payload;
	}

	public void setPayload(HttpServletRequest payload) {
		this.payload = payload;
	}

	public Pageable getPageRequest() {
		return pageRequest;
	}

	@SuppressWarnings("unchecked")
	private void setPageRequest() {
		if (getPayload() == null)
			return;

		int page = DEFAULT_PAGE;
		Map<String, String> pathVariables = (Map<String, String>) getPayload()
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		for (Entry<String, String> entry : pathVariables.entrySet()) {
			String key = entry.getKey();

			if (key.equals(PAGE_ATTRIBUTE)) {
				try {
					page = Integer.parseInt(entry.getValue()) - 1;
				} catch (NumberFormatException e) {
					throw new InvalidRequestParamException("Path variable '" + key + "' must be a whole number");
				}
			}
		}

		Map<String, Object> parameterMap = new HashMap<>();

		for (Entry<String, String[]> entry : getPayload().getParameterMap().entrySet()) {
			String key = entry.getKey();

			if (PAGE_REQUEST_PARAMS.keySet().contains(key)) {
				String paramValue = entry.getValue()[0];

				if (paramValue.isBlank()) {
					throw new InvalidRequestParamException("Request parameter '" + key
							+ "' must not be blank. Ommit parameter from path or provide a valid value");
				}

				Object value = CustomCaster.castTo(PAGE_REQUEST_PARAMS.get(key), paramValue);

				if (value != null)
					parameterMap.putIfAbsent(key, value);
			}
		}

		int size = parameterMap.get(SIZE_PARAM) == null ? DEFAULT_SIZE : (int) parameterMap.get(SIZE_PARAM);
		String[] orderBy = parameterMap.get(ORDER_BY_PARAM) == null ? DEFAULT_ORDER_BY
				: ((String) parameterMap.get(ORDER_BY_PARAM)).split(",");
		Direction direction = parameterMap.get(DIRECTION_PARAM) == null ? DEFAULT_DIRECTION
				: Direction.valueOf(((String) parameterMap.get(DIRECTION_PARAM)).toUpperCase());

		pageRequest = PageRequest.of(page, size, direction, orderBy);
	}

	public Set<String> getFields() {
		return fields;
	}

	private void setFields() {
		if (getPayload() == null)
			return;

		Map<String, Object> parameterMap = new HashMap<>();

		for (Entry<String, String[]> entry : getPayload().getParameterMap().entrySet()) {
			String key = entry.getKey();

			if (key.equals(FIELDS_PARAM)) {
				String paramValue = entry.getValue()[0];

				if (paramValue.isBlank()) {
					throw new InvalidRequestParamException("Request parameter '" + key
							+ "' must not be blank. Ommit parameter from path or provide a valid value");
				}

				Object value = CustomCaster.castTo(String.class, paramValue);

				if (value != null)
					parameterMap.putIfAbsent(key, value);
			}
		}

		if (parameterMap.get(FIELDS_PARAM) != null) {
			fields = Set.copyOf(Arrays.asList(((String) parameterMap.get(FIELDS_PARAM)).split(",")));
		}
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
	
	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters() {
		for (Field field : getEntityClass().getDeclaredFields()) {
			FilterSetting[] filterSettings = field.getDeclaredAnnotationsByType(FilterSetting.class);
			
			if (filterSettings.length > 0) {
				for (FilterSetting fs : filterSettings) {
					String alias = fs.alias().isBlank() ? field.getName() : fs.alias();
					buildFilter(field, alias, fs.nesting());
				}
			} else {
				buildFilter(field, field.getName(), new String[0]);
			}
		}
	}
	
	public <T extends IEntity> Specification<T> getSpecification() {
		return SpecificationBuilder.with(filters).build();
	}

	@SuppressWarnings("unchecked")
	private void buildFilter(Field field, String alias, String[] nesting) {
		String fieldName = field.getName();
		Class<?> fieldType = resolveFieldType(field);
		
		for (Entry<String, String[]> parameter : getPayload().getParameterMap().entrySet()) {
			String key = parameter.getKey();
			List<String> params = Arrays.asList(parameter.getValue());

			if (alias.equals(key)) {
				for (String param : params) {
					if (param.isBlank()) {
						throw new InvalidRequestParamException("Request parameter '" + key + "' must not be blank. Inform a valid value or remove from request");
					}
					
					QueryOperator qo = QueryOperator.from(param);
					
					for (int i = 0; i < nesting.length; i++) {
						fieldName += "." + nesting[i];
						
						if (i == nesting.length - 1) {
							List<Field> depFields = Arrays.asList(fieldType.getDeclaredFields());
							boolean typeChanged = false;
							
							while (!typeChanged) {
								for (Field depField : depFields) {
									if (depField.getName().equals(nesting[i])) {
										fieldType = resolveFieldType(depField);
										typeChanged = true;
										break;
									}
								}
								
								if (fieldType.getSuperclass().equals(Object.class))
									break;
								
								depFields = Arrays.asList(fieldType.getSuperclass().getDeclaredFields());
							}
						}
					}
					Filter filter = Filter.builder().fieldName(fieldName).fieldType(fieldType).operator(qo);

					if (qo.equals(QueryOperator.IN) || qo.equals(QueryOperator.BETWEEN)) {
						List<String> values = (List<String>) QueryOperator.treat(param);
						filter = filter.values(values);
					} else {
						String value = (String) QueryOperator.treat(param);
						filter = filter.value(value);
					}
					
					getFilters().add(filter);
				}
			}
		}
	}
	
	private Class<?> resolveFieldType(Field field) {
		Class<?> fieldType = field.getType();
		
		if (Collection.class.isAssignableFrom(fieldType)) {
			fieldType = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
			
			if (!IEntity.class.isAssignableFrom(fieldType)) {
				fieldType = field.getType();
			}
		}
		
		return fieldType;
	}

	public static Pageable paging() {
		return PageRequest.of(DEFAULT_PAGE, DEFAULT_SIZE, DEFAULT_DIRECTION, DEFAULT_ORDER_BY);
	}
}
