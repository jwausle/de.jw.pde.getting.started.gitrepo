package com.bosch.support.java.lang;

import com.google.common.base.Function;

public class Object_ {

	public static final class ObjectArgumentCastFun<A,T> implements Function<Object, T> {
		private final Class<A> class1;
		private final Function<A, T> setter;
	
		ObjectArgumentCastFun(Class<A> class1, Function<A, T> setter) {
			this.class1 = class1;
			this.setter = setter;
		}
	
		@Override
		public T apply(Object input) {
			A cast = class1.cast(input);
			return setter.apply(cast);
		}
	}
}
