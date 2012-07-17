package com.bosch.ubk.test.sample.host.internal;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.bosch.ubk.test.sample.host.internal.api.InternalApiDependency;
import com.bosch.ubk.test.sample.host.internal.api.impl.LongRunningRuntimeImplUncleaned;

public class HasInternalApiDependencyToUncleanedImplTest2 {
	@Test
	public void overrideGetterAndReturnMockTest() {
		final String myLocalTestPath = "my/local/test/path/";
		
		final InternalApiDependency internalApiDependency = Mockito.mock(InternalApiDependency.class);
		
		String expectedPath = myLocalTestPath + LongRunningRuntimeImplUncleaned.CONSTANT_PATH_SUFFIX;
		
		Mockito.when(internalApiDependency.getPath()).thenReturn(expectedPath);

		class OverrideGetterFromsService extends HasInternalApiDependencyImpl {

			@Override
			InternalApiDependency getInternalDependency() {
				return internalApiDependency;
			}
		}
		
		OverrideGetterFromsService testObj = new OverrideGetterFromsService();
		String actualResult = testObj.service();

		Assert.assertEquals(expectedPath , actualResult);
	}
}
