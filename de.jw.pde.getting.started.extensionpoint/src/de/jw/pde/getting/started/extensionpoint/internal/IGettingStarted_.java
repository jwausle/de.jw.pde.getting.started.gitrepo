package de.jw.pde.getting.started.extensionpoint.internal;

import java.util.Properties;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;

import de.jw.pde.getting.started.extensionpoint.api.IGettingStarted;

public class IGettingStarted_ {

	public static final class IGettingStartedSetUpByPropertiesFun implements Function<String, IGettingStarted> {
		private final Supplier<IGettingStarted> builder;
	
		public IGettingStartedSetUpByPropertiesFun(Supplier<IGettingStarted> builder) {
			this.builder = builder;
		}
	
		@Override
		public IGettingStarted apply(String attributeValue) {
			Preconditions.checkNotNull(attributeValue, "AttributeValue must not be null.");
			IGettingStarted iGettingStarted = Preconditions.checkNotNull(builder.get(), "IGettingStarted supplier call must no be null.");
	
			Properties defaultProperties = iGettingStarted.defaultProperties();
	
			Properties properties = ExtentensionPointCommandProviders.selectProperties(attributeValue, defaultProperties);
	
			iGettingStarted.setUp(properties);
	
			return iGettingStarted;
		}
	}

}
