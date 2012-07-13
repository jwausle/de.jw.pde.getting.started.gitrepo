package com.bosch.support.com.google.common.base;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public class Optionals {

	public static <A, T> Function<A, Optional<T>> optionalReturnValue(Function<A, T> getAttributeValue) {
		return new Optional_.OptionalReturnValue<A, T>(getAttributeValue);
	}

	public static <A> Function<A, Optional<Object>> toOptionalObjectFun(Function<A, Optional<?>> optionalAttribute) {
		return new Optional_.CastOptionalReturnValue<A>(optionalAttribute);
	}

}
