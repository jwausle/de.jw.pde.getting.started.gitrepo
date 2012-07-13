package de.jw.pde.getting.started.extensionpoint.internal;

import java.util.Properties;

import com.bosch.ubk.util.iftrue_handle.IfTrueHandle;
import com.bosch.ubk.util.iftrue_handle.IfTrueCallback;


public class ExtentensionPointCommandProviders {

	public static Properties tryLoadPropertiesOrDefault(final String optionalPropertiesFileSystemKey, Properties defaultProperties) {
		ExtentensionPointCommandProvider_.LoadPropertiesBySystemPropertiesKey loadPropertiesCallback = new ExtentensionPointCommandProvider_.LoadPropertiesBySystemPropertiesKey(
				optionalPropertiesFileSystemKey);

		return tryLoadPropertiesOrDefault(optionalPropertiesFileSystemKey, defaultProperties, loadPropertiesCallback);
	}

	public static Properties tryLoadPropertiesOrDefault(final String optionalPropertiesFileSystemKey, Properties defaultProperties,
			IfTrueCallback<Properties> loadPropertiesCallback) {

		Properties properties = IfTrueHandle.<Properties> create()
		/**/.defaultResult(defaultProperties)
		/**/.ifTrueThan(optionalPropertiesFileSystemKey == null, defaultProperties)
		/**/.ifTrueThan(System.getProperty(optionalPropertiesFileSystemKey) != null, loadPropertiesCallback)
		/**/.getDefaultIfNoPredicateTrue()//
		;
		return properties;
	}

}
