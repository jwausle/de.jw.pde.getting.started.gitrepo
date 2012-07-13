package de.jw.pde.getting.started.extensionpoint.internal;

import java.util.Properties;

import de.jw.strategy.Strategy;

public class ExtentensionPointCommandProviders {

	public static Properties selectProperties(final String optionalPropertiesFileSystemKey, Properties defaultProperties) {
		Properties properties = Strategy
				.<Properties> create()
				/**/.defaultResult(defaultProperties)
				/**/.ifTrueThan(optionalPropertiesFileSystemKey == null, defaultProperties)
				/**/.ifTrueThan(System.getProperty(optionalPropertiesFileSystemKey) != null,
						new ExtentensionPointCommandProvider_.LoadPropertiesBySystemPropertiesKey(optionalPropertiesFileSystemKey))
				.getDefaultIfNoPredicateTrue();
		return properties;
	}

}
