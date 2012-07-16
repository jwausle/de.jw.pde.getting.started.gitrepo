package com.bosch.ubk.test.sample.host.internal.api.impl;

import com.bosch.ubk.test.sample.host.internal.api.InternalApiDependency;

public class LongRunningRuntimeImplUncleaned implements InternalApiDependency {
	public static final String CONSTANT_PATH_SUFFIX = "constant/path/suffix";
	static final String PATH_TO_ANYWHERE = "path/to/anywhere/";

	@Override
	public String getPath() {
		for (int i = 0; i < 50000; i++)
			System.out.print(((i % 100) == 0 ? "\n" + i : "") + ".");

		String localPathToAnyWhere = PATH_TO_ANYWHERE;
		return localPathToAnyWhere + CONSTANT_PATH_SUFFIX;
	}

}
