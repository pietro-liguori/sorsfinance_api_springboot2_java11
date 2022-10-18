package com.vili.sorsfinance.framework.validation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.validation.ConstraintValidatorContext;

import org.springframework.data.domain.Example;

import com.vili.sorsfinance.framework.IEntity;
import com.vili.sorsfinance.framework.exceptions.FieldMessage;
import com.vili.sorsfinance.framework.exceptions.custom.EnumValueNotFoundException;
import com.vili.sorsfinance.framework.repositories.IRepository;
import com.vili.sorsfinance.framework.utils.RepositoryProvider;

public interface IValidator {

	List<FieldMessage> getErrors();

	default void addError(FieldMessage error) {
		getErrors().add(error);
	}
	
	default boolean after(String field, Date value, Date min, boolean required) {
		if (value == null) {
			if (required) {
				addError(new FieldMessage(field, "Must not be null"));
				return false;
			}

			return true;
		} else {
			if (value.after(min)) {
				addError(new FieldMessage(field, "Must be after " + min));
				return false;
			}
		}

		return true;
	}

	default boolean before(String field, Date value, Date max, boolean required) {
		if (value == null) {
			if (required) {
				addError(new FieldMessage(field, "Must not be null"));
				return false;
			}

			return true;
		} else {
			if (value.before(max)) {
				addError(new FieldMessage(field, "Must be before " + max));
				return false;
			}
		}

		return true;
	}

	default boolean future(String field, Date value, boolean required) {
		if (value == null) {
			if (required) {
				addError(new FieldMessage(field, "Must not be null"));
				return false;
			}

			return true;
		} else {
			if (value.before(new Date())) {
				addError(new FieldMessage(field, "Must be future date"));
				return false;
			}
		}

		return true;
	}

	default IEntity entityId(String field, Long id, Class<? extends IEntity> domain, boolean required) {
		IRepository<IEntity> repo = RepositoryProvider.get(domain);
		
		if (id == null) {
			if (required)
				addError(new FieldMessage(field, "Must not be null"));

			return null;
		} else {
			Optional<IEntity> aux = repo.findById(id);

			if (aux.isEmpty()) {
				addError(new FieldMessage(field, "Resource not found: [" + domain.getSimpleName() + "(id=" + id + ")]"));
				return null;
			} else
				return aux.get();
		}
	}

	default List<IEntity> entityIds(String field, List<Long> ids, Class<? extends IEntity> domain, boolean required) {
		IRepository<IEntity> repo = RepositoryProvider.get(domain);
		
		if (ids == null) {
			if (required)
				addError(new FieldMessage(field, "Must not be null"));

			return null;
		} else {
			if (ids.size() == 0) {
				addError(new FieldMessage(field, "Must not be empty"));
				return null;
			} else {
				List<IEntity> entities = new ArrayList<>();
				String msg = "Resource(s) not found: [" + domain.getSimpleName() + "(ids={";
				boolean isError = false;
				
				for (Long id : ids) {
					Optional<IEntity> aux = repo.findById(id);
					
					if (aux.isEmpty()) {
						isError = true;
						msg += id + ", ";
					} else {
						entities.add(aux.get());
					}
				}
				
				if (isError) {
					msg = msg.substring(0, msg.lastIndexOf(", ")) + "})]";
					addError(new FieldMessage(field, msg));
					return null;
				} else
					return entities;
			}
		}
	}

	default boolean enumValue(String field, Integer value, Class<?> target, boolean required) {
		if (value == null) {
			if (required) {
				addError(new FieldMessage(field, "Must not be null"));
				return false;
			}

			return true;
		}

		return enumValueTest(value, target, field);
	}
	
	default boolean length(String field, String value, int min, int max, boolean required) {
		if (value == null) {
			if (required) {
				addError(new FieldMessage(field, "Must not be null"));
				return false;
			}

			return true;
		} else {
			if (value.length() < min && value.length() > max) {
				addError(new FieldMessage(field, "Must be between " + min + " and " + max + " characters"));
				 return false;
			}
		}

		return true;
	}
	
