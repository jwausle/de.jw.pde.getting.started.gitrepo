package com.bosch.ubk.util.iftrue_handle;

public class IfTrueCallback_ {

	public static final class FinalStrategyCallback<T> implements IfTrueCallback<T> {
		private final T finalResult;
	
		public FinalStrategyCallback(T result) {
			this.finalResult = result;
		}
	
		@Override
		public T callback() {
			return finalResult;
		}
	}

}
