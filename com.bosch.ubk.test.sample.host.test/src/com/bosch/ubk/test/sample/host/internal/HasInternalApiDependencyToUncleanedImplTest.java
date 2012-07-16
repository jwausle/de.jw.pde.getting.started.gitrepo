package com.bosch.ubk.test.sample.host.internal;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.bosch.ubk.test.sample.host.internal.api.InternalApiDependency;
import com.bosch.ubk.test.sample.host.internal.api.impl.LongRunningRuntimeImplUncleaned;

public class HasInternalApiDependencyToUncleanedImplTest {

	@Test
	public void dummyTest() {
		HasInternalApiDependencyImpl testObj = new HasInternalApiDependencyImpl();
		String actualResult = testObj.service();

		Assert.assertEquals("path/to/anywhere/constant/path/suffix", actualResult);
	}

	@Test
	public void overrideGetterTest() {
		final String myLocalTestPath = "my/local/test/path/";
		final String expectedPath = myLocalTestPath + LongRunningRuntimeImplUncleaned.CONSTANT_PATH_SUFFIX;
		class OverrideGetterFromsService extends HasInternalApiDependencyImpl {

			@Override
			InternalApiDependency getInternalDependency() {
				return new InternalApiDependency() {

					@Override
					public String getPath() {
						return expectedPath;
					}
				};
			}
		}
		OverrideGetterFromsService testObj = new OverrideGetterFromsService();
		String actualResult = testObj.service();

		Assert.assertEquals(expectedPath, actualResult);
	}

	@Test
	public void overrideGetterAndReturnMockTest() {
		final String myLocalTestPath = "my/local/test/path/";
		
		final InternalApiDependency internalApiDependency = EasyMock.createNiceMock(InternalApiDependency.class);
		String expectedPath = myLocalTestPath + LongRunningRuntimeImplUncleaned.CONSTANT_PATH_SUFFIX;
		EasyMock.expect(internalApiDependency.getPath()).andReturn(expectedPath).anyTimes();
		EasyMock.replay(internalApiDependency);

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
