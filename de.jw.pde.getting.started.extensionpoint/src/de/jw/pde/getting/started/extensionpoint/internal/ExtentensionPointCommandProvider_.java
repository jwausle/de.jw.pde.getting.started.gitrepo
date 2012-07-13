package de.jw.pde.getting.started.extensionpoint.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

import de.jw.pde.getting.started.extensionpoint.api.IGettingStarted;
import de.jw.strategy.StrategyCallback;

public class ExtentensionPointCommandProvider_ {

	public static final class LoadPropertiesBySystemPropertiesKey implements StrategyCallback<Properties> {
		private final String optionalPropertiesFileSystemKey;

		public LoadPropertiesBySystemPropertiesKey(String optionalPropertiesFileSystemKey) {
			this.optionalPropertiesFileSystemKey = optionalPropertiesFileSystemKey;
		}

		@Override
		public Properties callback() {
			String pathToPropertiesFile = System.getProperty(optionalPropertiesFileSystemKey);
			File file = new File(pathToPropertiesFile);
			Properties properties = new Properties();
			try {
				properties.load(new FileInputStream(file));
				return properties;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public static final class GetExtensionPointInstanceFun<T> implements Function<IConfigurationElement, T> {
		private final String attributeKey;

		public GetExtensionPointInstanceFun(String attributeKey) {
			this.attributeKey = Preconditions.checkNotNull(attributeKey, "AttributeKey must not be null.");
		}

		@SuppressWarnings("unchecked")
		@Override
		public T apply(IConfigurationElement input) {
			try {
				Object createExecutableExtension = input.createExecutableExtension(attributeKey);
				return (T) createExecutableExtension;
			} catch (CoreException e) {
				throw new IllegalArgumentException("ExtensionPoint attributeKey '" + attributeKey
						+ "' not found in IConfigurationElement: " + input);
			}
		}
	}

	public static final class InitGettingStartedFun implements Function<IConfigurationElement, IGettingStarted> {
		private final String extensionPointOptionalAttribute;
		private final String extenstionPointAttributeClass;

		public InitGettingStartedFun(String extenstionPointAttributeClass, String extensionPointOptionalAttribute) {
			this.extensionPointOptionalAttribute = extensionPointOptionalAttribute;
			this.extenstionPointAttributeClass = extenstionPointAttributeClass;
		}

		@Override
		public IGettingStarted apply(IConfigurationElement extensionElement) {
			IGettingStarted gettingStarted;
			try {
				gettingStarted = (IGettingStarted) extensionElement
						.createExecutableExtension(extenstionPointAttributeClass);

				Properties defaultProperties = gettingStarted.defaultProperties();

				Properties properties = ExtentensionPointCommandProviders.selectProperties(
						extensionElement.getAttribute(extensionPointOptionalAttribute), defaultProperties);

				gettingStarted.setUp(properties);
				
				return gettingStarted;
			} catch (CoreException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}

}
