package de.jw.strategy;

import java.util.LinkedList;
import java.util.List;

public class Strategy<T> {
	public static <T> Strategy<T> create() {
		return new Strategy<T>();
	}

	private final List<StrategyCallback<T>> ifTrueCallbacks = new LinkedList<StrategyCallback<T>>();
	private volatile T defaultResult = null;

	public Strategy<T> ifTrueThan(boolean isTrue, StrategyCallback<T> thanCallback) {
		if (isTrue)
			this.ifTrueCallbacks.add(thanCallback);
		return this;
	}

	public Strategy<T> ifTrueThan(boolean isTrue, final T than) {
		if (isTrue)
			ifTrueCallbacks.add(new StrategyCallback_.FinalStrategyCallback<T>(than));
		return this;
	}

	public Strategy<T> orTrueThan(boolean isTrue, T than) {
		return ifTrueThan(isTrue, than);
	}

	public Strategy<T> orTrueThan(boolean isTrue, StrategyCallback<T> thanCallback) {
		return ifTrueThan(isTrue, thanCallback);
	}

	public Strategy<T> defaultResult(T defaultResult) {
		this.defaultResult = defaultResult;
		return this;
	}

	public T get() {
		if (ifTrueCallbacks.isEmpty() && defaultResult == null)
			throw new NullPointerException("No default result setted.");
		return ifTrueCallbacks.get(0).callback();
	}

	public T getDefaultIfNoPredicateTrue() {
		try {
			T t = get();
			if (t != null)
				return t;
		} catch (Exception e) {
			System.out.println("WARN: " + e.getClass() + " = "+ e.getMessage());
		}
		
		for (StrategyCallback<T> callback : ifTrueCallbacks) {
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
