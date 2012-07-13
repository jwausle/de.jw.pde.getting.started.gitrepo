package de.jw.pde.getting.started.extension1.internal.impl;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import de.jw.pde.getting.started.extensionpoint.api.IGettingStarted;

public class GettingStartedExtension1Impl implements IGettingStarted{
	public static final String DEFAULT_PATH_TO_BUNDLE_PROPERTIES_FILE = "getting-started-1.properties";
	private volatile Properties properties;
	
	public GettingStartedExtension1Impl() {
		System.out.println("Constructing " + GettingStartedExtension1Impl.class.getSimpleName() + " ..."  );
	}
	@Override
	public String toString() {
		return "GettingStarted1Impl: " + properties;
	}
	@Override
	public void setUp(Properties properties) {
		this.properties = properties;
		
	}
	@Override
	public Properties defaultProperties() {
		Properties properties2 = new Properties();
		try {
			Bundle bundle = Platform.getBundle(GettingStartedExtension1Const.BUNDLE_ID);
			Enumeration<URL> resources = bundle.getResources(DEFAULT_PATH_TO_BUNDLE_PROPERTIES_FILE);
			if(resources == null)
				throw new IllegalArgumentException("Bundle.resource not found: " + DEFAULT_PATH_TO_BUNDLE_PROPERTIES_FILE);
			
			boolean minOneResourceFound = resources.hasMoreElements();
			if(!minOneResourceFound)
				throw new IllegalArgumentException("Bundle.resource not found: " + DEFAULT_PATH_TO_BUNDLE_PROPERTIES_FILE);
			
			URL nextElement = resources.nextElement();
			properties2.load(nextElement.openStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return properties2;
	}

}