	default boolean max(String field, Number value, Number max, boolean required) {
		if (value == null) {
			if (required) {
				addError(new FieldMessage(field, "Must not be null"));
				return false;
			}

			return true;
		} else {
			if (value.getClass().equals(max.getClass())) {
				addError(new FieldMessage(field, "Method parameter (max) must be of same class of method parameter (value)"));
				return false;
			} else {
				if (value.getClass().isAssignableFrom(Integer.class)) {
					if (((Integer) value) >= ((Integer) max)) {
						addError(new FieldMessage(field, "Must be less than or equal " + max));
						return false;
					}
				}
				
				if (value.getClass().isAssignableFrom(Long.class)) {
					if (((Long) value) >= ((Long) max)) {
						addError(new FieldMessage(field, "Must be less than or equal " + max));
						return false;
					}
				}
	
				if (value.getClass().isAssignableFrom(Double.class)) {
					if (((Double) value) >= ((Double) max)) {
						addError(new FieldMessage(field, "Must be less than or equal " + max));
						return false;
					}
				}
			}
		}

		return true;
	}

	default boolean min(String field, Number value, Number min, boolean required) {
		if (value == null) {
			if (required) {
				addError(new FieldMessage(field, "Must not be null"));
				return false;
			}

			return true;
		} else {
			if (value.getClass().equals(min.getClass())) {
				addError(new FieldMessage(field, "Method parameter (min) must be of same class of method parameter (value)"));
				return false;
			} else {
				if (value.getClass().isAssignableFrom(Integer.class)) {
					if (((Integer) value) <= ((Integer) min)) {
						addError(new FieldMessage(field, "Must be greater than or equal " + min));
						return false;
					}
				}
				
				if (value.getClass().isAssignableFrom(Long.class)) {
					if (((Long) value) <= ((Long) min)) {
						addError(new FieldMessage(field, "Must be greater than or equal " + min));
						return false;
					}
				}
	
				if (value.getClass().isAssignableFrom(Double.class)) {
					if (((Double) value) <= ((Double) min)) {
						addError(new FieldMessage(field, "Must be greater than or equal " + min));
						return false;
					}
				}
			}
		}

		return true;
	}

	default boolean notEmpty(String field, Object value, boolean required) {
		if (value == null) {
			if (required) {
				addError(new FieldMessage(field, "Must not be null"));
				return false;
			}

			return true;
		} else {
			if (Collection.class.isAssignableFrom(value.getClass())) {
				if (((Collection<?>) value).isEmpty()) {
					addError(new FieldMessage(field, "Must not be empty"));
					 return false;
				}
			} else {
				if (value.toString().isBlank()) {
					addError(new FieldMessage(field, "Must not be empty"));
					 return false;
				}	
			}
			
		}

		return true;
	}
	
	default boolean positive(String field, Number value, boolean required) {
		if (value == null) {
			if (required) {
				addError(new FieldMessage(field, "Must not be null"));
				return false;
			}

			return true;
		} else {
			if (value.getClass().isAssignableFrom(Integer.class)) {
				if (((Integer) value) <= 0) {
					addError(new FieldMessage(field, "Must be positive"));
					return false;
				}
			}
			
			if (value.getClass().isAssignableFrom(Long.class)) {
				if (((Long) value) <= 0) {
					addError(new FieldMessage(field, "Must be positive"));
					return false;
				}
			}

			if (value.getClass().isAssignableFrom(Double.class)) {
				if (((Double) value) <= 0) {
					addError(new FieldMessage(field, "Must be positive"));
					return false;
				}
			}
		}

		return true;
	}

	default boolean positiveOrZero(String field, Object value, boolean required) {
		if (value == null) {
			if (required) {
				addError(new FieldMessage(field, "Must not be null"));
				return false;
			}

			return true;
		} else {
			if (value.getClass().isAssignableFrom(Integer.class)) {
				if (((Integer) value) < 0) {
					addError(new FieldMessage(field, "Must be positive or zero"));
					return false;
				}
			}
			
			if (value.getClass().isAssignableFrom(Long.class)) {
				if (((Long) value) < 0) {
					addError(new FieldMessage(field, "Must be positive or zero"));
					return false;
				}
			}

			if (value.getClass().isAssignableFrom(Double.class)) {
				if (((Double) value) < 0) {
					addError(new FieldMessage(field, "Must be positive or zero"));
					return false;
				}
			}
		}

		return true;
	}

