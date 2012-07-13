package com.bosch.ubk.util.iftrue_handle.properties;

import java.util.Properties;

import com.bosch.ubk.util.iftrue_handle.IfTrueHandle;
import com.bosch.ubk.util.iftrue_handle.IfTrueCallback;

public class LoadPropertiesBySystemPropertiesKeyCallbacks {

	public static Properties tryLoadPropertiesOrDefault(final String optionalPropertiesFileSystemKey, Properties defaultProperties) {
		LoadPropertiesBySystemPropertiesKeyCallback loadPropertiesCallback = new LoadPropertiesBySystemPropertiesKeyCallback(
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
