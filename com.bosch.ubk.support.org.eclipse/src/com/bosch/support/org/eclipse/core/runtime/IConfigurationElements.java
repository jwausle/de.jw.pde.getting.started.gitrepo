package com.bosch.support.org.eclipse.core.runtime;

import org.eclipse.core.runtime.IConfigurationElement;

import com.bosch.support.com.google.common.base.Optionals;
import com.bosch.support.org.eclipse.core.runtime.IConfigurationElement_.GetAttributeType;
import com.bosch.support.org.eclipse.core.runtime.IConfigurationElement_.GetAttributeValue;
import com.google.common.base.Function;
import com.google.common.base.Optional;


public class IConfigurationElements {

	public static <T> GetAttributeValue<T> getInstance(String classAttributeName) {
		GetAttributeType attributeType = GetAttributeType.CLASS;

		final GetAttributeValue<T> getAttributeValue = createGetAttributeValue(classAttributeName, attributeType);

		return getAttributeValue;
	}

	public static Function<IConfigurationElement, String> getAttribute(String attributeName) {
		final GetAttributeValue<String> getAttributeValue = createGetAttributeValue(attributeName, GetAttributeType.STRING);
		
		return getAttributeValue;
	}
	
	public static Function<IConfigurationElement, Optional<String>> getOptionalAttributeFun(String attributeName) {
		final GetAttributeValue<String> getAttributeValue = createGetAttributeValue(attributeName, GetAttributeType.STRING);
		return Optionals.<IConfigurationElement, String>optionalReturnValue(getAttributeValue);
	}
	
	private static <T> GetAttributeValue<T> createGetAttributeValue(String classAttributeName, GetAttributeType attributeType) {
		final GetAttributeValue<T> getAttributeValue = new IConfigurationElement_.GetAttributeValue<T>(classAttributeName,
				attributeType);
		return getAttributeValue;
	}


}
