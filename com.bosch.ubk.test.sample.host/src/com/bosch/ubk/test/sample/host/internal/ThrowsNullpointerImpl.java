package com.bosch.ubk.test.sample.host.internal;

import com.bosch.ubk.test.sample.host.api.ExportedInterface;

public class ThrowsNullpointerImpl implements ExportedInterface{

	@Override
	public String service() {
		throw new NullPointerException();
	}

}
