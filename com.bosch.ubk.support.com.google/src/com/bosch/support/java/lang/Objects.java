package com.bosch.support.java.lang;

import com.google.common.base.Function;

public class Objects {
	public static <T, R> Function<Object, R> castArgFun(Class<T> class1, final Function<T, R> toCastFun) {
		return new Object_.ObjectArgumentCastFun<T, R>(class1, toCastFun);
	}

	public static <R> Function<Object, R> castStringArgFun(final Function<String, R> toCastFun) {
		return Objects.<String, R> castArgFun(String.class, toCastFun);
	}
	
	public static <R> Function<Object, R> castIntegerArgFun(final Function<Integer, R> toCastFun) {
		return Objects.<Integer, R> castArgFun(Integer.class, toCastFun);
	}
}
