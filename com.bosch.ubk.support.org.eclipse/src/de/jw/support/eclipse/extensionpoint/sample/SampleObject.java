package de.jw.support.eclipse.extensionpoint.sample;

import com.google.common.base.Function;
import com.google.common.base.Supplier;

public class SampleObject {
	private static class GetValueFun implements Function<SampleObject, String> {

		@Override
		public String apply(SampleObject sampleObjectInstance) {
			return sampleObjectInstance.getValue();
		}

	}

	public static GetValueFun getValueFun = new GetValueFun();

	private static class GetIntegerValue implements Function<SampleObject, Integer> {

		@Override
		public Integer apply(SampleObject sampleObjectInstance) {
			return sampleObjectInstance.getIntegerValue();
		}

	}

	public static final GetIntegerValue getIntegerValueFun = new GetIntegerValue();

	private static class SetValue implements Function<String, SampleObject> {
		private final Supplier<SampleObject> sampleObjectSupplier;

		private SetValue(Supplier<SampleObject> sampleObjectSupplier) {
			this.sampleObjectSupplier = sampleObjectSupplier;
		}

		@Override
		public SampleObject apply(String newValue) {
			SampleObject sampleObject = sampleObjectSupplier.get();

			sampleObject.setValue(newValue);

			return sampleObject;
		}
	}

	public static SetValue setValueFun(Supplier<SampleObject> sampleObjectSupplier) {
		return new SetValue(sampleObjectSupplier);
	}

	private static class SetIntegerValue implements Function<Integer, SampleObject> {
		private final Supplier<SampleObject> sampleObjectSupplier;

		private SetIntegerValue(Supplier<SampleObject> sampleObjectSupplier) {
			this.sampleObjectSupplier = sampleObjectSupplier;
		}

		@Override
		public SampleObject apply(Integer newValue) {
			SampleObject sampleObject = sampleObjectSupplier.get();

			sampleObject.setIntegerValue(newValue);

			return sampleObject;
		}

	}

	public static SetIntegerValue setIntegerValueFun(Supplier<SampleObject> sampleObjectSupplier) {
		return new SetIntegerValue(sampleObjectSupplier);
	}

	private Integer integerValue;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getIntegerValue() {
		return integerValue;
	}

	public void setIntegerValue(Integer integerValue) {
		this.integerValue = integerValue;
	}
}
