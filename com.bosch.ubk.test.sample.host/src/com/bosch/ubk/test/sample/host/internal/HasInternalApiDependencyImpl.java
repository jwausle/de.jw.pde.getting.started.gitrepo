package com.bosch.ubk.test.sample.host.internal;

import com.bosch.ubk.test.sample.host.api.ExportedInterface;
import com.bosch.ubk.test.sample.host.internal.api.InternalApiDependency;
import com.bosch.ubk.test.sample.host.internal.api.impl.LongRunningRuntimeImplUncleaned;

public class HasInternalApiDependencyImpl implements ExportedInterface{
	private final InternalApiDependency internalDependency = new LongRunningRuntimeImplUncleaned();

	@Override
	public String service() {
		String path = getInternalDependency().getPath();
		System.out.println("\nCalcualted path b service: " + path);
		return path;
	}

	InternalApiDependency getInternalDependency() {
		return internalDependency;
	}

}
