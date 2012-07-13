package de.jw.strategy;

public class StrategyCallback_ {

	public static final class FinalStrategyCallback<T> implements StrategyCallback<T> {
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
