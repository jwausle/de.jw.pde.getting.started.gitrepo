package com.bosch.ubk.util.iftrue_handle;

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Optional;

public class IfTrueHandle<T> {
	public static <T> IfTrueHandle<T> create() {
		return new IfTrueHandle<T>();
	}

	private final List<IfTrueCallback<T>> ifTrueCallbacks = new LinkedList<IfTrueCallback<T>>();
	private volatile T defaultResult = null;

	public IfTrueHandle<T> ifTrueThan(boolean isTrue, IfTrueCallback<T> thanCallback) {
		if (isTrue)
			this.ifTrueCallbacks.add(thanCallback);
		return this;
	}

	public IfTrueHandle<T> ifTrueThan(boolean isTrue, final T than) {
		if (isTrue)
			ifTrueCallbacks.add(new IfTrueCallback_.FinalStrategyCallback<T>(than));
		return this;
	}

	public IfTrueHandle<T> orTrueThan(boolean isTrue, T than) {
		return ifTrueThan(isTrue, than);
	}

	public IfTrueHandle<T> orTrueThan(boolean isTrue, IfTrueCallback<T> thanCallback) {
		return ifTrueThan(isTrue, thanCallback);
	}

	public IfTrueHandle<T> defaultResult(T defaultResult) {
		this.defaultResult = defaultResult;
		return this;
	}

	public Optional<T> get() {
		if (ifTrueCallbacks.isEmpty() && defaultResult == null)
			return Optional.absent();
		
		IfTrueCallback<T> firstCallback = ifTrueCallbacks.get(0);
		T firstCallbackResult = firstCallback.callback();
		
		return Optional.fromNullable(firstCallbackResult);
	}

	public T getDefaultIfNoPredicateTrue() {
		try {
			T t = get().get();
			if (t != null)
				return t;
		} catch (Exception e) {
			System.out.println("WARN: " + e.getClass() + " = "+ e.getMessage());
		}
		
		for (IfTrueCallback<T> callback : ifTrueCallbacks) {
			try {
				T optResult = callback.callback();
				if (optResult != null)
					return optResult;
			} catch (Exception e) {
				System.out.println("WARN: " + e.getClass() + " = " + e.getMessage());
			}
		}
		
		return defaultResult;
	}
}
