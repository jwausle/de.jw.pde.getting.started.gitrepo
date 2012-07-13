
package com.bosch.ubk.util.iftrue_handle.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.bosch.ubk.util.iftrue_handle.IfTrueCallback;

public final class LoadPropertiesBySystemPropertiesKeyCallback implements IfTrueCallback<Properties> {
	private final String optionalPropertiesFileSystemKey;

	public LoadPropertiesBySystemPropertiesKeyCallback(String optionalPropertiesFileSystemKey) {
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