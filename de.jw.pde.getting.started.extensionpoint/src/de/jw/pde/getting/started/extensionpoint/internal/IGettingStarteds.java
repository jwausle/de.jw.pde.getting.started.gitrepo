package de.jw.pde.getting.started.extensionpoint.internal;

import com.google.common.base.Function;
import com.google.common.base.Supplier;

import de.jw.pde.getting.started.extensionpoint.api.IGettingStarted;

public class IGettingStarteds {

	public static Function<String, IGettingStarted> setUpGettingStartedByPropertiesFileFun(Supplier<IGettingStarted> builder) {
		return new IGettingStarted_.IGettingStartedSetUpByPropertiesFun(builder);
	}

}
