package com.bosch.ubk.test.sample.host.internal.api.impl;

import com.bosch.ubk.test.sample.host.internal.api.InternalApiDependency;

public class LongRunningLRuntimeImpl implements InternalApiDependency {
	public static String concatPrefixBeforeConstantPathSuffix(String localPathToAnywhere) {
		return localPathToAnywhere + CONSTANT_PATH_SUFFIX;
	}

	private static final String CONSTANT_PATH_SUFFIX = "constant/path/suffix";
	private final String pathToAnywhare;

	public LongRunningLRuntimeImpl(String pathToAnywhare) {
		this.pathToAnywhare = pathToAnywhare;
	}

	@Override
	public String getPath() {
		for (int i = 0; i < 50000; i++)
			System.out.print(((i % 100) == 0 ? "\n" + i : "") + ".");

		String localPathToAnywhere = getPathToAnywhere();
		return concatPrefixBeforeConstantPathSuffix(localPathToAnywhere);
	}

	private String getPathToAnywhere() {
		return pathToAnywhare;
	}

}
