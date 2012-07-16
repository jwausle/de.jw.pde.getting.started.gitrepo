package com.bosch.ubk.test.sample.host.internal;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.ubk.test.sample.host.internal.api.InternalApiDependency;
import com.bosch.ubk.test.sample.host.internal.api.impl.LongRunningLRuntimeImpl;

public class HasInternalApiDependencyToCleanedImplTest {
	@Test
	public void overrideGetterAndReturnMockTest() {
		final String myLocalTestPath = "my/local/test/path/";

		class OverrideGetterFromsService extends HasInternalApiDependencyImpl {

			@Override
			InternalApiDependency getInternalDependency() {
				return new LongRunningLRuntimeImpl(myLocalTestPath);
			}
		}
		
		OverrideGetterFromsService testObj = new OverrideGetterFromsService();
		String actualResult = testObj.service();

		Assert.assertEquals(myLocalTestPath + "constant/path/suffix" , actualResult);
	}
}
