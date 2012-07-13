package com.bosch.support.com.google.common.base;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public class Optional_ {

	public static final class OptionalReturnValue<A, T> implements Function<A, Optional<T>> {
		private final Function<A, T> getAttributeValue;

		public OptionalReturnValue(Function<A, T> getAttributeValue) {
			this.getAttributeValue = getAttributeValue;
		}

		@Override
		public Optional<T> apply(A input) {
			return Optional.fromNullable(getAttributeValue.apply(input));
		}
	}

	public static final class CastOptionalReturnValue<A> implements Function<A, Optional<Object>> {
		private final Function<A, Optional<?>> optionalAttribute2;
	
		public CastOptionalReturnValue(Function<A, Optional<?>> optionalAttribute2) {
			this.optionalAttribute2 = optionalAttribute2;
		}
	
		@Override
		public Optional<Object> apply(A arg0) {
			Optional<?> optional = optionalAttribute2.apply(arg0);
			if (optional.isPresent())
				return Optional.of((Object) optional);
	
			return Optional.absent();
	
		}
	}

}
