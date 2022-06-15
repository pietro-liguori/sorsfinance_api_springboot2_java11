package com.vili.sorsfinance.api.framework;

import org.springframework.data.domain.Page;

public interface DefaultFilter<T> {

	public default Page<T> apply() {
		return null;
	}
}