	default boolean range(String field, Object value, Object min, Object max, boolean required) {
		if (value == null) {
			if (required) {
				addError(new FieldMessage(field, "Must not be null"));
				return false;
			}

			return true;
		} else {
			if (value.getClass().equals(min.getClass()) && value.getClass().equals(max.getClass())) {
				addError(new FieldMessage(field, "Method parameters (min, max) must be of same class of method parameter (value)"));
				return false;
			} else {
				if (value.getClass().isAssignableFrom(Date.class)) {
					if (((Date) value).before(((Date) min))  && ((Date) value).after(((Date) max))) {
						addError(new FieldMessage(field, "Must be between " + min + " and " + max));
						return false;
					}
				}

				if (value.getClass().isAssignableFrom(Integer.class)) {
					if (((Integer) value) < ((Integer) min) && ((Integer) value) > ((Integer) max)) {
						addError(new FieldMessage(field, "Must be between " + min + " and " + max));
						return false;
					}
				}
				
				if (value.getClass().isAssignableFrom(Long.class)) {
					if (((Long) value) < ((Long) min) && ((Long) value) > ((Long) max)) {
						addError(new FieldMessage(field, "Must be between " + min + " and " + max));
						return false;
					}
				}
	
				if (value.getClass().isAssignableFrom(Double.class)) {
					if (((Double) value) < ((Double) min) && ((Double) value) > ((Double) max)) {
						addError(new FieldMessage(field, "Must be between " + min + " and " + max));
						return false;
					}
				}
			}
		}

		return true;
	}
	
	default boolean size(String field, Object value, int size, boolean required) {
		if (value == null) {
			if (required) {
				addError(new FieldMessage(field, "Must not be null"));
				return false;
			}

			return true;
		} else {
			if (Collection.class.isAssignableFrom(value.getClass())) {
				if (((Collection<?>) value).size() != size) {
					addError(new FieldMessage(field, "Must have " + size + " element(s)"));
					return false;
				}
			} else {
				if (value.toString().length() != size) {
				addError(new FieldMessage(field, "Must have " + size + " character(s)"));
				 return false;
				}
			}
		}

		return true;
	}

	default boolean unique(String field, Example<IEntity> example, boolean required) {
		Class<?> probeType = example.getProbeType();
		IRepository<IEntity> repo = RepositoryProvider.get(probeType);
		String method = "get" + field.substring(0, 1).toUpperCase() + field.substring(1, field.length());
		Object value = null;
		
		try {
			value = probeType.getDeclaredMethod(method).invoke(example.getProbe());

			if (value == null) {
				if (required) {
					addError(new FieldMessage(field, "Must not be null"));
					return false;
				}
 
				return true;
			} else {
				if (!repo.findAll(example).isEmpty()) {
					Map<String, Object> fields = mapNonNullOrEmptyValues(example.getProbe());
					String msg = "[";
					
					for (Entry<String, Object> entry : fields.entrySet()) {
						msg += entry.getKey() + "=" + entry.getValue().toString() + ", ";
					}

					msg = msg.substring(0, msg.lastIndexOf(", ")) + "]";
					addError(new FieldMessage(field, "Entity of class '" + probeType.getSimpleName() + "' with field(s) " + msg + " already exists"));
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			addError(new FieldMessage(field, e.getMessage()));
			return false;
		}

		return true;
	}
	
	default boolean validate(ConstraintValidatorContext context) {
		for (FieldMessage e : getErrors()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField()).addConstraintViolation();
		}
		
		return getErrors().isEmpty();
	}
	
	private boolean enumValueTest(Integer code, Class<?> target, String field) {
		try {
			target.getDeclaredMethod("toEnum", Integer.class).invoke(target, code);
		} catch (Exception e) {
			if (e.getCause().getClass().equals(EnumValueNotFoundException.class))
				addError(new FieldMessage(field, e.getCause().getMessage()));
			else
				addError(new FieldMessage(field, "Method 'public static " + target.getSimpleName() + " toEnum(Integer code)' not implemented on class '" + target.getSimpleName() + "'"));
			
			return false;
		}
		
		return true;
	}
	
	private Map<String, Object> mapNonNullOrEmptyValues(IEntity probe) {
		Map<String, Object> map = new HashMap<>();
		
		for (Method method : probe.getClass().getDeclaredMethods()) {
			if (method.getName().startsWith("get")) {
				String field = method.getName().substring(3);
				String key = field.substring(0, 1).toLowerCase() + field.substring(1, field.length());
				try {
					Object value = method.invoke(probe);
					
					if (value != null) {
						if (IEntity.class.isAssignableFrom(value.getClass())) {
							key = key + ".id";
							value = ((IEntity) value).getId();
						} else if (Collection.class.isAssignableFrom(value.getClass())) {
							if (((Collection<?>) value).isEmpty())
								continue;
						}
						
						map.put(key, value);
					}
				} catch (Exception e) {
					addError(new FieldMessage(key, e.getMessage()));
				}
			}
		}
		
		return map;
	}
}
