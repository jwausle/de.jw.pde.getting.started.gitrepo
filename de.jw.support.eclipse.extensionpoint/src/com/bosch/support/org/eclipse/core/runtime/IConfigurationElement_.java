package com.bosch.support.org.eclipse.core.runtime;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

public class IConfigurationElement_ {
	public static enum GetAttributeType{
		CLASS,STRING;
	}
	public static final class GetAttributeValue<T> implements Function<IConfigurationElement, T> {
		private final GetAttributeType attributeType;
		private final String attributeName;
	
		public GetAttributeValue(String attributeName) {
			this.attributeName = attributeName;
			this.attributeType = GetAttributeType.STRING;
		}
		public GetAttributeValue(String attributeName,GetAttributeType attributeType) {
			this.attributeName = attributeName;
			this.attributeType = attributeType;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public T apply(IConfigurationElement iConfigurationElement) {
			Preconditions.checkNotNull(iConfigurationElement);
			switch (attributeType) {
			case STRING:
				return (T) iConfigurationElement.getAttribute(attributeName);
			case CLASS:
				try {
					return (T) iConfigurationElement.createExecutableExtension(attributeName);
				} catch (CoreException e) {
					throw new RuntimeException(e.getMessage(),e);
				}
			default:
				throw new IllegalArgumentException("Unsupported GetAttributeType : " + attributeType);
			}
		}
	}

}
