package com.bosch.support.org.eclipse.core.runtime;

import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;

import com.bosch.support.com.google.common.base.Optional_;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;


/**
 * Builder to handle communication with a element of a extension point.
 * 
 * @author winter
 * 
 * @param <T>
 */
public class IConfigurationInstanceBuilder<T> implements Supplier<T> {
	public static <T> IConfigurationInstanceBuilder<T> create() {
		return new IConfigurationInstanceBuilder<T>();
	}

	private final class ExtensionInstanceData {
		Optional<IConfigurationElement> element = Optional.absent();
		Optional<T> newInstance = Optional.absent();

		@Override
		public String toString() {
			return "ExtensionInstanceData [element=" + element + ", newInstance=" + newInstance + "]";
		}

	}

	private Function<IConfigurationElement, T> newInstanceFun;
	private final Map<Function<IConfigurationElement, ?>, Function<? super Object, T>> setterMap = Maps.newLinkedHashMap();

	private final ExtensionInstanceData data = new ExtensionInstanceData();

	/**
	 * Build an instance from extension point element.<br />
	 * - first construct a element by newInstanceFun <br />
	 * - than configure instance by setterFuns <br />
	 * 
	 * @param element
	 * @return
	 */
	public T build(IConfigurationElement element) {
		Preconditions.checkNotNull(element, "IConfiguartionElement must not be null.");
		data.element = Optional.of(element);

		T newInstance = Preconditions.checkNotNull(newInstanceFun.apply(element), "Extension getter-function must not return NullPointer.");
		data.newInstance = Optional.of(newInstance);

		T settedInstance = newInstance;

		Set<Function<IConfigurationElement, ?>> getAttributeFuns = setterMap.keySet();
		for (Function<IConfigurationElement, ?> getAttributeFun : getAttributeFuns) {
			Function<? super Object, T> function = setterMap.get(getAttributeFun);

			Object attributeValue = getAttributeFun.apply(element);

			if (attributeValue instanceof Optional) {
				Optional<?> optAttributeValue = (Optional<?>) attributeValue;
				if (optAttributeValue.isPresent())
					attributeValue = optAttributeValue.get();
				else
					continue;
			}

			settedInstance = function.apply(attributeValue);
		}

		return settedInstance;
	}

	@Override
	public T get() {
		if (!data.newInstance.isPresent())
			return null;

		return data.newInstance.get();
	}

	public IConfigurationInstanceBuilder<T> newInstanceFun(Function<IConfigurationElement, T> getAttributeValue) {
		Preconditions.checkNotNull(getAttributeValue, "IConfigurationElement.createInstance*-fun must not be null.");

		this.newInstanceFun = getAttributeValue;
		return this;
	}

	public IConfigurationInstanceBuilder<T> newInstanceFun(String attribute) {
		Preconditions.checkNotNull(attribute, "Attribute to IConfigurationElement.createInstance*-fun must not be null.");

		this.newInstanceFun = IConfigurationElements.getInstance(attribute);
		return this;
	}

	/**
	 * Add a mandatory setter for T.
	 * 
	 * @param getAttributeValue
	 *          Function: f(IConfigurationElement) -> attribute
	 * @param setterFun
	 *          Function: f(Object) -> T, concrete f should be of type f(A) -> T.
	 * @return
	 */
	public IConfigurationInstanceBuilder<T> addSetter(Function<IConfigurationElement, ?> getAttributeValue, Function<Object, T> setterFun) {
		setterMap.put(getAttributeValue, setterFun);
		return this;
	}

	public IConfigurationInstanceBuilder<T> addSetter(String attribute, Function<Object, T> setterFun) {
		Function<IConfigurationElement, ?> getAttributeValue = IConfigurationElements.getAttribute(attribute);
		setterMap.put(getAttributeValue, setterFun);
		return this;
	}

	/**
	 * Add an optional setter for T.
	 * 
	 * @param getAttributeValue
	 *          Function: f(IConfigurationElement) -> Optional(attribute)
	 * @param setterFun
	 *          Function: f(Object) -> T
	 * @return this builder to continue with building.
	 */
	public IConfigurationInstanceBuilder<T> addOptionalSetter(Function<IConfigurationElement, Optional<Object>> getAttributeValue,
			Function<Object, T> setterFun) {
		setterMap.put(getAttributeValue, setterFun);
		return this;
	}

	public IConfigurationInstanceBuilder<T> addOptionalSetter(String attribute, Function<Object, T> setterFun) {
		Function<IConfigurationElement, Optional<String>> optionalAttribute = IConfigurationElements.getOptionalAttributeFun(attribute);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		Function<IConfigurationElement, Optional<Object>> uncheckedCastFun = new Optional_.CastOptionalReturnValue(optionalAttribute);

		addOptionalSetter(uncheckedCastFun, setterFun);
		return this;
	}

	public IConfigurationInstanceBuilder<T> addOptionalStringSetter(Function<IConfigurationElement, Optional<String>> getAttributeValue,
			Function<Object, T> setterFun) {
		Preconditions.checkNotNull(getAttributeValue, "IConfigurationElement.get*-fun must not be null.");
		Preconditions.checkNotNull(setterFun, "T.set*-fun must not be null.");

		setterMap.put(getAttributeValue, setterFun);
		return this;
	}

	public IConfigurationInstanceBuilder<T> addOptionalStringSetter(String attribute, Function<Object, T> setterFun) {
		Preconditions.checkNotNull(attribute, "Attribute for IConfigurationElement.get*-fun must not be null.");
		Preconditions.checkNotNull(setterFun, "T.set*-fun must not be null.");

		Function<IConfigurationElement, ?> getAttributeValueFun = IConfigurationElements.getAttribute(attribute);

		setterMap.put(getAttributeValueFun, setterFun);
		return this;
	}

	public Function<IConfigurationElement, T> asFunction() {
		return new Function<IConfigurationElement, T>() {

			@Override
			public T apply(IConfigurationElement element) {
				T build = build(element);
				return build;
			}
		};
	}
}
