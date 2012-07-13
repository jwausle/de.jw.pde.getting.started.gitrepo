package de.jw.pde.getting.started.extensionpoint.api;

import java.util.Properties;

public interface IGettingStarted {
	void setUp(Properties properties);
	
	Properties defaultProperties();

}
